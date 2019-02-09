package com.team113.plagiarismdetector.model.stats;

import com.team113.plagiarismdetector.model.plagiarism.PlagiarismPercent;

import java.util.List;

/**
 * This class holds the details of object of plagiarism runs performed for statistics
 */
public class PercentStats {

    private List<PlagiarismPercent> plagiarismPercents;
    private int casesToRun;
    private int casesRan;

    public PercentStats() {
    }

    public PercentStats(List<PlagiarismPercent> plagiarismPercents, int casesToRun, int casesRan) {
        this.plagiarismPercents = plagiarismPercents;
        this.casesToRun = casesToRun;
        this.casesRan = casesRan;
    }

    @Override
    public String toString() {
        return "PercentStats{" +
                "plagiarismPercents=" + plagiarismPercents +
                ", casesToRun=" + casesToRun +
                ", casesRan=" + casesRan +
                '}';
    }

    public List<PlagiarismPercent> getPlagiarismPercents() {
        return plagiarismPercents;
    }

    public void setPlagiarismPercents(List<PlagiarismPercent> plagiarismPercents) {
        this.plagiarismPercents = plagiarismPercents;
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
