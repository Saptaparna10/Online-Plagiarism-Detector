package com.team113.plagiarismdetector.model.homework;

import com.team113.plagiarismdetector.model.course.Course;

import javax.persistence.*;

/**
 * This class is used to store details of Homework model
 */
@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "homework_course_association"))
    private Course course;

    public Homework() {
    }

    @Override
    public String toString() {
        return "Homework{" +
                "homeworkId=" + id +
                ", homeworkName='" + name + '\'' +
                ", homeworkDescription='" + description + '\'' +
                ", course=" + course +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Homework(String name, String description, Course course) {
        this.name = name;
        this.description = description;
        this.course = course;
    }
}
