package pegasus.function;

/**
 * A binary indexed consumer function.
 *
 * @param <T> The type of the input parameter
 */
@FunctionalInterface
public interface BinaryIndexedConsumer<T> {
    /**
     * Accepts this consumer, executing its contents.
     *
     * @param i     The first relevant index
     * @param j     The second relevant index
     * @param value The relevant value
     */
    void accept(int i, int j, T value);
}
