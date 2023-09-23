package pegasus.function;

/**
 * A function which takes one {@code float} and returns one {@code int}.
 */
@FunctionalInterface
public interface FloatToIntFunction {
    /**
     * Applies this function.
     *
     * @param value The input parameter
     * @return The resulting value
     */
    double applyAsDouble(int value);
}
