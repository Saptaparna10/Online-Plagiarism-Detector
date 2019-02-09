package com.team113.plagiarismdetector.model.stats;

import com.team113.plagiarismdetector.model.homework.Homework;
import com.team113.plagiarismdetector.model.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is used to store details of Plagiarism Statistics
 */
@Entity
@Table(name = "stats")
public class Stats {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "student_stats_association"))
    private User student;
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "homework_stats_association"))
    private Homework homework;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date runDateTime;
    @Column(name = "algoId", nullable = false)
    private int algoId;
    @Column(name = "casesToRun", nullable = false)
    private int casesToRun;
    @Column(name = "casesRan", nullable = false)
    private int casesRan;

    public Stats() {
    }

    public Stats(User student, Homework homework, int algoId, int casesToRun, int casesRan) {
        this.student = student;
        this.homework = homework;
        this.algoId = algoId;
        this.casesToRun = casesToRun;
        this.casesRan = casesRan;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", student=" + student +
                ", homework=" + homework +
                ", algoId=" + algoId +
                ", casesToRun=" + casesToRun +
                ", casesRan=" + casesRan +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getRunDateTime() {
        return runDateTime;
    }

    public void setRunDateTime(Date runDateTime) {
        this.runDateTime = runDateTime;
    }

    public int getAlgoId() {
        return algoId;
    }

    public void setAlgoId(int algoId) {
        this.algoId = algoId;
    }

    public int getCasesToRun() {
        return casesToRun;
    }

    public void setCasesToRun(int casesToRun) {
        this.casesToRun = casesToRun;
    }

    public int getCasesRan() {
        return casesRan;
    }

    public void setCasesRan(int casesRan) {
        this.casesRan = casesRan;
    }
}
