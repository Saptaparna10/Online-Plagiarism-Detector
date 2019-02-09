package com.team113.plagiarismdetector.controller.homework;

import com.team113.plagiarismdetector.model.homework.Homework;
import com.team113.plagiarismdetector.service.course.CourseService;
import com.team113.plagiarismdetector.service.homework.HomeworkService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This controller class manages all end point calls related to homework functionality
 */
@RestController
@RequestMapping("api")
public class HomeworkController {

    @Autowired
    private HomeworkService hwService;

    @Autowired
    private CourseService courseService;

    static final Logger logger = Logger.getLogger(HomeworkController.class);

    /**
     * This method is used to fetch details of homework by a given homework id
     * @param hwId id of the homework to be fetched
     * @return the details of homework of a given homework id
     */
    @GetMapping("hw/{hwId}")
    public Homework findHwById(@PathVariable int hwId) {
        logger.log(Level.INFO, "Received request to get Homework for hwId: " + hwId);

        return hwService.findHwById(hwId);

    }

    /**
     * This method is used to fetch all homework of given course
     * @param courseId id of course whose homework are to be fetched
     * @return details of homework of given course
     */
    @GetMapping("/course/{courseId}/hw")
    public List<Homework> findHwByCourseId(@PathVariable int courseId) {
        logger.log(Level.INFO, "Received request to find Homeworks for courseId: " + courseId);

        return hwService.findHwByCourseId(courseId);

    }

    /**
     * This method is used to create new homework for a given course
     * @param courseId id of course whose homework is to be created
     * @param hw details of homework to be created
     * @return result of inserting the homework details to database
     */
    @PostMapping("/course/{courseId}/hw")
    public Homework createHw(@PathVariable int courseId, @RequestBody Homework hw) {
        logger.log(Level.INFO, "Received request for create Homework: " + hw + " for courseId: " + courseId);

        hw.setCourse(courseService.findCourseById(courseId));
        return hwService.createHw(hw);

    }

    /**
     * This method is used to update the details of homework of given course id
     * @param hw details of homework to be updated
     * @return result of updating the homework details in database
     */
    @PutMapping("hw")
    public Homework updateHw(@RequestBody Homework hw) {
        logger.log(Level.INFO, "Received request for update Homework: " + hw);

        return hwService.updateHw(hw);

    }
}
