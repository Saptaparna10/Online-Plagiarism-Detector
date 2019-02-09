package com.team113.plagiarismdetector.controller.admin;

import com.team113.plagiarismdetector.model.admin.AdminNote;
import com.team113.plagiarismdetector.model.user.User;
import com.team113.plagiarismdetector.service.admin.AdminNoteService;
import com.team113.plagiarismdetector.service.user.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages all end point calls related to admin functionality
 */
@RestController
@RequestMapping("api")
public class AdminController {

    @Autowired
    private AdminNoteService adminNoteService;

    @Autowired
    private UserService userService;

    static final Logger logger = Logger.getLogger(AdminController.class);

    /**
     * This method is used to save the details of message to admin
     * @param userId id of user sending message (0 in case the message is sent by non-user)
     * @param note the details of the message to admin
     * @return result of inserting the message details to database
     */
    @PostMapping("/contactAdmin/{userId}")
    public AdminNote contactAdmin(@PathVariable int userId, @RequestBody AdminNote note) {
        logger.log(Level.INFO, "Received request to send message:" + note + " to admin by userId: " + userId);

        if(userId == 0){

            return adminNoteService.addAdminNote(note);

        }else{

            note.setUser(userService.getUserById(userId));
            return adminNoteService.addAdminNote(note);

        }
    }

    /**
     * This method is used to fetch all the notes to admin
     * @return all the notes to the admin
     */
    @GetMapping("admin/note")
    public List<AdminNote> getAllNote(){

        return adminNoteService.getAllNote();

    }

    /**
     * This method is used to delete a given noteId
     * @param noteId id of note to be deleted
     * @return list of remaining note to admin
     */
    @DeleteMapping("admin/note/{noteId}")
    public List<AdminNote> deleteNote(@PathVariable int noteId){

        adminNoteService.deleteNote(noteId);

        return adminNoteService.getAllNote();

    }

    /**
     * This method is used to fetch all the users with role as professor
     * @return list of all users with role as professor
     */
    @GetMapping("prof")
    public List<User> findAllProf() {

        logger.log(Level.INFO, "Received request to get all prof for admin");
        return userService.findAllProf();

    }

    /**
     * This method is used to approve/reject professor by admin
     * @param prof prof object for whom admin files request to approve or reject
     * @return prof object containing the result of request by admin
     */
    @PutMapping("prof/approve")
    public User approveRejectProf(@RequestBody User prof) {

        logger.log(Level.INFO, "Received request to approve/reject prof for admin" + prof);
        return userService.approveRejectProf(prof);

    }
}
