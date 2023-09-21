package pegasus.exception;

/**
 * Thrown when a binary index (an index with two components, usually row and column) is out of bounds.
 *
 * @see IndexOutOfBoundsException
 */
public class BinaryIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Creates a new exception with no detail message.
     */
    public BinaryIndexOutOfBoundsException() {
    }

    /**
     * Creates a new exception with the provided detail message.
     *
     * @param s The detail message of this exception
     */
    public BinaryIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * Creates a new exception with the default detail message.
     *
     * @param i The first component of the illegal index
     * @param j The second component of the illegal index
     */
    public BinaryIndexOutOfBoundsException(int i, int j) {
        super("Index out of bounds: [" + i + ", " + j + "]");
    }
}
