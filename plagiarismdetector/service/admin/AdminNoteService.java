package com.team113.plagiarismdetector.service.admin;

import com.team113.plagiarismdetector.model.admin.AdminNote;
import com.team113.plagiarismdetector.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service class is used to execute methods for admin functionality
 */
@Service
public class AdminNoteService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * This method is used to save the message sent to admin
     * @param note deatils of the message sent to admin
     * @return result of inserting message to database
     */
    public AdminNote addAdminNote(AdminNote note) {
        return adminRepository.save(note);
    }

    public void deleteNote(int noteId) {
        adminRepository.deleteById(noteId);
    }

    public List<AdminNote> getAllNote() {
        return adminRepository.findAll();
    }
}
