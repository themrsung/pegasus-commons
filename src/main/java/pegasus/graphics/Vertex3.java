package pegasus.graphics;

import pegasus.tensor.Vector;
import pegasus.tensor.Vector3;

import java.io.Serial;

/**
 * A specialized three-dimensional vector with cached norms for optimal runtime performance.
 *
 * @see Vector3
 */
public class Vertex3 extends Vector3 {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new vertex.
     *
     * @param x The X coordinate of this vertex
     * @param y The Y coordinate of this vertex
     * @param z The Z coordinate of this vertex
     */
    public Vertex3(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 3}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vertex3(Vector<?> v) {
        super(v);
    }

    /**
     * The Euclidean norm of this vertex.
     */
    protected double norm = Double.NaN;

    /**
     * The squared Euclidean norm of this vertex.
     */
    protected double normSquared = Double.NaN;

    /**
     * The squared Manhattan norm of this vertex.
     */
    protected double normManhattan = Double.NaN;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        if (!Double.isNaN(norm)) return norm;

        norm = super.norm();
        return norm;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        if (!Double.isNaN(normSquared)) return normSquared;

        normSquared = super.normSquared();
        return normSquared;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        if (!Double.isNaN(normManhattan)) return normManhattan;

        normManhattan = super.normManhattan();
        return normManhattan;
    }
}
