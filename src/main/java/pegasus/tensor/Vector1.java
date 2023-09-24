package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable one-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector3
 * @see Vector4
 * @see Vector5
 * @see Vector6
 * @see Vector7
 * @see Vector8
 * @see Vector9
 * @see Vector10
 * @see Vector11
 * @see Vector12
 * @see LargeVector
 * @see Quaternion
 */
public class Vector1 implements Vector<Vector1> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector1 ZERO = new Vector1(0);

    /**
     * The positive X unit vector.
     */
    public static final Vector1 POSITIVE_X = new Vector1(1);

    /**
     * The negative X unit vector.
     */
    public static final Vector1 NEGATIVE_X = new Vector1(-1);

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     */
    public Vector1(double x) {
        this.x = x;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 1}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector1(Vector<?> v) {
        if (v.size() != 1) {
            throw new IllegalArgumentException("The provided vector's size is not 1.");
        }

        this.x = v.valueAt(0);
    }

    /**
     * The X component of this vector.
     */
    protected final double x;

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public double x() {
        return x;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 1;
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
        if (i != 0) throw new IndexOutOfBoundsException(i);
        return x;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Double.isNaN(x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.abs(x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        double norm = Math.abs(x);
        return norm * norm;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return Math.abs(x);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 add(double s) {
        return new Vector1(x + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 subtract(double s) {
        return new Vector1(x - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 multiply(double s) {
        return new Vector1(x * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector1 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        return new Vector1(x / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector1 add(Vector1 v) {
        return new Vector1(x + v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector1 subtract(Vector1 v) {
        return new Vector1(x - v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector1 v) {
        return x * v.x;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 abs() {
        return new Vector1(Math.abs(x));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 round() {
        return new Vector1(Math.round(x));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector1 negate() {
        return new Vector1(-x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector1 normalize() throws ArithmeticException {
        return POSITIVE_X;
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector1 map(DoubleUnaryOperator mapper) {
        return new Vector1(mapper.applyAsDouble(x));
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
    public Vector1 merge(Vector1 v, DoubleBinaryOperator merger) {
        return new Vector1(merger.applyAsDouble(x, v.x));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector1 min(Vector1 v) {
        return new Vector1(Math.min(x, v.x));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector1 max(Vector1 v) {
        return new Vector1(Math.max(x, v.x));
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
    public Vector1 clamp(Vector1 min, Vector1 max) {
        return new Vector1(Math.min(Math.max(x, min.x), max.x));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distance(Vector1 v) {
        return Math.abs(x - v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector1 v) {
        double dx = x - v.x;
        return dx * dx;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector1 v) {
        return Math.abs(x - v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(x);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{x};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x);
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
        if (v.size() != 1) return false;
        return x == v.valueAt(0);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + x + "]";
    }
}
