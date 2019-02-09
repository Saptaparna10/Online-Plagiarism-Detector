package com.team113.plagiarismdetector.strategy;

import com.team113.plagiarismdetector.controller.exception.ParserException;

/**
 * This class acts as wrapper to execute different strategies
 */
public class Context {
    private Strategy strategy;

    /**
     * This constructor initializes the startegy to be executed
     * @param strategy the startegy to be executed
     */
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * This method executes strategy method that calculates strategy percent
     * @return strategy percent for given two files
     */
    public int executePlagiarismStrategy() throws ParserException {
        return strategy.calcPlagiarismPercent();
    }
}
