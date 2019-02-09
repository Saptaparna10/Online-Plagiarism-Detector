package com.team113.plagiarismdetector.repository.admin;

import com.team113.plagiarismdetector.model.admin.AdminNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface extends JpaRepository and implements CRUD functions for admin
 */
@Repository
public interface AdminRepository extends JpaRepository<AdminNote, Integer> {

}
