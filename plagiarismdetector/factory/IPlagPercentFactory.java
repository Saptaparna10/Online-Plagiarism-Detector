package com.team113.plagiarismdetector.factory;

import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismPercent;
import com.team113.plagiarismdetector.model.upload.Upload;

/**
 * This interface defines a factory for calculating plagiarism percent
 */
public interface IPlagPercentFactory {

    /**
     * This method is used to fetch plagiarism percent based on option selected by user
     * @param algoId option selected by user
     * @param studentUpload uploaded file of student for whom plagiarism is to be detected
     * @param otherUpload uploaded file of other students for that homework
     * @return percent plagiarism detected along with file details
     * @throws ParserException error in parsing file using ANTLR
     */
    PlagiarismPercent getPlagPercent(int algoId, Upload studentUpload, Upload otherUpload) throws ParserException;

}
