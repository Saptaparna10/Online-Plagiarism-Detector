package com.team113.plagiarismdetector.repository.upload;

import com.team113.plagiarismdetector.model.upload.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for upload
 */
@Repository
public interface UploadRepository extends JpaRepository<Upload, Integer> {

    /**
     * This method is used to fetch submission of given student and homework
     * @param studentId id of student for which submissions are to be fetched
     * @param homeworkId id of homework for which submission are to be fetched
     * @return list of submission for given student and homework
     */
    List<Upload> findByStudentIdAndHomeworkId(int studentId, int homeworkId);

    /**
     * This method is used to fetch file by given id
     * @param fileId id of file for which details are to be fetched
     * @return details of given file
     */
    Upload findById(int fileId);

    /**
     * This method is used to fetch submissions for given homework
     * @param homeworkId id of homework for which submission are to be fetched
     * @return list of submissions of given homework
     */
    @Query("select u from Upload u where homework_id = ?1 group by student_id")
    List<Upload> findByHomeworkId(int homeworkId);

    /**
     * This method is used to fetch submissions for given homework and except for particular student
     * @param studentId id of student for which submissions are to be fetched
     * @param homeworkId id of homework for which submissions are to be fetched
     * @return list of submission except for given student
     */
    @Query("select u from Upload u where homework_id = ?2 and student_id <> ?1")
    List<Upload> findUploadForAllExceptStudent(int studentId, int homeworkId);

}
