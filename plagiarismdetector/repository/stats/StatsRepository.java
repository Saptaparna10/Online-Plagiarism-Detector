package com.team113.plagiarismdetector.repository.stats;

import com.team113.plagiarismdetector.model.stats.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for plagiarism statistics
 */
@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {

    /**
     * This method is used to fetch statistics details for given prof id
     * @param profId id of professor for whom statistics is to be fetched
     * @return statistics details for given professor
     */
    @Query("select s from Stats s join Homework h on h.id = homework_id join Course c on c.id = course_id where professor_id = ?1")
    List<Stats> findByProfId(int profId);
}
