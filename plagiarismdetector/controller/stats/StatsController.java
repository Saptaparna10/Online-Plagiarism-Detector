package com.team113.plagiarismdetector.controller.stats;

import com.team113.plagiarismdetector.model.stats.Stats;
import com.team113.plagiarismdetector.service.stats.StatsService;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class manages all rest calls related to plagiarism statistics functionality
 */
@RestController
@RequestMapping("api")
public class StatsController {
    @Autowired
    private StatsService statsService;

    static final Logger logger = Logger.getLogger(StatsController.class);

    /**
     * This method is used to fetch plagiarism run statistics for given prof
     * @param profId id of prof for which plagiarism statistics are to be fetched
     * @return list of statistics for given professor
     */
    @GetMapping("prof/{profId}/stats")
    public List<Stats> findStatsForProf(@PathVariable int profId) {
        logger.log(Level.INFO, "Received request to get plagiarism statistics for profId: " + profId);
        return statsService.findStatsForProf(profId);
    }
}
