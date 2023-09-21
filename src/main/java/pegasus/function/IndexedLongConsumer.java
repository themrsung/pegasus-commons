package pegasus.function;

/**
 * An indexed consumer function.
 */
@FunctionalInterface
public interface IndexedLongConsumer {
    /**
     * Accepts this consumer, executing its contents.
     *
     * @param index The relevant index
     * @param value The relevant value
     */
    void accept(int index, long value);
}
