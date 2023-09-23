package pegasus.graphics;

import pegasus.geometry.BoundingBox;
import pegasus.tensor.Tensors;
import pegasus.tensor.Vector3;

import java.util.function.UnaryOperator;

/**
 * A three-dimensional triangle.
 *
 * @see Triangle
 */
public class Triangle3D extends AbstractTriangle<Vector3> {
    /**
     * Creates a new triangle.
     *
     * @param a The first vertex of this triangle
     * @param b The second vertex of this triangle
     * @param c The third vertex of this triangle
     * @throws NullPointerException When a {@code null} vector is provided
     */
    public Triangle3D(Vector3 a, Vector3 b, Vector3 c) {
        super(a, b, c);
    }

    /**
     * Creates a new triangle.
     *
     * @param t The triangle of which to copy properties from
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public Triangle3D(AbstractTriangle<Vector3> t) {
        super(t);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 getCenter() {
        return Tensors.avg(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public BoundingBox getBounds() {
        return BoundingBox.of(a, b, c);
    }

    /**
     * Returns the surface normal vector of this triangle.
     *
     * @return The surface normal vector of this triangle
     */
    public Vector3 getNormal() {
        var ab = b.subtract(a);
        var ac = c.subtract(a);

        return ab.cross(ac);
    }

    /**
     * Returns the normalized surface normal vector of this triangle.
     *
     * @return The normalized surface normal vector of this triangle
     * @throws ArithmeticException When the Euclidean norm of the normal vector is zero
     */
    public Vector3 getUnitNormal() throws ArithmeticException {
        var ab = b.subtract(a);
        var ac = c.subtract(a);

        return ab.cross(ac).normalize();
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each vertex of this triangle
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Triangle3D map(UnaryOperator<Vector3> mapper) {
        return new Triangle3D(mapper.apply(a), mapper.apply(b), mapper.apply(c));
    }
}
