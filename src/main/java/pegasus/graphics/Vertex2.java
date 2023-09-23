package pegasus.graphics;

import pegasus.exception.DivisionByZeroException;
import pegasus.tensor.Vector;
import pegasus.tensor.Vector2;

import java.io.Serial;

/**
 * A specialized two-dimensional vector with cached norms for optimal runtime performance.
 *
 * @see Vector2
 */
public class Vertex2 extends Vector2 {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vertex.
     */
    public static final Vertex2 ZERO = new Vertex2(0, 0);

    /**
     * The right vertex.
     */
    public static final Vertex2 RIGHT = new Vertex2(1, 0);

    /**
     * The up vertex.
     */
    public static final Vertex2 UP = new Vertex2(0, 1);

    /**
     * The left vertex.
     */
    public static final Vertex2 LEFT = new Vertex2(-1, 0);

    /**
     * The down vertex.
     */
    public static final Vertex2 DOWN = new Vertex2(0, -1);

    /**
     * Creates a new vertex.
     *
     * @param x The X coordinate of this vertex
     * @param y The Y coordinate of this vertex
     */
    public Vertex2(double x, double y) {
        super(x, y);
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 3}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vertex2(Vector<?> v) {
        super(v);
    }

    /**
     * The Euclidean norm of this vertex.
     */
    private double norm = Double.NaN;

    /**
     * The squared Euclidean norm of this vertex.
     */
    private double normSquared = Double.NaN;

    /**
     * The squared Manhattan norm of this vertex.
     */
    private double normManhattan = Double.NaN;

    /**
     * The normalized value of this vertex.
     */
    private Vertex2 normalized = null;

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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vertex2 normalize() throws ArithmeticException {
        if (normalized != null) return normalized;

        double s = norm();
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        normalized = new Vertex2(x * i, y * i);

        return normalized;
    }

    static {
        // Precalculate norms

        ZERO.norm();
        ZERO.normSquared();
        ZERO.normManhattan();

        RIGHT.norm();
        RIGHT.normSquared();
        RIGHT.normManhattan();

        UP.norm();
        UP.normSquared();
        UP.normManhattan();

        LEFT.norm();
        LEFT.normSquared();
        LEFT.normManhattan();

        DOWN.norm();
        DOWN.normSquared();
        DOWN.normManhattan();
    }
}
