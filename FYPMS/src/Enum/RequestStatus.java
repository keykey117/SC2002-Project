package Enum;//
/**
 * This is an enum class which represents the RequestStatus for Request objects
 * @author Tham Key Yew
 * @version 1.0.0 Apr 15, 2023
 */

public enum RequestStatus {
    /**
     * Indicates that the request is pending and has not been approved or rejected.
     * */
    PENDING,
    /**
     * Indicates that the request has been approved.
     * */
    APPROVED,
    /**
     * Private constructor to restrict instantiation of RequestStatus objects.
     * */
    REJECTED;
    /**
     * Private constructor to restrict instantiation of RequestStatus objects.
     * */
    private RequestStatus() {
    }
}
