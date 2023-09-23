package pegasus.function;

/**
 * A function which takes one {@code float} and returns one {@code long}.
 */
@FunctionalInterface
public interface FloatToLongFunction {
    /**
     * Applies this function.
     *
     * @param value The input parameter
     * @return The resulting value
     */
    long applyAsLong(float value);
}
