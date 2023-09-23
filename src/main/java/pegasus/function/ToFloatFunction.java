package pegasus.function;

/**
 * A function which takes one arbitrary and returns a {@code float} value.
 * @param <T> The type of the input parameter
 */
public interface ToFloatFunction<T> {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    float applyAsFloat(T value);
}
