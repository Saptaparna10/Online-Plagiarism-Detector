package com.team113.plagiarismdetector.repository.user;

import com.team113.plagiarismdetector.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for user
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * This method is used to fetch details of user of given id
     * @param userId id of user for which details are to be fetched
     * @return details of user of given id
     */
    User findById(int userId);


    /**
     * This method is used to fetch user based on role
     * @param role role for which list of users is to be fetched
     * @return list of users of specified role
     */
    List<User> findByRole(String role);

    /**
     * This method is used to fetch all students not enrolled in a given course
     * @param courseId id of course in which students are not enrolled are to be fetched
     * @return list of student which are not enrolled to given course
     */
    @Query("select u from User u where role = 'student' and id not in (select student from StudentCourse sc where course_id = ?1)")
    List<User> findAllStudentForCourse(int courseId);

    /**
     * This method uis used to fetch user details by given username and password
     * @param username username of user
     * @return details of user of given username
     */
    User findByUsername(String username);

    /**
     * This method uis used to fetch if user exists of given email
     * @param email email of user
     * @return details of user of given email
     */
    boolean existsByEmail(String email);
}
