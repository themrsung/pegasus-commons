package pegasus.container;

import java.io.Serializable;
import java.util.stream.BaseStream;

/**
 * The base interface for all container implementations.
 *
 * @param <T> The type of object of which to contain
 * @see ObjectContainer
 * @see DoubleContainer
 * @see FloatContainer
 * @see LongContainer
 * @see IntContainer
 */
public interface BaseContainer<T> extends Serializable {
    /**
     * Returns a single-element stream of this container's value.
     *
     * @return A singular stream of this container's value
     */
    BaseStream<T, ?> stream();

    /**
     * Returns the hash code of this container.
     *
     * @return The hash code of this container
     */
    int hashCode();

    /**
     * Checks for equality between this object and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the provided object is also a container of the same type,
     * and the underlying values are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this container's value into a string.
     *
     * @return The string representation of this container's value
     */
    String toString();
}
