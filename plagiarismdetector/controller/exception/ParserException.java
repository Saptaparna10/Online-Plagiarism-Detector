package com.team113.plagiarismdetector.controller.exception;

import com.team113.plagiarismdetector.email.SendEmail;
import org.apache.log4j.Level;

/**
 * This is a custom exception class for exceptions encountered in parsing
 */
public class ParserException extends Exception {

    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ParserException.class);

    /**
     * This method is used to send the exception encountered to Exception class
     * @param message exception message
     */
    public ParserException(String message) {
        super(message);
        logger.log(Level.FATAL, "Encountered ParserException: " + message);
        SendEmail.send("Encountered ParserException", "ParserException message: " + message);
    }
}
