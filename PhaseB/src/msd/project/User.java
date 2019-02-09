package msd.project;

/**
 * This interface is used to represent different users of the system
 */
public interface User {

    /**
     * @param userName
     * @param password
     * @return true if success else false
     */
    boolean login(String userName, String password);

    /**
     * @param user
     * @return true if registration was successful else false
     */
    boolean register(User user);

    /**
     * logs out the user from the system
     */
    boolean logout();
}
