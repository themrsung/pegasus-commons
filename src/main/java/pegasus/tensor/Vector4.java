package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable four-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector3
 * @see Vector5
 * @see Vector6
 * @see Vector7
 * @see Vector8
 * @see Vector9
 * @see Vector10
 * @see Vector11
 * @see Vector12
 * @see Quaternion
 */
public class Vector4 implements Vector<Vector4> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector4 ZERO = new Vector4(0, 0, 0, 0);

    /**
     * The positive W unit vector.
     */
    public static final Vector4 POSITIVE_W = new Vector4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vector4 POSITIVE_X = new Vector4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector4 POSITIVE_Y = new Vector4(0, 0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vector4 POSITIVE_Z = new Vector4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final Vector4 NEGATIVE_W = new Vector4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Vector4 NEGATIVE_X = new Vector4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector4 NEGATIVE_Y = new Vector4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vector4 NEGATIVE_Z = new Vector4(0, 0, 0, -1);

    /**
     * Creates a new vector.
     *
     * @param w The W component of this vector
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector4(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 4}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector4(Vector<?> v) {
        if (v.size() != 4) {
            throw new IllegalArgumentException("The provided vector's size is not 4.");
        }

        this.w = v.valueAt(0);
        this.x = v.valueAt(1);
        this.y = v.valueAt(2);
        this.z = v.valueAt(3);
    }

    /**
     * The W component of this vector.
     */
    protected final double w;

    /**
     * The X component of this vector.
     */
    protected final double x;

    /**
     * The Y component of this vector.
     */
    protected final double y;

    /**
     * The Z component of this vector.
     */
    protected final double z;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 4;
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
            case 0 -> w;
            case 1 -> x;
            case 2 -> y;
            case 3 -> z;
            default -> throw new IndexOutOfBoundsException(i);
        };
    }

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public double w() {
        return w;
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
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Double.isNaN(w) || Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(w) && Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(w) || Double.isInfinite(x) || Double.isInfinite(y) || Double.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return Math.abs(w) + Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 add(double s) {
        return new Vector4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 subtract(double s) {
        return new Vector4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 multiply(double s) {
        return new Vector4(w * s, x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector4 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector4(w * i, x * i, y * i, z * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector4 add(Vector4 v) {
        return new Vector4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector4 subtract(Vector4 v) {
        return new Vector4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 abs() {
        return new Vector4(Math.abs(w), Math.abs(x), Math.abs(y), Math.abs(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 round() {
        return new Vector4(Math.round(w), Math.round(x), Math.round(y), Math.round(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector4 negate() {
        return new Vector4(-w, -x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector4 normalize() throws ArithmeticException {
        double s = Math.sqrt(w * w + x * x + y * y + z * z);
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector4(w * i, x * i, y * i, z * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector4 min(Vector4 v) {
        return new Vector4(
                Math.min(w, v.w),
                Math.min(x, v.x),
                Math.min(y, v.y),
                Math.min(z, v.z)
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
    public Vector4 max(Vector4 v) {
        return new Vector4(
                Math.max(w, v.w),
                Math.max(x, v.x),
                Math.max(y, v.y),
                Math.max(z, v.z)
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
    public Vector4 clamp(Vector4 min, Vector4 max) {
        return new Vector4(
                Math.min(Math.max(w, min.w), max.w),
                Math.min(Math.max(x, min.x), max.x),
                Math.min(Math.max(y, min.y), max.y),
                Math.min(Math.max(z, min.z), max.z)
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
    public Vector4 map(DoubleUnaryOperator mapper) {
        return new Vector4(
                mapper.applyAsDouble(w),
                mapper.applyAsDouble(x),
                mapper.applyAsDouble(y),
                mapper.applyAsDouble(z)
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
    public Vector4 merge(Vector4 v, DoubleBinaryOperator merger) {
        return new Vector4(
                merger.applyAsDouble(w, v.w),
                merger.applyAsDouble(x, v.x),
                merger.applyAsDouble(y, v.y),
                merger.applyAsDouble(z, v.z)
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
    public double distance(Vector4 v) {
        double dw = w - v.w;
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;

        return Math.sqrt(dw * dw + dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector4 v) {
        double dw = w - v.w;
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;

        return dw * dw + dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector4 v) {
        double aw = Math.abs(w - v.w);
        double ax = Math.abs(x - v.x);
        double ay = Math.abs(y - v.y);
        double az = Math.abs(z - v.z);

        return aw + ax + ay + az;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(w, x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{w, x, y, z};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(w, x, y, z);
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
        if (v.size() != 4) return false;
        return w == v.valueAt(0) &&
                x == v.valueAt(1) &&
                y == v.valueAt(2) &&
                z == v.valueAt(3);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + w + ", " + x + ", " + y + ", " + z + "]";
    }
}
