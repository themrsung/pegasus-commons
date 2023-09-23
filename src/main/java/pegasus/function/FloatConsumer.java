package pegasus.function;

/**
 * A consumer which takes one {@code float} value and returns no value.
 */
@FunctionalInterface
public interface FloatConsumer {
    /**
     * Accepts this consumer.
     *
     * @param value The input parameter
     */
    void accept(float value);
}
