package pegasus.exception;

/**
 * Thrown when two data structures have incompatible dimensions for a certain operation.
 */
public class IncompatibleDimensionsException extends RuntimeException {
    /**
     * Creates a new exception with the default detail message.
     */
    public IncompatibleDimensionsException() {
        super("Incompatible dimensions.");
    }

    /**
     * Creates a new exception with the provided detail message.
     *
     * @param s The detail message
     */
    public IncompatibleDimensionsException(String s) {
        super(s);
    }

    /**
     * Creates a new exception.
     *
     * @param message The detail message
     * @param cause   The cause of this exception
     */
    public IncompatibleDimensionsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception with the default detail message.
     *
     * @param cause The cause of this exception
     */
    public IncompatibleDimensionsException(Throwable cause) {
        super("Incompatible dimensions.", cause);
    }
}
