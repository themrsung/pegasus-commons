package pegasus.graphics;

import pegasus.geometry.BoundingSquare;
import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;

import java.util.function.UnaryOperator;

/**
 * A two-dimensional triangle.
 *
 * @see Triangle
 */
public class Triangle2D extends AbstractTriangle<Vector2> {
    /**
     * Creates a new triangle.
     *
     * @param a The first vertex of this triangle
     * @param b The second vertex of this triangle
     * @param c The third vertex of this triangle
     * @throws NullPointerException When a {@code null} vector is provided
     */
    public Triangle2D(Vector2 a, Vector2 b, Vector2 c) {
        super(a, b, c);
    }

    /**
     * Creates a new triangle.
     *
     * @param t The triangle of which to copy properties from
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public Triangle2D(AbstractTriangle<Vector2> t) {
        super(t);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 getCenter() {
        return Tensors.avg(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public BoundingSquare getBounds() {
        return BoundingSquare.of(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each vertex of this triangle
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Triangle2D map(UnaryOperator<Vector2> mapper) {
        return new Triangle2D(mapper.apply(a), mapper.apply(b), mapper.apply(c));
    }
}
