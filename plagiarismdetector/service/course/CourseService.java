package com.team113.plagiarismdetector.service.course;

import com.team113.plagiarismdetector.model.course.Course;
import com.team113.plagiarismdetector.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class is used to execute methods for course functionality
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * This method is used to fetch details of course by given id
     * @param courseId id of course to be fetched
     * @return details of course of given id
     */
    public Course findCourseById(int courseId) {
        return courseRepository.findById(courseId);
    }

    /**
     * This method is used to fetch details of courses created by given professor
     * @param profId id of professor whose created courses are to be fetched
     * @return list of courses created by given professor
     */
    public List<Course> findCourseByProfessorId(int profId) {
        return courseRepository.findByProfessorId(profId);
    }

    /**
     * This method is used to create new course
     * @param course details of the course to be created
     * @return result of inserting course to database
     */
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * This method is used to update details of given course
     * @param course details to course to be updated
     * @return result of updating the course in database
     */
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }
}
