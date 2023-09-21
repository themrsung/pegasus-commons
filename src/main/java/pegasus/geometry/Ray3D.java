package pegasus.geometry;

import pegasus.tensor.Vector3;

/**
 * A three-dimensional ray.
 *
 * @see Ray
 */
public interface Ray3D extends Ray<Vector3> {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Vector3 origin();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Vector3 direction();

    /**
     * {@inheritDoc}
     *
     * @param t The distance parameter
     * @return {@inheritDoc}
     */
    Vector3 destination(double t);
}
