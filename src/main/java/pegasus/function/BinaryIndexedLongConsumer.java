package pegasus.function;

/**
 * A binary indexed consumer function.
 */
@FunctionalInterface
public interface BinaryIndexedLongConsumer {
    /**
     * Accepts this consumer, executing its contents.
     *
     * @param i     The first relevant index
     * @param j     The second relevant index
     * @param value The relevant value
     */
    void accept(int i, int j, long value);
}
