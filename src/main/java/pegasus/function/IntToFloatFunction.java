package pegasus.function;

/**
 * A function which takes one {@code int} and returns one {@code float}.
 */
@FunctionalInterface
public interface IntToFloatFunction {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    float applyAsLong(int value);
}
