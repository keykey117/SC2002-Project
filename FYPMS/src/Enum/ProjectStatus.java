package Enum;
/**
 * This enum class represents the various project statuses that a project can have.
 * It contains four constants: AVAILABLE, RESERVED, ALLOCATED, UNAVAILABLE.
 *
 * @author Nobel
 * @version 1.0.0 Apr 15, 2023
 */
public enum ProjectStatus {
    /**
     * The project is available for allocation to students.
     */
    AVAILABLE,
    /**
     * The project has been reserved by a supervisor.
     */
    RESERVED,
    /**
     * The project has been allocated to a student.
     */
    ALLOCATED,
    /**
     * The project is not available for allocation, for example, if it has been completed or cancelled.
     */
    UNAVAILABLE
}
