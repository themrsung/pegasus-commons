package pegasus.function;

/**
 * A function which takes one {@code long} and returns one {@code float}.
 */
@FunctionalInterface
public interface LongToFloatFunction {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    float applyAsLong(long value);
}
