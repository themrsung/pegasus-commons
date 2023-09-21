package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable two-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector3
 * @see Vector4
 * @see Vector5
 * @see Vector6
 * @see Quaternion
 */
public class Vector2 implements Vector<Vector2> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vector2 POSITIVE_X = new Vector2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector2 POSITIVE_Y = new Vector2(0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Vector2 NEGATIVE_X = new Vector2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector2 NEGATIVE_Y = new Vector2(0, -1);

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 2}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector2(Vector<?> v) {
        if (v.size() != 2) {
            throw new IllegalArgumentException("The provided vector's size is not 2.");
        }

        this.x = v.valueAt(0);
        this.y = v.valueAt(1);
    }

    /**
     * The X component of this vector.
     */
    protected final double x;

    /**
     * The Y component of this vector.
     */
    protected final double y;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 2;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the component to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public double valueAt(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IndexOutOfBoundsException(i);
        };
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public double x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public double y() {
        return y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Double.isNaN(x) || Double.isNaN(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(x) && Double.isFinite(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(x) || Double.isInfinite(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        return x * x + y * y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 add(double s) {
        return new Vector2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 subtract(double s) {
        return new Vector2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 multiply(double s) {
        return new Vector2(x * s, y * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector2 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector2(x * i, y * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 subtract(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector2 v) {
        return x * v.x + y * v.y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 abs() {
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 round() {
        return new Vector2(Math.round(x), Math.round(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 negate() {
        return new Vector2(-x, -y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector2 normalize() throws ArithmeticException {
        double s = Math.sqrt(x * x + y * y);
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector2(x * i, y * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 min(Vector2 v) {
        return new Vector2(
                Math.min(x, v.x),
                Math.min(y, v.y)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 max(Vector2 v) {
        return new Vector2(
                Math.max(x, v.x),
                Math.max(y, v.y)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum boundary vector of which to compare to
     * @param max The maximum boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 clamp(Vector2 min, Vector2 max) {
        return new Vector2(
                Math.min(Math.max(x, min.x), max.x),
                Math.min(Math.max(y, min.y), max.y)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 map(DoubleUnaryOperator mapper) {
        return new Vector2(
                mapper.applyAsDouble(x),
                mapper.applyAsDouble(y)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v      The vector of which to merge this vector with
     * @param merger The merger function of which to handle the merging of the two vectors
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector2 merge(Vector2 v, DoubleBinaryOperator merger) {
        return new Vector2(
                merger.applyAsDouble(x, v.x),
                merger.applyAsDouble(y, v.y)
        );
    }

    /**
     * Rotates this vector counter-clockwise along the XY plane by the provided angle.
     *
     * @param angRads The angle of rotation to apply to this vector in radians
     * @return The rotated vector
     */
    public Vector2 rotate(double angRads) {
        double c = Math.cos(angRads);
        double s = Math.sin(angRads);

        return new Vector2(x * c - y * s, x * s + y * c);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distance(Vector2 v) {
        double dx = x - v.x;
        double dy = y - v.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector2 v) {
        double dx = x - v.x;
        double dy = y - v.y;

        return dx * dx + dy * dy;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector2 v) {
        double ax = Math.abs(x - v.x);
        double ay = Math.abs(y - v.y);

        return ax * ay;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(x, y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{x, y};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector<?> v)) return false;
        if (v.size() != 2) return false;
        return x == v.valueAt(0) && y == v.valueAt(1);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
