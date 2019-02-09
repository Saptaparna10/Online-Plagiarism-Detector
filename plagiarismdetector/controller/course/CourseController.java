package com.team113.plagiarismdetector.controller.course;

import com.team113.plagiarismdetector.model.course.Course;
import com.team113.plagiarismdetector.service.course.CourseService;
import com.team113.plagiarismdetector.service.user.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages all end point calls related to course functionality
 */
@RestController
@RequestMapping("api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    static final Logger logger = Logger.getLogger(CourseController.class);

    /**
     * This method is used to fetch details of course of given course id
     * @param courseId id of the course whose details needs to be fetched
     * @return course details of given course id
     */
    @GetMapping("course/{courseId}")
    public Course findCourseById(@PathVariable int courseId) {
        logger.log(Level.INFO, "Received request to find course by courseId: " + courseId);

        return courseService.findCourseById(courseId);

    }

    /**
     * This method is used to fetch all courses created by given professor
     * @param profId id of professor whose courses is to be fetched
     * @return list of details of courses created by the professor
     */
    @GetMapping("prof/{profId}/course")
    public List<Course> findCourseByProfessorId(@PathVariable int profId) {
        logger.log(Level.INFO, "Received request to find courses for profId: " + profId);

        return courseService.findCourseByProfessorId(profId);

    }

    /**
     * This method is used to create new course for given professor
     * @param profId id of professor creating course
     * @param course details of course created by professor
     * @return result of inserting course details to database
     */
    @PostMapping("prof/{profId}/course")
    public Course createCourse(@PathVariable int profId, @RequestBody Course course) {
        logger.log(Level.INFO, "Received request for create Course: " + course);

        course.setProfessor(userService.getUserById(profId));
        return courseService.createCourse(course);

    }

    /**
     * This method is used to update details of given course
     * @param course details of course to be updated
     * @return result of updating details of course in database
     */
    @PutMapping("course")
    public Course updateCourse(@RequestBody Course course) {
        logger.log(Level.INFO, "Received request for update Course: " + course);

        return courseService.updateCourse(course);

    }
}
