package msd.project;

import java.util.List;

/**
 * This interface is used to define various algorithmic methods for checking plagiarism.
 */
public interface DetectionMethod<T> {

    /**
     * @param listOfSubmission
     * This method executes the relevant algorithm for checking plagiarism
     */
    T checkPlagiarism(Submission submission);
}
