package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable three-dimensional vector. Three-dimensional vectors have three components
 * labeled {@code x, y, z}. When used in a geometric context, the components represent a coordinate
 * along their corresponding axes. The axes and their directions are defined as follows.
 * <ul>
 *     <li>X axis - Represents horizontal movement, and increments to the right</li>
 *     <li>Y axis - Represents height, and increments upwards.</li>
 *     <li>Z axis - Represents depth, and increments as the coordinate recedes from the viewer.</li>
 * </ul>
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector4
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
public class Vector3 implements Vector<Vector3> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vector3 POSITIVE_X = new Vector3(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector3 POSITIVE_Y = new Vector3(0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vector3 POSITIVE_Z = new Vector3(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Vector3 NEGATIVE_X = new Vector3(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector3 NEGATIVE_Y = new Vector3(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vector3 NEGATIVE_Z = new Vector3(0, 0, -1);

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 3}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector3(Vector<?> v) {
        if (v.size() != 3) {
            throw new IllegalArgumentException("The provided vector's size is not 3.");
        }

        this.x = v.valueAt(0);
        this.y = v.valueAt(1);
        this.z = v.valueAt(2);
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
        return 3;
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
            case 2 -> z;
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
        return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(x) || Double.isInfinite(y) || Double.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to add to this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 add(double s) {
        return new Vector3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 subtract(double s) {
        return new Vector3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 multiply(double s) {
        return new Vector3(x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector3 divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector3(x * i, y * i, z * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector3 add(Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector3 subtract(Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the cross product between
     * @return The cross product between this vector and the provided vector {@code v}
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public Vector3 cross(Vector3 v) {
        return new Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 abs() {
        return new Vector3(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 round() {
        return new Vector3(Math.round(x), Math.round(y), Math.round(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 negate() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Vector3 normalize() throws ArithmeticException {
        double s = Math.sqrt(x * x + y * y + z * z);
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Vector3(x * i, y * i, z * i);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Override
    public Vector3 min(Vector3 v) {
        return new Vector3(
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
     */
    @Override
    public Vector3 max(Vector3 v) {
        return new Vector3(
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
    public Vector3 clamp(Vector3 min, Vector3 max) {
        return new Vector3(
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
    public Vector3 map(DoubleUnaryOperator mapper) {
        return new Vector3(
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
    public Vector3 merge(Vector3 v, DoubleBinaryOperator merger) {
        return new Vector3(
                merger.applyAsDouble(x, v.x),
                merger.applyAsDouble(y, v.y),
                merger.applyAsDouble(z, v.z)
        );
    }

    /**
     * Rotates this vector by the provided rotation quaternion {@code q}.
     *
     * @param q The rotation quaternion to apply to this vector
     * @return The rotated vector
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    public Vector3 rotate(Quaternion q) {
        return Tensors.rotate(this, q);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distance(Vector3 v) {
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceSquared(Vector3 v) {
        double dx = x - v.x;
        double dy = y - v.y;
        double dz = z - v.z;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double distanceManhattan(Vector3 v) {
        double ax = Math.abs(x - v.x);
        double ay = Math.abs(y - v.y);
        double az = Math.abs(z - v.z);

        return ax + ay + az;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return DoubleStream.of(x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return new double[]{x, y, z};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
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
        if (v.size() != 3) return false;
        return x == v.valueAt(0) && y == v.valueAt(1) && z == v.valueAt(2);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}
