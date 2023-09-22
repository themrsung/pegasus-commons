package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable seven-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector3
 * @see Vector4
 * @see Vector5
 * @see Vector6
 * @see Vector8
 * @see Vector9
 * @see Vector10
 * @see Vector11
 * @see Vector12
 * @see LargeVector
 * @see Quaternion
 */
public class Vector7 implements Vector<Vector7> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector7 ZERO = new Vector7(0, 0, 0, 0, 0, 0, 0);

    /**
     * Creates a new vector.
     *
     * @param i The I component of this vector
     * @param j The J component of this vector
     * @param k The K component of this vector
     * @param l The L component of this vector
     * @param m The M component of this vector
     * @param n The N component of this vector
     * @param o The O component of this vector
     */
    public Vector7(double i, double j, double k, double l, double m, double n, double o) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 7}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector7(Vector<?> v) {
        if (v.size() != 7) {
            throw new IllegalArgumentException("The provided vector's size is not 7.");
        }

        this.i = v.valueAt(0);
        this.j = v.valueAt(1);
        this.k = v.valueAt(2);
        this.l = v.valueAt(3);
        this.m = v.valueAt(4);
        this.n = v.valueAt(5);
        this.o = v.valueAt(6);
    }

    /**
     * The I component of this vector.
     */
    protected final double i;

    /**
     * The J component of this vector.
     */
    protected final double j;

    /**
     * The K component of this vector.
     */
    protected final double k;

    /**
     * The L component of this vector.
     */
    protected final double l;

    /**
     * The M component of this vector.
     */
    protected final double m;

    /**
     * The N component of this vector.
     */
    protected final double n;

    /**
     * The O component of this vector.
     */
    protected final double o;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 7;
    }

    /**
     * Returns the component at the specified index of this vector.
     *
     * @param index The index of the component to get
     * @return The component at the specified index
     * @throws IndexOutOfBoundsException When the provided index is out of bounds
     */
    @Override
    public double valueAt(int index) throws IndexOutOfBoundsException {
        return switch (index) {
            case 0 -> i;
            case 1 -> j;
            case 2 -> k;
            case 3 -> l;
            case 4 -> m;
            case 5 -> n;
            case 6 -> o;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    /**
     * Returns the I component of this vector.
     *
     * @return The I component of this vector
     */
    public double i() {
        return i;
    }

    /**
     * Returns the J component of this vector.
     *
     * @return The J component of this vector
     */
    public double j() {
        return j;
    }

    /**
     * Returns the K component of this vector.
     *
     * @return The K component of this vector
     */
    public double k() {
        return k;
    }

    /**
     * Returns the L component of this vector.
     *
     * @return The L component of this vector
     */
    public double l() {
        return l;
    }

    /**
     * Returns the M component of this vector.
     *
     * @return The M component of this vector
     */
    public double m() {
        return m;
    }

    /**
     * Returns the N component of this vector.
     *
     * @return The N component of this vector
     */
    public double n() {
        return n;
    }

    /**
     * Returns the O component of this vector.
     *
     * @return The O component of this vector
     */
    public double o() {
        return o;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return stream().anyMatch(Double::isNaN);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return stream().allMatch(Double::isFinite);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return stream().anyMatch(Double::isInfinite);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(stream().map(x -> x * x).sum());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        return stream().map(x -> x * x).sum();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return stream().map(Math::abs).sum();
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 add(double s) {
        return new Vector7(i + s, j + s, k + s, l + s, m + s, n + s, o + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 subtract(double s) {
        return new Vector7(i - s, j - s, k - s, l - s, m - s, n - s, o - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 multiply(double s) {
        return new Vector7(i * s, j * s, k * s, l * s, m * s, n * s, o * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector7 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double inv = 1 / s;
        return new Vector7(i * inv, j * inv, k * inv, l * inv, m * inv, n * inv, o * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector7 add(Vector7 v) {
        return new Vector7(i + v.i, j + v.j, k + v.k, l + v.l, m + v.m, n + v.n, o + v.o);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector7 subtract(Vector7 v) {
        return new Vector7(i - v.i, j - v.j, k - v.k, l - v.l, m - v.m, n - v.n, o - v.o);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector7 v) {
        return i * v.i + j * v.j + k * v.k + l * v.l + m * v.m + n * v.n + o * v.o;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 abs() {
        return new Vector7(Math.abs(i), Math.abs(j), Math.abs(k), Math.abs(l), Math.abs(m), Math.abs(n), Math.abs(o));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 round() {
        return new Vector7(
                Math.round(i),
                Math.round(j),
                Math.round(k),
                Math.round(l),
                Math.round(m),
                Math.round(n),
                Math.round(o)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector7 negate() {
        return new Vector7(-i, -j, -k, -l, -m, -n, -o);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector7 normalize() throws ArithmeticException {
        double norm = Math.sqrt(stream().map(x -> x * x).sum());
        if (norm == 0) throw new DivisionByZeroException();
        double inv = 1 / norm;
        return new Vector7(i * inv, j * inv, k * inv, l * inv, m * inv, n * inv, o * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector7 map(DoubleUnaryOperator mapper) {
        return new Vector7(
                mapper.applyAsDouble(i),
                mapper.applyAsDouble(j),
                mapper.applyAsDouble(k),
                mapper.applyAsDouble(l),
                mapper.applyAsDouble(m),
                mapper.applyAsDouble(n),
                mapper.applyAsDouble(o)
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
    public Vector7 merge(Vector7 v, DoubleBinaryOperator merger) {
        return new Vector7(
                merger.applyAsDouble(i, v.i),
                merger.applyAsDouble(j, v.j),
                merger.applyAsDouble(k, v.k),
                merger.applyAsDouble(l, v.l),
                merger.applyAsDouble(m, v.m),
                merger.applyAsDouble(n, v.n),
                merger.applyAsDouble(o, v.o)
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
    public Vector7 min(Vector7 v) {
        return merge(v, Math::min);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector7 max(Vector7 v) {
        return merge(v, Math::max);
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
    public Vector7 clamp(Vector7 min, Vector7 max) {
        return max(min).min(max);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distance(Vector7 v) {
        return subtract(v).norm();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector7 v) {
        return subtract(v).normSquared();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector7 v) {
        return subtract(v).normManhattan();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(i, j, k, l, m, n, o);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{i, j, k, l, m, n, o};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(i, j, k, l, m, n, o);
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
        if (v.size() != 7) return false;
        return i == v.valueAt(0) &&
                j == v.valueAt(1) &&
                k == v.valueAt(2) &&
                l == v.valueAt(3) &&
                k == v.valueAt(4) &&
                l == v.valueAt(5) &&
                o == v.valueAt(6);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + i + ", " + j + ", " + k + ", " + l + ", " + m + ", " + n + ", " + o + "]";
    }
}
