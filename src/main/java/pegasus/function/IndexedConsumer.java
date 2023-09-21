package pegasus.function;

/**
 * An indexed consumer function.
 *
 * @param <T> The type of the input parameter
 */
@FunctionalInterface
public interface IndexedConsumer<T> {
    /**
     * Accepts this consumer, executing its contents.
     *
     * @param index The relevant index
     * @param value The relevant value
     */
    void accept(int index, T value);
}
