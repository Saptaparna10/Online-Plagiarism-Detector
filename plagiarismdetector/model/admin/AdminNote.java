package com.team113.plagiarismdetector.model.admin;

import com.team113.plagiarismdetector.model.user.User;

import javax.persistence.*;

/**
 * This class is used to store details of AdminNote model
 */
@Entity
@Table(name = "contactAdmin")
public class AdminNote {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "contactAdmin_user_association"))
    private User user;
    @Column(name = "description")
    private String description;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;

    public AdminNote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AdminNote{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public AdminNote(User user, String description, String firstName, String lastName, String email) {
        this.user = user;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
