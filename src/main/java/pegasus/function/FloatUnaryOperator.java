package pegasus.function;

/**
 * A unary operator which handles a primitive {@code float} value.
 */
@FunctionalInterface
public interface FloatUnaryOperator {
    /**
     * Applies this operator to the provided value.
     *
     * @param value The input parameter
     * @return The resulting value
     */
    float applyAsFloat(float value);
}
