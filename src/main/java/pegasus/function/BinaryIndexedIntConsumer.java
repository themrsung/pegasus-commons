package pegasus.function;

/**
 * A binary indexed consumer function.
 */
@FunctionalInterface
public interface BinaryIndexedIntConsumer {
    /**
     * Accepts this consumer, executing its contents.
     *
     * @param i     The first relevant index
     * @param j     The second relevant index
     * @param value The relevant value
     */
    void accept(int i, int j, int value);
}
