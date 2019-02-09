package com.team113.plagiarismdetector.controller.user;

import com.team113.plagiarismdetector.controller.exception.UserException;
import com.team113.plagiarismdetector.email.SendEmail;
import com.team113.plagiarismdetector.model.user.User;
import com.team113.plagiarismdetector.service.user.UserService;
import org.apache.log4j.Level;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static com.team113.plagiarismdetector.constants.Constants.SESSION_NAME;

/**
 * This controller class manages all end point calls related to user functionality
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * This method is used to validate login of given user
     * @param user details of user that needs to be validated
     * @return details of user fetched from database
     * @throws UserException if user with given username and password not found
     */
    @PostMapping("login")
    public User loginUser(@RequestBody User user, HttpSession session) throws UserException {
        logger.log(Level.INFO, "Received login request for user: " + user);

        User u = userService.findUserByUsername(user.getUsername());

        if(u != null && BCrypt.checkpw(user.getPasswrd(), u.getPasswrd())){

            if(u.getRole().equals("Professor") && !u.isApproved()){

                logger.log(Level.INFO, "Admin approval is pending for user: " + user);
                throw new UserException("Admin approval is pending.");

            }

            session.setAttribute(SESSION_NAME, u);
            session.setMaxInactiveInterval(600);
            return u;

        }
        else{

            logger.log(Level.INFO, "Invalid username and password received for user: " + user);
            throw new UserException("Invalid Username and Password");

        }
    }

    /**
     * This method is used to check of the used requested is already logged in or not
     * @param session HTTP session containing the details of user
     * @return user object containing the response if user already logged in or not
     */
    @GetMapping("loggedIn")
    public User loggedIn(HttpSession session){

        User u = (User)session.getAttribute(SESSION_NAME);
        return u == null ? new User() : u;

    }

    /**
     * This method is used to invalidate the session of currently logged in user
     * @param session HTTP session containing details of session of user logged in
     * @return response after invalidating the user session
     */
    @PostMapping("logout")
    public ResponseEntity logout(HttpSession session){

        session.invalidate();
        return new ResponseEntity<>("{}", HttpStatus.OK);

    }

    /**
     * This method is used to save details of user to be registered
     * @param user details of user to be registered
     * @return result of inserting the user details to database
     * @throws UserException if user with given username exist
     */
    @PostMapping("register")
    public User registerUser(@RequestBody User user, HttpSession session) throws UserException {

        logger.log(Level.INFO, "Received register request for user: " + user);

        if(userService.findUserByUsername(user.getUsername()) != null){

            logger.log(Level.INFO, "Username already registered for user: " + user);
            throw new UserException("Username already registered");

        }

        if(userService.userExistsByEmail(user.getEmail())){

            logger.log(Level.INFO, "Email already registered for user: " + user);
            throw new UserException("Email already registered");

        }

        user.setPasswrd(BCrypt.hashpw(user.getPasswrd(), BCrypt.gensalt()));
        User u = userService.registerUser(user);

        if(user.getRole().equals("Professor")){

            SendEmail.send("New professor approval request", "New professor approval request. Details: \n" + u);
            logger.log(Level.INFO, "New professor approval request received for user: " + user);
            throw new UserException("User registered successfully. Admin approval is pending.");

        }

        session.setAttribute(SESSION_NAME, u);
        session.setMaxInactiveInterval(600);
        return u;

    }

    /**
     * This method is used to update details of user
     * @param user details of user to be updated
     * @return details of user to be updated
     */
    @PutMapping("user/update")
    public User updateUser(@RequestBody User user, HttpSession session) {
        logger.log(Level.INFO, "Received update request for user: " + user);

        user.setPasswrd(BCrypt.hashpw(user.getPasswrd(), BCrypt.gensalt()));
        session.setAttribute(SESSION_NAME, user);
        return userService.updateUser(user);

    }

    /**
     * This method is used to fetch user details by given id
     * @param userId id of user to be fetched
     * @return details of user
     */
    @GetMapping("user/{userId}")
    public User getUserById(@PathVariable int userId) {

        logger.log(Level.INFO, "Received request for getUserById for id: " + userId);
        return userService.getUserById(userId);

    }

    /**
     * This method is used to fetch all students that can be added to given course
     * @param courseId id of course to which student can be added
     * @return list of students that can be added to given course
     */
    @GetMapping("course/{courseId}/student")
    public List<User> findAllStudentForCourse(@PathVariable int courseId) {

        logger.log(Level.INFO, "Received request to get all students for courseId: " + courseId);
        return userService.findAllStudentForCourse(courseId);

    }
}
