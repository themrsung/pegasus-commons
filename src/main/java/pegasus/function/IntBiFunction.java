package pegasus.function;

/**
 * A bi-function which takes two {@code int} values, and returns one value of type {@code T}.
 *
 * @param <T> The type of value to return
 */
@FunctionalInterface
public interface IntBiFunction<T> {
    /**
     * Applies this function to the provided parameters.
     *
     * @param i The first relevant integer
     * @param j The second relevant integer
     * @return The resulting value
     */
    T apply(int i, int j);
}
