package pegasus.tuple;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.BaseStream;

/**
 * The base interface for all tuples.
 *
 * @param <T> The type of object this tuple is to hold
 * @see Tuple
 * @see DoubleTuple
 * @see LongTuple
 * @see IntTuple
 */
public interface BaseTuple<T> extends Iterable<T>, Serializable {
    /**
     * Returns the size of this tuple.
     *
     * @return The number of elements this tuple contains
     */
    int size();

    /**
     * Returns a stream whose source is the elements of this tuple.
     *
     * @return A stream of this tuple's elements
     */
    BaseStream<T, ?> stream();

    /**
     * Returns an iterator over the elements of this tuple.
     *
     * @return An iterator over this tuple's elements
     */
    Iterator<T> iterator();

    /**
     * Returns the hash code of this tuple.
     *
     * @return The hash code of this tuple
     */
    int hashCode();

    /**
     * Checks for equality between this tuple and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a tuple of the same type, and the size
     * and component values are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this tuple into a string.
     *
     * @return The string representation of this tuple
     */
    String toString();
}
