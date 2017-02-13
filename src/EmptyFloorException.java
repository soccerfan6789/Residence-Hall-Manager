
/**
 * Homework Assignment 1
 * CSE 214 Recitation 8
 * Recitation TA: Daniel Scanteianu
 * Grading TA: ?
 *
 * @author Shariq Syed
 *         ID: ##########
 *         NETID: ##########
 */

/**
 * Create Custom Exception called EmptyFloorException which
 * is thrown when he floor is empty
 */
public class EmptyFloorException extends Exception {
    public EmptyFloorException() {
    }

    public EmptyFloorException(String message) {
        super(message);
    }
}
