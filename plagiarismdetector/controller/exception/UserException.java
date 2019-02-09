package com.team113.plagiarismdetector.controller.exception;

import org.apache.log4j.Level;

/**
 * This is a custom exception class for exceptions encountered in user
 */
public class UserException extends Exception {

    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserException.class);

    /**
     * This method is used to send the exception encountered to Exception class
     * @param message exception message
     */
    public UserException(String message) {
        super(message);
        logger.log(Level.FATAL, "Encountered UserException: " + message);
    }
}
