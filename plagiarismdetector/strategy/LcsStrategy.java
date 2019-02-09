package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.ast.DataProvider;
import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.upload.Upload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * This class implements The Dynamic Programming Strategy Longest Common Subsequence for calculating strategy percent
 * Literature referenced from Algorithms coursework
 */
public class LcsStrategy implements Strategy {

    private Upload studentUpload; // student's upload
    private Upload otherUpload; // other students upload

    private Set<String> suspectedLines; // contains the suspected lines to be highlighted
    private Set<String> originalLines; // contains the original lines to be highlighted

    private static final Logger logger = Logger.getLogger(LcsStrategy.class);

    /**
     * Constructor of the class initializing the uploads
     * @param studentUpload upload of suspected student to be compared
     * @param otherUpload upload of other student to be compared
     */
    public LcsStrategy(Upload studentUpload, Upload otherUpload){
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
    }

    /**
     * This method is used to fetch the strategy percent for given two files
     * @return strategy percent for given two files
     * If suspected python3 file has bad syntax return -1
     * If original python3 file has bad syntax return -2
     */
    @Override
    public int calcPlagiarismPercent() throws ParserException {
        logger.log(Level.INFO, "Received request to execute LcsStrategy");

        String studentFile = new String(studentUpload.getPyFile());
        String otherFile = new String(otherUpload.getPyFile());

        // data structures containing AST of both submissions
        Map<Integer, Map<String, String>> suspected = DataProvider.lcsHash(studentFile);
        Map<Integer, Map<String, String>> original = DataProvider.lcsHash(otherFile);

        // checking for empty files
        if(original.isEmpty() || suspected.isEmpty()){
            throw new ParserException("Empty file detected");
        }

        // The dynamic programming computation table
        int[][] computation = new int[suspected.size() + 1][original.size() + 1];
        // The structure to later track the longest common subsequence
        int[][][] store = new int[suspected.size()+1][original.size()+1][2];

        // loops to fill the computation structure
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

                // when an element of the lcs is found
                if (suspected.get(i-1).get(StrategyConstants.TYPE).compareTo(original.get(j-1).get(StrategyConstants.TYPE)) == 0){
                    computation[i][j] = computation[i-1][j-1] + 1;
                    store[i][j][0] = i-1;
                    store[i][j][1] = j-1;
                }

                // when element is not part of the lcs
                else{

                    if (computation[i-1][j] > computation[i][j-1]){
                        computation[i][j] = computation[i-1][j];
                        store[i][j][0] = i-1;
                        store[i][j][1] = j;
                    }
                    else{
                        computation[i][j] = computation[i][j-1];
                        store[i][j][0] = i;
                        store[i][j][1] = j-1;
                    }
                }


            }
        }
        generateSequence(suspected, original, store);

        float lcsSize = computation[suspected.size()][original.size()];
        return (int)((lcsSize/suspected.size()) * 100);
    }

    /**
     * @param suspected the suspected file ast
     * @param original original file ast
     * @param store the structure storing all sub-problems
     * Method to generate the LCS sequence
     */
    private void generateSequence(Map<Integer, Map<String, String>> suspected, Map<Integer, Map<String, String>> original, int[][][] store){

        suspectedLines = new HashSet<>();
        originalLines = new HashSet<>();

        int i = suspected.size();
        int j = original.size();

        Deque<String> stackSuspected = new ArrayDeque<>();
        Deque<String> stackOriginal = new ArrayDeque<>();

        while (i > 0 && j > 0){

            if (store[i][j][0] == i-1 && store[i][j][1] == j-1){
                stackSuspected.push(suspected.get(i-1).get(StrategyConstants.LINE_NUM));
                stackOriginal.push(original.get(j-1).get(StrategyConstants.LINE_NUM));
            }
            int ti = store[i][j][0];
            int tj = store[i][j][1];
            i = ti;
            j = tj;

        }

        int[] totalNodesOnLineSuspected = totalNodesOnLines(suspected);
        int[] totalNodesOnLineOriginal = totalNodesOnLines(original);
        int count = 0;

        while (!stackSuspected.isEmpty()){

            String current = stackSuspected.pop();
            count++;

            if (!current.equals(stackSuspected.peek()) && count/totalNodesOnLineSuspected[Integer.parseInt(current)] > StrategyConstants.THRESHOLD){
                getSuspectedLines().add(current);
                count = 0;
            }
        }

        while (!stackOriginal.isEmpty()){

            String current = stackOriginal.pop();
            count++;

            if (!current.equals(stackOriginal.peek()) && count/totalNodesOnLineOriginal[Integer.parseInt(current)] > StrategyConstants.THRESHOLD){
                getOriginalLines().add(current);
                count = 0;
            }
        }
    }

    public Set<String> getOriginalLines() {
        return originalLines;
    }

    public Set<String> getSuspectedLines() {
        return suspectedLines;
    }

    /**
     * @param data data structure holding AST
     * @return an integer array holding number of nodes in each line of the code
     */
    private int[] totalNodesOnLines(Map<Integer, Map<String, String>> data){

        int[] result = new int[data.size()];
        int i;

        for (i = 0; i < data.size(); i++){

            result[Integer.parseInt(data.get(i).get(StrategyConstants.LINE_NUM))]++;
        }
        return result;
    }
}
