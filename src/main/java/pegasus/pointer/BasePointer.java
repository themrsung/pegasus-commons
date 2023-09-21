package pegasus.pointer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.BaseStream;

/**
 * The base interface for Pegasus pointers. Pointer can be used to allow the external
 * manipulation of the underlying data via other APIs. Pointers can also be used as arrays,
 * and have array-like features accessible in an object-oriented manner.
 *
 * @param <T> The type of object this pointer references
 */
public interface BasePointer<T> extends Iterable<T>, Serializable {
    /**
     * Returns the size of this pointer.
     *
     * @return The size of this pointer
     */
    int size();

    /**
     * Returns a stream whose source is the values of this pointer.
     *
     * @return A stream of this pointer's values
     */
    BaseStream<T, ?> stream();

    /**
     * Returns an iterator of the values of this pointer.
     *
     * @return An iterator of this pointer's values
     */
    Iterator<T> iterator();

    /**
     * Returns the hash code of this pointer.
     *
     * @return The hash code of this pointer
     */
    int hashCode();

    /**
     * Checks for equality between this pointer and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the provided object is also a pointer of the same type, and the
     * size and underlying values are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this pointer into a string.
     *
     * @return The string representation of this pointer
     */
    String toString();
}
