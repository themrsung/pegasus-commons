package pegasus.geometry;

import pegasus.tensor.Vector;

/**
 * An {@code n}-dimensional ray.
 *
 * @param <V> The vector used to represent this ray's coordinates
 * @see Ray2D
 * @see Ray3D
 */
public interface Ray<V extends Vector<V>> {
    /**
     * Returns the point of origin of this ray.
     *
     * @return The point of origin of this ray
     */
    V origin();

    /**
     * Returns the direction of this ray.
     *
     * @return The direction of this ray
     */
    V direction();

    /**
     * Given a distance parameter {@code t}, this returns the destination of this ray.
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    V destination(double t);
}
