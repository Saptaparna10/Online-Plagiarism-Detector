package com.team113.plagiarismdetector.factory;

import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismPercent;
import com.team113.plagiarismdetector.model.upload.Upload;
import com.team113.plagiarismdetector.strategy.*;

/**
 * This class implements IPlagPercentFactory factory pattern to calculate the plagiarism percent
 */
public class PlagPercentFactory implements IPlagPercentFactory{

    /**
     * This method is used to fetch plagiarism percent based on option selected by user
     * @param algoId option selected by user
     * @param studentUpload uploaded file of student for whom plagiarism is to be detected
     * @param otherUpload uploaded file of other students for that homework
     * @return percent plagiarism detected along with file details
     * @throws ParserException error in parsing file using ANTLR
     */
    public PlagiarismPercent getPlagPercent(int algoId, Upload studentUpload, Upload otherUpload) throws ParserException {

        switch (algoId){

            case 0:
                return calcAll(studentUpload, otherUpload);

            case 1:
                return calcPlagPercent(studentUpload, otherUpload, calcAlgo1(studentUpload, otherUpload));

            case 2:
                return calcPlagPercent(studentUpload, otherUpload, calcAlgo2(studentUpload, otherUpload));

            case 3:
                return calcPlagPercent(studentUpload, otherUpload, calcAlgo3(studentUpload, otherUpload));

            case 4:
                return calcPlagPercent(studentUpload, otherUpload, calcAlgo4(studentUpload, otherUpload));

            case 5:
                return calcPlagPercent(studentUpload, otherUpload, calcAlgo5(studentUpload, otherUpload));

            default:
                return new PlagiarismPercent();

        }
    }

    /**
     * This method is used to prepare the object to be returned containing plagiarism percent and file details
     * @param studentUpload file uploaded by student
     * @param otherUpload file of other student of that homework
     * @param percent percent plagiarism detected for given two files
     * @return object containing detail of plagiarism percent and files compared
     */
    private PlagiarismPercent calcPlagPercent(Upload studentUpload, Upload otherUpload, int percent){

        return new PlagiarismPercent(studentUpload, otherUpload, percent);

    }

    /**
     * This method is used to execute the first strategy for given file submissions
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @return percent of plagiarism between two file submissions
     */
    private int calcAlgo1(Upload studentUpload, Upload otherUpload)  throws ParserException {

        Context context1 = new Context(new StringComparison(studentUpload, otherUpload));
        return context1.executePlagiarismStrategy();

    }

    /**
     * This method is used to execute the second strategy for given file submissions
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @return percent of plagiarism between two file submissions
     */
    private int calcAlgo2(Upload studentUpload, Upload otherUpload) throws ParserException {

        Context context2 = new Context(new TypeComparison(studentUpload, otherUpload));
        return context2.executePlagiarismStrategy();

    }

    /**
     * This method is used to execute the third strategy for given file submissions
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @return percent of plagiarism between two file submissions
     */
    private int calcAlgo3(Upload studentUpload, Upload otherUpload)  throws ParserException {

        Context context3 = new Context(new LcsStrategy(studentUpload, otherUpload));
        return context3.executePlagiarismStrategy();

    }

    /**
     * This method is used to execute the fourth strategy for given file submissions
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @return percent of plagiarism between two file submissions
     */
    private int calcAlgo4(Upload studentUpload, Upload otherUpload)  throws ParserException {

        Context context4 = new Context(new EditDistanceStrategy(studentUpload, otherUpload));
        return context4.executePlagiarismStrategy();

    }

    private int calcAlgo5(Upload studentUpload, Upload otherUpload)  throws ParserException {

        return calcAll(studentUpload, otherUpload).getPercent5();

    }

    /**
     * This method is used to execute all strategies for given file submissions
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @return percent of plagiarism between two file submissions
     */
    private PlagiarismPercent calcAll(Upload studentUpload, Upload otherUpload)  throws ParserException {

        int percent1 = calcAlgo1(studentUpload, otherUpload);
        int percent2 = calcAlgo2(studentUpload, otherUpload);
        int percent3 = calcAlgo3(studentUpload, otherUpload);
        int percent4 = calcAlgo4(studentUpload, otherUpload);
        int percent5 = weightedPercent(percent3, percent4, percent1, percent2);

        return new PlagiarismPercent(studentUpload, otherUpload, percent1, percent2, percent3, percent4, percent5);

    }

    /**
     * This method is used to calculate the weighted plagiarism percent
     * @param lcs plagiarism percent by LCS strategy
     * @param ed plagiarism percent by edit distance strategy
     * @param one plagiarism percent by first strategy
     * @param two plagiarism percent by second strategy
     * @return weighted plagiarism percent
     */
    private int weightedPercent(int lcs, int ed, int one, int two){

        int lcsWeight = 10;
        int edWeight = 7;
        int algo1Weight = 5;
        int algo2Weight = 5;

        int weightedLcs = lcs * lcsWeight;
        int weightedED = ed * edWeight;
        int weightedOne = one * algo1Weight;
        int weightedTwo = two * algo2Weight;

        int totalWeight = lcsWeight + edWeight + algo1Weight +algo2Weight;

        return (weightedLcs + weightedED + weightedOne + weightedTwo)/totalWeight;
    }
}
