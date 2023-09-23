package pegasus.function;

/**
 * A function which takes one {@code double} and returns one {@code float}.
 */
@FunctionalInterface
public interface DoubleToFloatFunction {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    float applyAsFloat(double value);
}
