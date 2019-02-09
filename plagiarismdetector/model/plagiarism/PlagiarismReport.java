package com.team113.plagiarismdetector.model.plagiarism;

import com.team113.plagiarismdetector.model.upload.Upload;

/**
 * This class holds the details of plagiarism report generated for two uploaded files
 */
public class PlagiarismReport {
    private String htmlOriginal;
    private String htmlSuspected;
    private int percentMatch;
    private Upload uploadOriginal;
    private Upload uploadSuspected;

    @Override
    public String toString() {
        return "PlagiarismReport{" +
                "htmlOriginal='" + htmlOriginal + '\'' +
                ", htmlSuspected='" + htmlSuspected + '\'' +
                ", percentMatch=" + percentMatch +
                ", uploadOriginal=" + uploadOriginal +
                ", uploadSuspected=" + uploadSuspected +
                '}';
    }

    public PlagiarismReport(String htmlOriginal, String htmlSuspected, int percentMatch, Upload uploadOriginal, Upload uploadSuspected) {
        this.htmlOriginal = htmlOriginal;
        this.htmlSuspected = htmlSuspected;
        this.percentMatch = percentMatch;
        this.uploadOriginal = uploadOriginal;
        this.uploadSuspected = uploadSuspected;
    }

    public PlagiarismReport() {
    }

    public String getHtmlOriginal() {
        return htmlOriginal;
    }

    public void setHtmlOriginal(String htmlOriginal) {
        this.htmlOriginal = htmlOriginal;
    }

    public String getHtmlSuspected() {
        return htmlSuspected;
    }

    public void setHtmlSuspected(String htmlSuspected) {
        this.htmlSuspected = htmlSuspected;
    }

    public int getPercentMatch() {
        return percentMatch;
    }

    public void setPercentMatch(int percentMatch) {
        this.percentMatch = percentMatch;
    }

    public Upload getUploadOriginal() {
        return uploadOriginal;
    }

    public void setUploadOriginal(Upload uploadOriginal) {
        this.uploadOriginal = uploadOriginal;
    }

    public Upload getUploadSuspected() {
        return uploadSuspected;
    }

    public void setUploadSuspected(Upload uploadSuspected) {
        this.uploadSuspected = uploadSuspected;
    }
}