package com.team113.plagiarismdetector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This singleton class is used to fetch the config properties of the application
 */
@Component
@ConfigurationProperties
public final class ConfigProperties {

    private static String gmailHost;
    private static String gmailPort;
    private static String socketFactory;
    private static String smtpAuth;
    private static String emailFrom;
    private static String emailPass;
    private static String emailTo;
    private static String thresholdALGO2;

    /**
     * Private constructor to access static methods of class
     */
    private ConfigProperties(){ }

    public static String getGmailHost() {
        return gmailHost;
    }

    public static void setGmailHost(String gmailHost) {
        ConfigProperties.gmailHost = gmailHost;
    }

    public static String getGmailPort() {
        return gmailPort;
    }

    public static void setGmailPort(String gmailPort) {
        ConfigProperties.gmailPort = gmailPort;
    }

    public static String getSocketFactory() {
        return socketFactory;
    }

    public static void setSocketFactory(String socketFactory) {
        ConfigProperties.socketFactory = socketFactory;
    }

    public static String getSmtpAuth() {
        return smtpAuth;
    }

    public static void setSmtpAuth(String smtpAuth) {
        ConfigProperties.smtpAuth = smtpAuth;
    }

    public static String getEmailFrom() {
        return emailFrom;
    }

    public static void setEmailFrom(String emailFrom) {
        ConfigProperties.emailFrom = emailFrom;
    }

    public static String getEmailPass() {
        return emailPass;
    }

    public static void setEmailPass(String emailPass) {
        ConfigProperties.emailPass = emailPass;
    }

    public static String getEmailTo() {
        return emailTo;
    }

    public static void setEmailTo(String emailTo) {
        ConfigProperties.emailTo = emailTo;
    }

    public static String getThresholdALGO2() {
        return thresholdALGO2;
    }

    public static void setThresholdALGO2(String thresholdALGO2) {
        ConfigProperties.thresholdALGO2 = thresholdALGO2;
    }
}
