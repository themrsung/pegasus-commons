package pegasus.grid;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.BaseStream;

/**
 * The base interface for all grids.
 *
 * @param <T> The type of object this grid is to hold
 * @see Grid
 * @see DoubleGrid
 * @see LongGrid
 * @see IntGrid
 */
public interface BaseGrid<T> extends Iterable<T>, Serializable {
    /**
     * Returns the size of this grid.
     *
     * @return The size of this grid
     */
    int size();

    /**
     * Returns the number of rows this grid has.
     *
     * @return The number of rows this grid has
     */
    int rows();

    /**
     * Returns the number of columns this grid has.
     *
     * @return The number of columns this grid has
     */
    int columns();

    /**
     * Returns a stream whose source is the elements of this grid.
     *
     * @return A stream of this grid's elements
     */
    BaseStream<T, ?> stream();

    /**
     * Returns an iterator over the elements of this grid.
     *
     * @return An iterator of this grid's elements
     */
    Iterator<T> iterator();

    /**
     * Returns the hash code of this grid.
     *
     * @return The hash code of this grid
     */
    int hashCode();

    /**
     * Checks for equality between this grid and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the provided object is also a grid of the same type, and the
     * dimensions and element values are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this grid into a string.
     *
     * @return The string representation of this grid
     */
    String toString();
}
