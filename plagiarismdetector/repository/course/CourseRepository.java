package com.team113.plagiarismdetector.repository.course;

import com.team113.plagiarismdetector.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for course
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    /**
     * This method fetches courses created by given professor
     * @param profId id of professor for whom courses are to be fetched
     * @return list of courses created by given professor id
     */
    List<Course> findByProfessorId(int profId);

    /**
     * This method fetches course by given id
     * @param courseId id of course for which details of course needs to be fetched
     * @return details of course for given id
     */
    Course findById(int courseId);
}
