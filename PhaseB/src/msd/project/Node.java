package msd.project;

import java.util.List;

/**
 * @param <T> is a type of Node in the AST
 */
public class Node<T> {

    private T value; // contains teh value of the node
    private List<Node> children; // contains the children of the node
}
