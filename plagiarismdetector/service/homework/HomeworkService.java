package com.team113.plagiarismdetector.service.homework;

import com.team113.plagiarismdetector.model.homework.Homework;
import com.team113.plagiarismdetector.repository.homework.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This service class is used to execute methods for homework functionality
 */
@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository hwRepository;

    /**
     * This method is used to fetch details of homework of given id
     * @param hwId id of homework whose details are to be fetched
     * @return details of homework of given id
     */
    public Homework findHwById(int hwId) {
        return hwRepository.findById(hwId);
    }

    /**
     * This method is used to fetch details of homeworks of a given course
     * @param courseId if of course whose homeworks are to be fetched
     * @return list of homework of given course
     */
    public List<Homework> findHwByCourseId(int courseId) {
        return hwRepository.findByCourseId(courseId);
    }

    /**
     * This method is used to save details of new homework
     * @param hw details of new homework to be saved
     * @return result of inserting details homework in database
     */
    public Homework createHw(Homework hw) {
        return hwRepository.save(hw);
    }

    /**
     * This method is used to update the details of given homework
     * @param hw details of homework to be updated
     * @return result of updating the details of homework in database
     */
    public Homework updateHw(Homework hw) {
        return hwRepository.save(hw);
    }
}
