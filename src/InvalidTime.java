/**
 * Jacob Crosby
 * Programming Project 4
 * 4/7/2026
 * This class defines a checked exception for invalid time input.
 */


public class InvalidTime extends Exception {
    private final String message;

    /**
     * Constructs an InvalidTime exception with the specified message.
     *
     * @param message reason the time is invalid
     */
    public InvalidTime(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return message describing the error
     */
    @Override
    public String getMessage() {
        return message;
    }
}
