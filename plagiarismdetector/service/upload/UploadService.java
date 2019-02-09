package com.team113.plagiarismdetector.service.upload;

import com.team113.plagiarismdetector.controller.exception.ParserException;
import com.team113.plagiarismdetector.factory.PlagPercentFactory;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismPercent;
import com.team113.plagiarismdetector.model.plagiarism.PlagiarismReport;
import com.team113.plagiarismdetector.model.stats.PercentStats;
import com.team113.plagiarismdetector.model.upload.Upload;
import com.team113.plagiarismdetector.repository.upload.UploadRepository;
import com.team113.plagiarismdetector.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This service class is used to execute methods for upload functionality
 */
@Service
public class UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    /**
     * This method is used to save details of given file
     * @param upload details of file to be saved
     */
    public void upload(Upload upload) {
        uploadRepository.saveAndFlush(upload);
    }

    /**
     * This method is used to fetch submissions for given homework
     * @param hwId id of homework for which submissions are to be fetched
     * @return list of submissions made to given homework
     */
    public List<Upload> findUploadForHw(int hwId) {
        return uploadRepository.findByHomeworkId(hwId);
    }

    /**
     * This method is used to fetch list of files uploaded by given student for given homework
     * @param studentId id of student for whom list of submitted files are to be fetched
     * @param hwId id of homework for which list of submitted files are to be fetched
     * @return list of files submitted by given student for given homework
     */
    public List<Upload> findUploadForStudent(int studentId, int hwId) {

        return uploadRepository.findByStudentIdAndHomeworkId(studentId, hwId);

    }

    /**
     * This method is used to fetch all submissions for given homework except for given student
     * @param studentId id of student whose submission is not to be fetched
     * @param hwId id of homework for which submissions are to be fetched
     * @return list of submissions made to given homework except for given student
     */
    public List<Upload> findUploadForAllExceptStudent(int studentId, int hwId) {

        return uploadRepository.findUploadForAllExceptStudent(studentId, hwId);

    }

    /**
     * This method is used to fetch all submissions for given homework except for given student
     * @param file1Id id of file1 to be compared
     * @param file2Id id of file1 to be compared
     * @return plagiarism report
     */
    public PlagiarismReport getPlagiarismReport(int file1Id, int file2Id) throws ParserException {

        Upload suspectedUpload = uploadRepository.findById(file1Id);
        Upload originalUpload = uploadRepository.findById(file2Id);
        String studentFile = new String(suspectedUpload.getPyFile());
        String otherFile = new String(originalUpload.getPyFile());

        LcsStrategy lcsStrategy = new LcsStrategy(suspectedUpload, originalUpload);
        int percent = lcsStrategy.calcPlagiarismPercent();
        Set<String> originalLines = lcsStrategy.getOriginalLines();
        Set<String> suspectedLines = lcsStrategy.getSuspectedLines();

        String htmlOriginal = getHTML(otherFile, originalLines);
        String htmlSuspected = getHTML(studentFile, suspectedLines);

        return new PlagiarismReport(htmlOriginal, htmlSuspected, percent, originalUpload, suspectedUpload);

    }

    /**
     * This method is used to generate the HTML content of similarity between the plagiarised files
     * @param file the file in string format
     * @param lineNum the line numbers of the code that is plagiarized
     * @return HTML string on highlighted plagiarized code
     */
    public String getHTML(String file, Set<String> lineNum) {

        StringBuilder res = new StringBuilder();

        String[] lines = file.split("\\r?\\n");
        for (int i = 0; i < lines.length; i++){
            boolean highlight = false;
            String currLine = lines[i];
            int currNum = i + 1;
            highlight = lineNum.contains(Integer.toString(currNum));
            if (highlight){
                res.append("<code><mark>").append(currLine).append("</mark></code>\n");
            }
            else{
                res.append("<code>").append(currLine).append("</code>\n");
            }
        }
        return res.toString();

    }

    /**
     * This method is used to execute strategy according to the user preference
     * @param studentUpload file submission of student whose submission needs to be compared
     * @param otherUpload file submission of other student in class
     * @param algoId the strategy given by user
     * @return percent report for given two file submissions
     */
    private PlagiarismPercent executeStrategy(Upload studentUpload, Upload otherUpload, int algoId) throws ParserException {

        PlagPercentFactory plagPercentFactory = new PlagPercentFactory();
        return plagPercentFactory.getPlagPercent(algoId, studentUpload, otherUpload);

    }

    public PercentStats checkPlagiarism(int hwId, int studentId, int algoId) throws ParserException {

        List<PlagiarismPercent> plagiarismPercents = new ArrayList<>();

        int casesToRun = 0;
        int casesRan = 0;

        List<Upload> studentUploads = uploadRepository.findByStudentIdAndHomeworkId(studentId, hwId);
        List<Upload> otherUploads = uploadRepository.findUploadForAllExceptStudent(studentId, hwId);

        casesToRun = studentUploads.size() * otherUploads.size();

        for(Upload studentUpload : studentUploads){
            for(Upload otherUpload : otherUploads){
                plagiarismPercents.add(executeStrategy(studentUpload, otherUpload, algoId));
                casesRan++;
            }
        }
        return new PercentStats(plagiarismPercents, casesToRun, casesRan);

    }
}

