package Enum;//
/**
 * This is an enum class which represents the RequestType for Request objects
 * It contains the different types of requests that can be made by a user for a FYP
 * project such as changing the title, deregistering, registering, or changing supervisor
 * @author Tham Key Yew
 * @version 1.0.0 Apr 15, 2023
 */

public enum RequestType {
    /**
     * Represents a request to change the title of a FYP project
     * */
    CHANGE_TITLE,
    /**
     * Represents a request to deregister from a FYP project
     * */
    DEREGISTER_FYP,
    /**
     * Represents a request to register for a FYP project
     * */
    REGISTER_FYP,
    /**
     * Represents a request to change the supervisor of a FYP project
     * */
    CHANGE_SUPERVISOR;
    /**
     * Private constructor to prevent instantiation of the enum
     * */
    private RequestType() {
    }
}
