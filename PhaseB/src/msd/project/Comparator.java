package msd.project;

/**
 * this interface compares two ASTs
 */
public interface Comparator {

    /**
     * @param ast1
     * @param ast2
     * @return -1, 0 , 1 depending upon the comparison as smaller ,equal or greater.
     */
    int compare(AST ast1, AST ast2);
}
