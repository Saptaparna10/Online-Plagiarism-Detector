package com.team113.plagiarismdetector.service.studentcourse;

import com.team113.plagiarismdetector.model.studentcourse.StudentCourse;
import com.team113.plagiarismdetector.repository.studentcourse.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This service class is used to execute methods for student's course functionality
 */
@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    /**
     * This method is used to add given student to given course
     * @param studentCourse details of student being added to course
     * @return result of adding given student to course
     */
    public StudentCourse addStudentToCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    /**
     * This method is used to remove given student from given course
     * @param studentCourseId id of studentCourse which is to be deleted
     */
    public void removeStudentFromCourse(int studentCourseId) {
        studentCourseRepository.deleteById(studentCourseId);
    }

    /**
     * This method is used to fetch list of courses to which student is enrolled
     * @param studentId id of student for whom the list of courses are to be fetched
     * @return list of courses to which given student is enrolled
     */
    public List<StudentCourse> findCourseForStudent(int studentId) {
        return studentCourseRepository.findByStudentId(studentId);
    }

    /**
     * This method is used to fetch list of students enrolled to given course
     * @param courseId id of course for which details of students are to be fetched
     * @return list of details of students enrolled to given course
     */
    public List<StudentCourse> findStudentOfCourse(int courseId) {
        return studentCourseRepository.findByCourseId(courseId);
    }
}
