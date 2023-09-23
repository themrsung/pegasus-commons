package pegasus.function;

/**
 * A binary operator which handles a primitive {@code float} values.
 */
@FunctionalInterface
public interface FloatBinaryOperator {
    /**
     * Applies this operator to the provided value.
     * @param left The left parameter
     * @param right The right parameter
     * @return The resulting value
     */
    float applyAsFloat(float left, float right);
}
