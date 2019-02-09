package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.controller.exception.ParserException;

/**
 * This interface defines a strategy containing method for calculating strategy percent
 */
public interface Strategy {

    /**
     * This method is used to fetch the strategy percent for given two files
     * @return strategy percent for given two files
     */
    int calcPlagiarismPercent() throws ParserException;
}
