package pegasus.function;

/**
 * A bi-function which takes two {@code int} values, and returns one {@code long} value.
 */
@FunctionalInterface
public interface IntToLongBiFunction {
    /**
     * Applies this function to the provided parameters.
     *
     * @param i The first relevant integer
     * @param j The second relevant integer
     * @return The resulting value
     */
    long applyAsLong(int i, int j);
}
