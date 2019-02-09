package msd.project;

/**
 * This interface is used for parsing Submission to AST
 */
public interface Parser {

    /**
     * @param submission
     * @return an AST generated from the submission
     */
    public AST parse(Submission submission);

}
