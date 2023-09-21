package pegasus.function;

/**
 * A bi-function which takes two {@code int} values, and returns one {@code double} value.
 */
@FunctionalInterface
public interface IntToDoubleBiFunction {
    /**
     * Applies this function to the provided parameters.
     *
     * @param i The first relevant integer
     * @param j The second relevant integer
     * @return The resulting value
     */
    double applyAsDouble(int i, int j);
}
