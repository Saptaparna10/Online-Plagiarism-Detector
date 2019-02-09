package com.team113.plagiarismdetector.model.plagiarism;

import com.team113.plagiarismdetector.model.upload.Upload;

/**
 * This class holds the details of object for storing percent calculated using each plagiarism algorithm
 */
public class PlagiarismPercent {
    private Upload studentUpload;
    private Upload otherUpload;
    private Integer percent;
    private Integer percent1;
    private Integer percent2;
    private Integer percent3;
    private Integer percent4;
    private Integer percent5;

    public PlagiarismPercent(Upload studentUpload, Upload otherUpload, Integer percent1, Integer percent2, Integer percent3, Integer percent4, Integer percent5) {
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
        this.percent1 = percent1;
        this.percent2 = percent2;
        this.percent3 = percent3;
        this.percent4 = percent4;
        this.percent5 = percent5;
    }

    public PlagiarismPercent(Upload studentUpload, Upload otherUpload, Integer percent) {
        this.studentUpload = studentUpload;
        this.otherUpload = otherUpload;
        this.percent = percent;
    }

    public PlagiarismPercent() {
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Upload getOtherUpload() {
        return otherUpload;
    }

    public void setOtherUpload(Upload otherUpload) {
        this.otherUpload = otherUpload;
    }

    @Override
    public String toString() {
        return "PlagiarismPercent{" +
                "upload=" + studentUpload +
                "upload=" + otherUpload +
                ", percent1=" + percent1 +
                ", percent2=" + percent2 +
                ", percent3=" + percent3 +
                ", percent4=" + percent4 +
                ", percent5=" + percent5 +
                '}';
    }

    public void setStudentUpload(Upload studentUpload) {
        this.studentUpload = studentUpload;
    }

    public Upload getStudentUpload() {
        return studentUpload;
    }

    public void setPercent1(Integer percent1) {
        this.percent1 = percent1;
    }

    public Integer getPercent1() {
        return percent1;
    }

    public void setPercent2(Integer percent2) {
        this.percent2 = percent2;
    }

    public Integer getPercent2() {
        return percent2;
    }

    public void setPercent3(Integer percent3) {
        this.percent3 = percent3;
    }

    public Integer getPercent3() {
        return percent3;
    }

    public void setPercent4(Integer percent4) {
        this.percent4 = percent4;
    }

    public Integer getPercent4() {
        return percent4;
    }

    public void setPercent5(Integer percent5) {
        this.percent5 = percent5;
    }

    public Integer getPercent5() {
        return percent5;
    }
}
