package com.team113.plagiarismdetector.model.upload;

import com.team113.plagiarismdetector.model.homework.Homework;
import com.team113.plagiarismdetector.model.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

/**
 * This class is used to store details of Upload model
 */
@Entity
@Table(name = "upload")
public class Upload {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "fileName", nullable = false)
    private String fileName;
    @Lob
    @Column(name = "pyFile", nullable = false)
    private byte[] pyFile;
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "student_submission_association"))
    private User student;
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "homework_submission_association"))
    private Homework homework;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date uploadDateTime;

    public Upload() {
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", pyFile=" + Arrays.toString(pyFile) +
                ", student=" + student +
                ", homework=" + homework +
                ", uploadDateTime=" + uploadDateTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getPyFile() {
        return pyFile;
    }

    public void setPyFile(byte[] pyFile) {
        this.pyFile = pyFile;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Upload(String fileName, byte[] pyFile, User student, Homework homework) {
        this.fileName = fileName;
        this.pyFile = pyFile;
        this.student = student;
        this.homework = homework;
    }

    public Date getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
}
