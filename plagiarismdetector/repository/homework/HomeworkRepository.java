package com.team113.plagiarismdetector.repository.homework;

import com.team113.plagiarismdetector.model.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This interface extends JpaRepository and implements CRUD functions for homework
 */
@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {

    /**
     * This method fetches homework of given course
     * @param courseId id of course for which homework needs to be fetched
     * @return list of homework for a given course
     */
    List<Homework> findByCourseId(int courseId);

    /**
     * This method fetches details of homework of given id
     * @param homeworkId id of homework for which details of homework is needed
     * @return details of homework of given id
     */
    Homework findById(int homeworkId);
}
