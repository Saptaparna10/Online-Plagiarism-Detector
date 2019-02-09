package com.team113.plagiarismdetector.service.user;

import com.team113.plagiarismdetector.model.user.User;
import com.team113.plagiarismdetector.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.team113.plagiarismdetector.constants.Constants.PROFESSOR_ROLE;

/**
 * This service class is used to execute methods for user functionality
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to fetch details of user of given username
     * @param username username of user to be fetched
     * @return details of user of given username
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * This method is used to fetch details of user of given email
     * @param email email of user to be fetched
     * @return details of user of given email
     */
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * This method is used to save details of given user
     * @param user details of user to be saved
     * @return result of inserting the user details to database
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * This method is used to update details of given user
     * @param user details of user to be updated
     * @return result of updating the details of given user
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * This method is used to fetch details of user of given id
     * @param userId id of user whose details are to be fetched
     * @return details of user of given id
     */
    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }

    /**
     * This method is used to fetch students not enrolled in given course
     * @param courseId id of course for which students are not enrolled
     * @return list of students not enrolled in the given course
     */
    public List<User> findAllStudentForCourse(int courseId) {
        return userRepository.findAllStudentForCourse(courseId);
    }

    /**
     * This method is used to fetch list of all the professor for admin
     * @return List of all the users with role as professor
     */
    public List<User> findAllProf() {
        return userRepository.findByRole(PROFESSOR_ROLE);
    }

    /**
     * This method is used to approve or reject professor validation
     * @param prof prof for whom the request for approval or rejection is submitted
     * @return user object with the resultant professor approval status
     */
    public User approveRejectProf(User prof) {
        return userRepository.save(prof);
    }
}
