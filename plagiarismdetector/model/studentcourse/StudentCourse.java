package com.team113.plagiarismdetector.model.studentcourse;

import com.team113.plagiarismdetector.model.course.Course;
import com.team113.plagiarismdetector.model.user.User;

import javax.persistence.*;

/**
 * This class is used to store details of StudentCourse model
 */
@Entity
@Table(name = "studentcourse")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "course_studentcourse_association"))
    private Course course;
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "student_studentcourse_association"))
    private User student;

    public StudentCourse() {
    }

    public StudentCourse(Course course, User student) {
        this.course = course;
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", course=" + course +
                ", student=" + student +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
