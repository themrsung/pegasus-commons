package pegasus.exception;

/**
 * Thrown when an instance of a non-instantiable class is attempted.
 */
public class IllegalInstanceException extends RuntimeException {
    /**
     * Creates a new exception with no detail message.
     */
    public IllegalInstanceException() {
    }

    /**
     * Creates a new exception.
     *
     * @param message The detail message
     */
    public IllegalInstanceException(String message) {
        super(message);
    }

    /**
     * Creates a new exception.
     *
     * @param instance The illegal instance
     * @param <T>      The type of object which was instantiated
     */
    public <T> IllegalInstanceException(T instance) {
        super(instance.getClass().getSimpleName() + " cannot be instantiated.");
    }
}
