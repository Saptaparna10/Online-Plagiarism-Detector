package com.team113.plagiarismdetector.repository.studentcourse;

import com.team113.plagiarismdetector.model.studentcourse.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for student's course
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {

    /**
     * This method is used to fetch details of course of given student id
     * @param studentId id of student for which details are to be fetched
     * @return details of course of given student id
     */
    List<StudentCourse> findByStudentId(int studentId);

    /**
     * This method is used to fetch details of students of given course
     * @param courseId id of course for which details are to be fetched
     * @return details of student of given course
     */
    List<StudentCourse> findByCourseId(int courseId);
}
