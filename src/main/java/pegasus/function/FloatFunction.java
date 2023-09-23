package pegasus.function;

/**
 * A function which takes one {@code float} and returns an arbitrary object.
 * @param <T> The type of object to return
 */
@FunctionalInterface
public interface FloatFunction<T> {
    /**
     * Applies this function.
     * @param value The input parameter
     * @return The resulting value
     */
    T apply(float value);
}
