package com.team113.plagiarismdetector.model.course;

import com.team113.plagiarismdetector.model.user.User;

import javax.persistence.*;

/**
 * This class is used to store details of Course model
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "homework_professor_association"))
    private User professor;

    public Course() {
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", professor=" + professor +
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

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public Course(String name, String description, User professor) {
        this.name = name;
        this.description = description;
        this.professor = professor;
    }
}
