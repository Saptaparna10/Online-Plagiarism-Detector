package com.team113.plagiarismdetector.service.stats;

import com.team113.plagiarismdetector.model.homework.Homework;
import com.team113.plagiarismdetector.model.stats.Stats;
import com.team113.plagiarismdetector.model.user.User;
import com.team113.plagiarismdetector.repository.stats.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class is used to execute methods for plagiarism statistics functionality
 */
@Service
public class StatsService {
    @Autowired
    private StatsRepository statsRepository;

    /**
     * This method is used to fetch plagiarism statistics for given prof
     * @param profId id of prof for which plagiarism statistics are to be fetched
     * @return details of plagiarism statistics for given prof
     */
    public List<Stats> findStatsForProf(int profId) {
        return statsRepository.findByProfId(profId);
    }

    /**
     * This method is used to add new plagiarism statistics for given homework, student and algo
     */
    public void addNewRun(Homework homework, User student, int algoId, int casesToRun, int casesRan) {
        statsRepository.save(new Stats(student, homework, algoId, casesToRun, casesRan));
    }
}
