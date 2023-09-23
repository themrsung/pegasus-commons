package pegasus.function;

/**
 * A supplier function which returns a {@code float} value.
 */
@FunctionalInterface
public interface FloatSupplier {
    /**
     * Executes this supplier function.
     * @return The resulting value
     */
    float getAsFloat();
}
