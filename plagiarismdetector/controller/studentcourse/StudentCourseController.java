package com.team113.plagiarismdetector.controller.studentcourse;

import com.team113.plagiarismdetector.model.studentcourse.StudentCourse;
import com.team113.plagiarismdetector.model.user.User;
import com.team113.plagiarismdetector.repository.course.CourseRepository;
import com.team113.plagiarismdetector.repository.user.UserRepository;
import com.team113.plagiarismdetector.service.studentcourse.StudentCourseService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages all end point calls related to student's course functionality
 */
@RestController
@RequestMapping("/api")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepo;

    static final Logger logger = Logger.getLogger(StudentCourseController.class);

    /**
     * This method is used to add given student to given course
     * @param courseId id of course to which student is added
     * @param studentId id of student to be added to given course
     * @return result of adding student to course in database
     */
    @PostMapping("/course/{courseId}/student/{studentId}/add")
    public List<User> addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId) {
        logger.log(Level.INFO, "Received request to add studentId: " + studentId +" to courseId: " + courseId);

        StudentCourse studentCourse = new StudentCourse(courseRepo.findById(courseId), userRepository.findById(studentId));
        studentCourseService.addStudentToCourse(studentCourse);

        return userRepository.findAllStudentForCourse(courseId);

    }

    /**
     * This method is used to remove given student to given course
     * @param studentCourseId id of studentCourse which is to be deleted
     * @return result of removing student to course in database
     */
    @PostMapping("course/{courseId}/studentCourse/{studentCourseId}")
    public List<StudentCourse> removeStudentFromCourse(@PathVariable int courseId, @PathVariable int studentCourseId) {
        logger.log(Level.INFO, "Received request to remove studentCourseId: " + studentCourseId);

        studentCourseService.removeStudentFromCourse(studentCourseId);
        return studentCourseService.findStudentOfCourse(courseId);

    }

    /**
     * This method is used to fetch details of all courses to which student can be added
     * @param studentId id of student for whom details of courses are to be fetched
     * @return list of details of courses to which student is added
     */
    @GetMapping("/student/{studentId}/course")
    public List<StudentCourse> findCourseForStudent(@PathVariable int studentId) {
        logger.log(Level.INFO, "Received request to find courses for studentId" + studentId);

        return studentCourseService.findCourseForStudent(studentId);

    }

    /**
     * This method is used to fetch details of all students enrolled to given course
     * @param courseId id of course for which details of students are to be fetched
     * @return list of details of students enrolled to given course
     */
    @GetMapping("/course/{courseId}/enrolled")
    public List<StudentCourse> findStudentOfCourse(@PathVariable int courseId) {
        logger.log(Level.INFO, "Received request to find students for courseId" + courseId);

        return studentCourseService.findStudentOfCourse(courseId);

    }
}
