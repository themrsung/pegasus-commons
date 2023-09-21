package pegasus.exception;

/**
 * Thrown when division by zero is attempted.
 */
public class DivisionByZeroException extends ArithmeticException {
    /**
     * Creates a new exception with the default detail message.
     */
    public DivisionByZeroException() {
        super("Cannot divide by zero.");
    }

    /**
     * Creates a new exception.
     *
     * @param s The detail message
     */
    public DivisionByZeroException(String s) {
        super(s);
    }
}
