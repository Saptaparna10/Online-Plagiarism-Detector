package com.team113.plagiarismdetector.controller.upload;

import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.email.SendEmail;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismPercent;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismReport;
import com.team113.plagiarismdetector.model.stats.PercentStats;
import com.team113.plagiarismdetector.model.upload.Upload;
import com.team113.plagiarismdetector.service.homework.HomeworkService;
import com.team113.plagiarismdetector.service.stats.StatsService;
import com.team113.plagiarismdetector.service.upload.UploadService;
import com.team113.plagiarismdetector.service.user.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

/**
 * This controller class manages all end point calls related to upload functionality
 */
@RestController
@RequestMapping("api")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeworkService hwService;

    @Autowired
    private StatsService statsService;

    static final Logger logger = Logger.getLogger(UploadController.class);

    /**
     * This method is used to save file by given student to database
     * @param request details of file to be saved
     * @param studentId id of student who has updated the file
     * @param hwId id of homework to which student has uploaded file
     * @return status of saving file to database
     * @throws IOException
     */
    @PostMapping(value="student/{studentId}/hw/{hwId}/upload")
    public ResponseEntity upload(MultipartHttpServletRequest request, @PathVariable int studentId, @PathVariable int hwId) {
        logger.log(Level.INFO, "Received request for upload for studentId: " + studentId + "HomeworkId: " + hwId);
        Iterator<String> itr = request.getFileNames();

        while (itr.hasNext()) {
            String uploadedFile = itr.next();
            MultipartFile file = request.getFile(uploadedFile);
            String fileName = file.getOriginalFilename();
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                SendEmail.send("Encountered exception in reading bytes from file: " + fileName, "Exception: " + e);
            }
            Upload upload = new Upload(fileName, bytes, userService.getUserById(studentId), hwService.findHwById(hwId));
            uploadService.upload(upload);
        }
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /**
     * This method is used to fetch submissions for given homework
     * @param hwId id of homework for which submissions are to be fetched
     * @return list of submissions for given homework
     */
    @GetMapping("hw/{hwId}/upload")
    public List<Upload> findUploadForHw(@PathVariable int hwId) {
        logger.log(Level.INFO, "Received request to find uploads for hwId: " + hwId);
        return uploadService.findUploadForHw(hwId);
    }

    /**
     * This method is used to fetch submission for given student
     * @param hwId id of homework for which submission is to be fetched
     * @param studentId id of student for whom submission is to be fetched
     * @return
     */
    @GetMapping("hw/{hwId}/student/{studentId}")
    public List<Upload> findUploadForStudent(@PathVariable int hwId, @PathVariable int studentId) {
        logger.log(Level.INFO, "Received request to find upload for studentId: "+ studentId + " for hwId: " + hwId);
        return uploadService.findUploadForStudent(studentId, hwId);
    }

    /**
     * This method is used to fetch plagiarism report for given two files
     * @param file1Id id of file1 for which report is to be fetched
     * @param file2Id id of file1 for which report is to be fetched
     * @return details of plagiarism for given two files
     */
    @GetMapping("file1/{file1Id}/file2/{file2Id}/report")
    public PlagiarismReport plagiarismReport(@PathVariable int file1Id, @PathVariable int file2Id) throws ParserException {
        logger.log(Level.INFO, "Received request to get Plagiarism report for file1Id: "+ file1Id + " and file2Id: " + file2Id);
        return uploadService.getPlagiarismReport(file1Id, file2Id);
    }

    /**
     * This method is used to fetch strategy percent for given student submission with rest of submission in given homework
     * @param hwId id of homework for which strategy percent is to be fetched for given student
     * @param studentId id of student for whom strategy percent is to be fetched
     * @return details of strategy percent for given student with rest of submission in given homework
     */
    @GetMapping("hw/{hwId}/student/{studentId}/percent/{algoId}")
    public List<PlagiarismPercent> checkPlagiarism(@PathVariable int hwId, @PathVariable int studentId, @PathVariable int algoId)  throws ParserException {
        logger.log(Level.INFO, "Received request to check Plagiarism percent for studentId: "+ studentId + " for hwId: " + hwId);

        PercentStats percentStats = uploadService.checkPlagiarism(hwId, studentId, algoId);

        statsService.addNewRun(hwService.findHwById(hwId), userService.getUserById(studentId), algoId, percentStats.getCasesToRun(), percentStats.getCasesRan());

        return percentStats.getPlagiarismPercents();
    }
}
