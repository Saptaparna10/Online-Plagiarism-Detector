package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.ast.DataProvider;
import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.upload.Upload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * This class implements type comparision strategy.
 * It identifies the AST node types (eg. while, if etc)
 * and counts them in both files. It checks if count for
 * a particular type is differing by at most threshold value.
 */
public class TypeComparison implements Strategy {
    private static final int THRESHOLD = 5;

    private Upload studentUpload;
    private Upload otherUpload;

    static final Logger logger = Logger.getLogger(TypeComparison.class);

    /**
     * Constructor of the class initializing the uploads
     * @param studentUpload upload of suspected student to be compared
     * @param otherUpload upload of other student to be compared
     */
    public TypeComparison(Upload studentUpload, Upload otherUpload){
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
    }

    /**
     * This method is used to fetch the strategy percent for given two files
     * @return strategy percent for given two files
     */
    @Override
    public int calcPlagiarismPercent() throws ParserException {
        logger.log(Level.INFO, "Received request to execute TypeComparison");

        String studentFile = new String(studentUpload.getPyFile());
        String otherFile = new String(otherUpload.getPyFile());

        Map<Integer, Integer> studentCategoryHash = DataProvider.cateHash(studentFile);
        Map<Integer, Integer> otherCategoryHash = DataProvider.cateHash(otherFile);

        if(studentCategoryHash.isEmpty() || otherCategoryHash.isEmpty()){
            throw new ParserException("Empty file detected");
        }

        // Count of number of possible plagiarisms
        int count = 0;

        // Counts number of times, the nodes of some type in both files, differ by at most threshold
        for (Map.Entry<Integer, Integer> entry : studentCategoryHash.entrySet()) {
            if (otherCategoryHash.containsKey(entry.getKey()) &&
                    (Math.abs(studentCategoryHash.get(entry.getKey()) - otherCategoryHash.get(entry.getKey())) < THRESHOLD)){
                        count++;
            }
        }

        float similarity = (float)count/studentCategoryHash.size();

        return (int)(similarity*100);
    }
}
