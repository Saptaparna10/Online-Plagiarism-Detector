package com.team113.plagiarismdetector.model.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class is used to store details of User model
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "username", unique=true, nullable = false)
    private String username;
    @Column(name = "passwrd", nullable = false)
    private String passwrd;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "approved")
    private boolean approved;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.passwrd = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", passwrd='" + passwrd + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", approved=" + approved +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User(String firstName, String lastName, String username, String passwrd, String role, String email, boolean approved) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwrd = passwrd;
        this.role = role;
        this.email = email;
        this.approved = approved;
    }
}
