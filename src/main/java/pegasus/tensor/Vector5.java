package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable five-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector3
 * @see Vector4
 * @see Vector6
 * @see Vector7
 * @see Quaternion
 */
public class Vector5 implements Vector<Vector5> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new vector.
     *
     * @param i The I component of this vector
     * @param j The J component of this vector
     * @param k The K component of this vector
     * @param l The L component of this vector
     * @param m The M component of this vector
     */
    public Vector5(double i, double j, double k, double l, double m) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 5}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector5(Vector<?> v) {
        if (v.size() != 5) {
            throw new IllegalArgumentException("The provided vector's size is not 5.");
        }

        this.i = v.valueAt(0);
        this.j = v.valueAt(1);
        this.k = v.valueAt(2);
        this.l = v.valueAt(3);
        this.m = v.valueAt(4);
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

    @Override
    public int size() {
        return 5;
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
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Double.isNaN(i) ||
                Double.isNaN(j) ||
                Double.isNaN(k) ||
                Double.isNaN(l) ||
                Double.isNaN(m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(i) &&
                Double.isFinite(j) &&
                Double.isFinite(k) &&
                Double.isFinite(l) &&
                Double.isFinite(m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(i) ||
                Double.isInfinite(j) ||
                Double.isInfinite(k) ||
                Double.isInfinite(l) ||
                Double.isInfinite(m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(i * i + j * j + k * k + l * l + m * m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        return i * i + j * j + k * k + l * l + m * m;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return Math.abs(i) + Math.abs(j) + Math.abs(k) + Math.abs(l) + Math.abs(m);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 add(double s) {
        return new Vector5(i + s, j + s, k + s, l + s, m + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 subtract(double s) {
        return new Vector5(i - s, j - s, k - s, l - s, m - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 multiply(double s) {
        return new Vector5(i * s, j * s, k * s, l * s, m * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector5 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double inv = 1 / s;
        return new Vector5(i * inv, j * inv, k * inv, l * inv, m * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector5 add(Vector5 v) {
        return new Vector5(i + v.i, j + v.j, k + v.k, l + v.l, m + v.m);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector5 subtract(Vector5 v) {
        return new Vector5(i - v.i, j - v.j, k - v.k, l - v.l, m - v.m);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector5 v) {
        return i * v.i + j * v.j + k * v.k + l * v.l + m * v.m;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 abs() {
        return new Vector5(Math.abs(i), Math.abs(j), Math.abs(k), Math.abs(l), Math.abs(m));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 round() {
        return new Vector5(Math.round(i), Math.round(j), Math.round(k), Math.round(l), Math.round(m));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector5 negate() {
        return new Vector5(-i, -j, -k, -l, -m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector5 normalize() throws ArithmeticException {
        double norm = Math.sqrt(i * i + j * j + k * k + l * l + m * m);
        if (norm == 0) throw new DivisionByZeroException();
        double inv = 1 / norm;
        return new Vector5(i * inv, j * inv, k * inv, l * inv, m * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector5 map(DoubleUnaryOperator mapper) {
        return new Vector5(
                mapper.applyAsDouble(i),
                mapper.applyAsDouble(j),
                mapper.applyAsDouble(k),
                mapper.applyAsDouble(l),
                mapper.applyAsDouble(m)
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
    public Vector5 merge(Vector5 v, DoubleBinaryOperator merger) {
        return new Vector5(
                merger.applyAsDouble(i, v.i),
                merger.applyAsDouble(j, v.j),
                merger.applyAsDouble(k, v.k),
                merger.applyAsDouble(l, v.l),
                merger.applyAsDouble(m, v.m)
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
    public Vector5 min(Vector5 v) {
        return new Vector5(
                Math.min(i, v.i),
                Math.min(j, v.j),
                Math.min(k, v.k),
                Math.min(l, v.l),
                Math.min(m, v.m)
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
    public Vector5 max(Vector5 v) {
        return new Vector5(
                Math.max(i, v.i),
                Math.max(j, v.j),
                Math.max(k, v.k),
                Math.max(l, v.l),
                Math.max(m, v.m)
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
    public Vector5 clamp(Vector5 min, Vector5 max) {
        return new Vector5(
                Math.min(Math.max(i, min.i), max.i),
                Math.min(Math.max(j, min.j), max.j),
                Math.min(Math.max(k, min.k), max.k),
                Math.min(Math.max(l, min.l), max.l),
                Math.min(Math.max(m, min.m), max.m)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distance(Vector5 v) {
        double di = i - v.i;
        double dj = j - v.j;
        double dk = k - v.k;
        double dl = l - v.l;
        double dm = m - v.m;

        return Math.sqrt(di * di + dj * dj + dk * dk + dl * dl + dm * dm);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector5 v) {
        double di = i - v.i;
        double dj = j - v.j;
        double dk = k - v.k;
        double dl = l - v.l;
        double dm = m - v.m;

        return di * di + dj * dj + dk * dk + dl * dl + dm * dm;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector5 v) {
        double ai = Math.abs(i - v.i);
        double aj = Math.abs(j - v.j);
        double ak = Math.abs(k - v.k);
        double al = Math.abs(l - v.l);
        double am = Math.abs(m - v.m);

        return ai + aj + ak + al + am;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(i, j, k, l, m);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{i, j, k, l, m};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(i, j, k, l, m);
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
        if (v.size() != 5) return false;
        return i == v.valueAt(0) &&
                j == v.valueAt(1) &&
                k == v.valueAt(2) &&
                l == v.valueAt(3) &&
                k == v.valueAt(4);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + i + ", " + j + ", " + k + ", " + l + ", " + m + "]";
    }
}
