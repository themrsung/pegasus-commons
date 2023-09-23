package pegasus.function;

/**
 * A function which takes one {@code float} and returns one {@code double}.
 */
@FunctionalInterface
public interface FloatToDoubleFunction {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    double applyAsDouble(float value);
}
