package pegasus.geometry;

import pegasus.tensor.Vector2;

/**
 * A two-dimensional ray.
 *
 * @see Ray
 */
public interface Ray2D extends Ray<Vector2> {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Vector2 origin();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Vector2 direction();

    /**
     * {@inheritDoc}
     *
     * @param t The distance parameter
     * @return {@inheritDoc}
     */
    Vector2 destination(double t);
}
