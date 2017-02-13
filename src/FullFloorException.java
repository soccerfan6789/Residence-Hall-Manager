
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
 * Create Custom Exception called FullFloorException which
 * is thrown when he floor is full
 */
public class FullFloorException extends Exception {
    public FullFloorException() {
    }

    public FullFloorException(String message) {
        super(message);
    }

}
