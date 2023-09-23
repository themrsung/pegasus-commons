package pegasus.geometry;

import pegasus.tensor.Vector;
import pegasus.tuple.Tuple;

/**
 * The superinterface for boundary objects such as {@link BoundingSquare bounding squares}
 * and {@link BoundingBox bounding boxes}.
 *
 * @param <V> The vector used as the vertices of this boundary
 * @see BoundingSquare
 * @see BoundingBox
 */
public interface Boundary<V extends Vector<V>> {
    /**
     * Returns whether this boundary contains the provided vertex {@code v}.
     *
     * @param v The vertex of which to check for containment
     * @return {@code true} if this boundary contains the provided vertex {@code v}
     * @throws NullPointerException When the provided vertex {@code v} is {@code null}
     */
    boolean contains(V v);

    /**
     * Returns the minimum boundary vector of this boundary.
     *
     * @return The minimum boundary vector of this boundary
     */
    V min();

    /**
     * Returns the maximum boundary vector of this boundary.
     *
     * @return The maximum boundary vector of this boundary
     */
    V max();

    /**
     * Returns a tuple containing the corners of this boundary.
     *
     * @return A tuple of the corners of this boundary
     */
    Tuple<V> corners();

    /**
     * Serializes this boundary into a string.
     *
     * @return The string representation of this boundary
     */
    String toString();
}
