package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.upload.Upload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * This class implements string comparision strategy.
 * It identifies the common words in both the files
 * and takes the ratio of common words count to total length.
 */
public class StringComparison implements Strategy {

    private Upload studentUpload;
    private Upload otherUpload;

    static final Logger logger = Logger.getLogger(StringComparison.class);

    /**
     * Constructor of the class initializing the uploads
     * @param studentUpload upload of suspected student to be compared
     * @param otherUpload upload of other student to be compared
     */
    public StringComparison(Upload studentUpload, Upload otherUpload){
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
    }

    /**
     * This method is used to fetch the strategy percent for given two files
     * @return strategy percent for given two files
     */
    @Override
    public int calcPlagiarismPercent() throws ParserException {
        logger.log(Level.INFO, "Received request to execute StringComparison");

        String studentFile = new String(studentUpload.getPyFile());
        String otherFile = new String(otherUpload.getPyFile());

        String[] strList1 = studentFile.split("\\W+");
        String[] strList2 = otherFile.split("\\W+");

        if(strList1.length == 0 || strList2.length == 0){
            throw new ParserException("Empty file detected");
        }

        Set<String> list1 = new HashSet<>();
        Set<String> list2 = new HashSet<>();

        for(String s:strList1){
            list1.add(s);
        }
        for(String s:strList2){
            list2.add(s);
        }

        // Prepare an intersection
        List<String> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);

        // Calculates similarity for first file
        float similarity = (float) intersection.size() / list1.size();

        return (int) (similarity * 100);
    }
}
