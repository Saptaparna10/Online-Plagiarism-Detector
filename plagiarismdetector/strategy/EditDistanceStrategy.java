package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.ast.DataProvider;
import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.upload.Upload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * This class implements The Dynamic Programming Strategy Edit Distance for calculating strategy percent
 * Literature referenced from Algorithms coursework
 */
public class EditDistanceStrategy implements Strategy {

    private Upload studentUpload;
    private Upload otherUpload;

    private static final Logger logger = Logger.getLogger(EditDistanceStrategy.class);

    /**
     * Constructor of the class initializing the uploads
     * @param studentUpload upload of suspected student to be compared
     * @param otherUpload upload of other student to be compared
     */
    public EditDistanceStrategy(Upload studentUpload, Upload otherUpload){
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
    }

    /**
     * This method is used to fetch the strategy percent for given two files
     * @return strategy percent for given two files
     */
    @Override
    public int calcPlagiarismPercent() throws ParserException {
        logger.log(Level.INFO, "Received request to execute EditDistanceStrategy");

        String studentFile = new String(studentUpload.getPyFile());
        String otherFile = new String(otherUpload.getPyFile());

        Map<Integer, Map<String, String>> suspected = DataProvider.lcsHash(studentFile);
        Map<Integer, Map<String, String>> original = DataProvider.lcsHash(otherFile);

        if(original.isEmpty() || suspected.isEmpty()){
            throw new ParserException("Empty file detected");
        }
        return getPlagiarismPercent(suspected, original);
    }

    /**
     * @param suspected data structure for suspected file
     * @param original data structure for original file
     * @return plagiarism percent for the two submissions
     * If suspected python3 file has bad syntax return -1
     * If original python3 file has bad syntax return -2
     */
    private int getPlagiarismPercent(Map<Integer, Map<String, String>> suspected, Map<Integer, Map<String, String>> original){
        double[][] computation = new double[suspected.size()+1][original.size()+1];

        for (int i = 1; i <= suspected.size(); i++){

            if (suspected.get(i-1) == null){
                logger.log(Level.INFO, "suspected file is a wrong python3 syntax");
                return -1;
            }

            for (int j = 1; j <= original.size(); j++){
                if (original.get(j-1)== null){
                    logger.log(Level.INFO, "original file is a wrong python3 syntax");
                    return -2;
                }
                double cost = suspected.size();

                if (suspected.get(i-1).get(StrategyConstants.TYPE).equals(original.get(j-1).get(StrategyConstants.TYPE))){

                    cost = computation[i-1][j-1];
                }
                if (computation[i-1][j-1] + 1 < cost){

                    cost = computation[i-1][j-1] + 1;
                }
                if (computation[i-1][j] + 1 < cost) {

                    cost = computation[i-1][j] + 1;
                }
                if (computation[i][j-1] + 1 < cost){

                    cost = computation[i][j-1] + 1;
                }
                computation[i][j] = cost;
            }
        }
        double editCost = computation[suspected.size()][original.size()];
        return (int)((1 - editCost/original.size()) * 100);
    }
}
