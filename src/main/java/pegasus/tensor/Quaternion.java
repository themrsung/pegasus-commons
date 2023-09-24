package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.io.Serial;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * An immutable complex number with one real component and three imaginary components.
 * Conversion to and from axis/angle notation follows the right-handed coordinate system,
 * and conversion to and from Euler angles is performed in the following manner.
 * <ul>
 *     <li>Pitch is defined as counter-clockwise rotation along the X axis</li>
 *     <li>Yaw is defined as counter-clockwise rotation along the Y axis</li>
 *     <li>Roll is defined as counter-clockwise rotation along the Z axis</li>
 * </ul>
 * The identity quaternion corresponds to no rotation along {@link Vector3#POSITIVE_Y
 * the positive Y axis}, and Euler angles of {@code 0, 0, 0}.
 *
 * @see Tensor
 * @see Vector
 * @see Vector1
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
 */
public class Quaternion implements Vector<Quaternion> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The identity quaternion.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    /**
     * Creates a new rotation quaternion from Euler angle representation. This method
     * uses the right-handed coordinate system.
     *
     * @param pitch The pitch of the rotation in radians (rotation along X axis)
     * @param yaw   The yaw of the rotation in radians (rotation along Y axis)
     * @param roll  The roll of the rotation in radians (rotation along Z axis)
     * @return The constructed quaternion
     */
    public static Quaternion from(double pitch, double yaw, double roll) {
        double halfRoll = roll * 0.5;
        double sinHalfRoll = Math.sin(halfRoll);
        double cosHalfRoll = Math.cos(halfRoll);

        double halfYaw = yaw * 0.5;
        double sinHalfYaw = Math.sin(halfYaw);
        double cosHalfYaw = Math.cos(halfYaw);

        double halfPitch = pitch * 0.5;
        double sinHalfPitch = Math.sin(halfPitch);
        double negatedSinHalfPitch = -sinHalfPitch;
        double cosHalfPitch = Math.cos(halfPitch);

        double crcy = cosHalfRoll * cosHalfYaw;
        double srsy = -sinHalfRoll * -sinHalfYaw;
        double crsy = cosHalfRoll * -sinHalfYaw;
        double srcy = sinHalfRoll * cosHalfYaw;

        return new Quaternion(
                crcy * cosHalfPitch - srsy * negatedSinHalfPitch,
                crcy * negatedSinHalfPitch + srsy * cosHalfPitch,
                crsy * cosHalfPitch + srcy * negatedSinHalfPitch,
                -crsy * negatedSinHalfPitch + srcy * cosHalfPitch
        );
    }

    /**
     * Creates a new rotation quaternion from an axis/angle notation. This method uses
     * the right-handed coordinate system.
     *
     * @param axis  The axis of rotation as a unit vector
     * @param angle The angle of rotation in radians
     * @return The constructed quaternion
     * @throws NullPointerException When the provided axis is {@code null}
     */
    public static Quaternion from(Vector3 axis, double angle) {
        double halfAngle = angle * 0.5;
        double sinHalfAngle = Math.sin(halfAngle);

        double w = Math.cos(halfAngle);
        double x = -axis.x * sinHalfAngle; // Inversion is required
        double y = -axis.y * sinHalfAngle; // Inversion is required
        double z = axis.z * sinHalfAngle;

        return new Quaternion(w, x, y, z);
    }

    /**
     * Creates a new quaternion.
     *
     * @param w The W component of this quaternion
     * @param x The X component of this quaternion
     * @param y The Y component of this quaternion
     * @param z The Z component of this quaternion
     */
    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new quaternion.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 4}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Quaternion(Vector<?> v) {
        if (v.size() != 4) {
            throw new IllegalArgumentException("The provided vector's size is not 4.");
        }

        this.w = v.valueAt(0);
        this.x = v.valueAt(1);
        this.y = v.valueAt(2);
        this.z = v.valueAt(3);
    }

    /**
     * The W component of this quaternion.
     */
    protected final double w;

    /**
     * The X component of this quaternion.
     */
    protected final double x;

    /**
     * The Y component of this quaternion.
     */
    protected final double y;

    /**
     * The Z component of this quaternion.
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
     * @param i The index of the component to get the value of
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
     * Returns the W component (the scalar component) of this quaternion.
     *
     * @return The W component of this quaternion
     */
    public double w() {
        return w;
    }

    /**
     * Returns the X component of this quaternion.
     *
     * @return The X component of this quaternion
     */
    public double x() {
        return x;
    }

    /**
     * Returns the Y component of this quaternion.
     *
     * @return The Y component of this quaternion
     */
    public double y() {
        return y;
    }

    /**
     * Returns the Z component of this quaternion.
     *
     * @return The Z component of this quaternion
     */
    public double z() {
        return z;
    }

    /**
     * Returns the imaginary part (the vector part) of this quaternion.
     *
     * @return The imaginary part of this quaternion
     */
    public Vector3 imaginary() {
        return new Vector3(x, y, z);
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
     * Returns the Euclidean norm of this quaternion.
     *
     * @return The Euclidean norm of this quaternion
     */
    @Override
    public double norm() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * Returns the squared Euclidean norm of this quaternion.
     *
     * @return The squared Euclidean norm of this quaternion
     */
    @Override
    public double normSquared() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * Returns the Manhattan norm of this quaternion.
     *
     * @return The Manhattan norm of this quaternion
     */
    @Override
    public double normManhattan() {
        return Math.abs(w) + Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /**
     * Returns the pitch of this quaternion. (angle of counter-clockwise rotation along the X axis)
     *
     * @return The pitch of this quaternion in radians
     */
    public double pitch() {
        double sinHalfPitch = 2 * (w * x - y * z);
        double cosHalfPitch = 1 - 2 * (x * x + y * y);
        return -Math.atan2(sinHalfPitch, cosHalfPitch);
    }

    /**
     * Returns the yaw of this quaternion. (angle of counter-clockwise rotation along the Y axis)
     *
     * @return The yaw of this quaternion in radians
     */
    public double yaw() {
        double sinHalfYaw = 2 * (w * y + x * z);
        double cosHalfYaw = 1 - 2 * (y * y + z * z);
        return -Math.atan2(sinHalfYaw, cosHalfYaw);
    }

    /**
     * Returns the roll of this quaternion. (angle of counter-clockwise rotation along the Z axis)
     *
     * @return The roll of this quaternion in radians
     */
    public double roll() {
        double sinHalfRoll = 2 * (w * z - x * y);
        double cosHalfRoll = 1 - 2 * (z * z + y * y);
        return Math.atan2(sinHalfRoll, cosHalfRoll);
    }

    /**
     * Returns the axis of rotation of this quaternion. This method fallbacks to {@link Vector3#POSITIVE_Y
     * the positive Y qxis} when this quaternion represents no rotation.
     *
     * @return The axis of rotation of this quaternion
     */
    public Vector3 axis() {
        if (Math.abs(w) >= 1) {
            return Vector3.POSITIVE_Y; // Fallback to POSITIVE_Y for the identity quaternion
        }

        double sinHalfAngle = Math.sqrt(x * x + y * y + z * z);
        double angle = 2 * Math.atan2(sinHalfAngle, w);

        if (angle == 0) {
            return Vector3.POSITIVE_Y; // Fallback to POSITIVE_Y for zero angle
        }

        double inv = 1 / sinHalfAngle;
        return new Vector3(-x * inv, -y * inv, z * inv);
    }

    /**
     * Returns the angle of rotation of this quaternion.
     *
     * @return The angle of rotation of this quaternion in radians
     */
    public double angle() {
        if (Math.abs(w) >= 1) {
            return 0; // Angle is zero for the identity quaternion
        }

        double sinHalfAngle = Math.sqrt(x * x + y * y + z * z);
        return 2 * Math.atan2(sinHalfAngle, w);
    }

    /**
     * Returns the direction this rotation quaternion is facing. In other words, this is the
     * result of rotating the {@link Vector3#POSITIVE_Z positive Z unit vector} by this quaternion.
     * When this is the identity quaternion, this will return the zero vector.
     *
     * @return The direction this quaternion is facing
     */
    public Vector3 direction() {
        if (Math.abs(w) >= 1) {
            return Vector3.ZERO;
        }

        double minusX = -x;
        double minusZ = -z;

        return new Vector3(
                (2 * minusX * minusZ) + (2 * w * y),
                (2 * w * minusX) - (2 * y * minusZ),
                w * w - minusX * minusX - y * y + minusZ * minusZ
        );
    }

    /**
     * Adds a scalar to this quaternion, then returns the resulting quaternion.
     *
     * @param s The scalar of which to add to this quaternion
     * @return The resulting quaternion
     */
    @Override
    public Quaternion add(double s) {
        return new Quaternion(w + s, x, y, z);
    }

    /**
     * Subtracts a scalar from this quaternion, then returns the resulting quaternion.
     *
     * @param s The scalar of which to subtract from this quaternion
     * @return The resulting quaternion
     */
    @Override
    public Quaternion subtract(double s) {
        return new Quaternion(w - s, x, y, z);
    }

    /**
     * Multiplies this quaternion by a scalar, then returns the resulting quaternion. Note that this
     * is different from {@link #scale(double) scaling} this quaternion.
     *
     * @param s The scalar of which to multiply this quaternion by
     * @return The resulting quaternion
     */
    @Override
    public Quaternion multiply(double s) {
        return new Quaternion(w * s, x * s, y * s, z * s);
    }

    /**
     * Scales the rotation of this quaternion, then returns the resulting quaternion. Note that this
     * is different from {@link #multiply(double) multiplication}.
     *
     * @param s The scalar of which to apply to the rotational angle of this quaternion
     * @return The scaled quaternion
     */
    public Quaternion scale(double s) {
        if (w >= 1) {
            return IDENTITY;
        }

        double halfAngle = Math.acos(w);
        double newHalfAngle = halfAngle * s;

        double sinHalfAngle = Math.sin(halfAngle);
        double sinNewHalfAngle = Math.sin(newHalfAngle);

        double scaleFactor = sinNewHalfAngle / sinHalfAngle;

        return new Quaternion(
                Math.cos(newHalfAngle),
                x * scaleFactor,
                y * scaleFactor,
                z * scaleFactor
        );
    }

    /**
     * Divides this quaternion by a scalar, then returns the resulting quaternion.
     *
     * @param s The scalar of which to divide this quaternion by
     * @return The resulting quaternion
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public Quaternion divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Quaternion(w * i, x * i, y * i, z * i);
    }

    /**
     * Adds a quaternion to this quaternion, then returns the resulting quaternion.
     *
     * @param q The quaternion of which to add to this quaternion
     * @return The resulting quaternion
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public Quaternion add(Quaternion q) {
        return new Quaternion(w + q.w, x + q.x, y + q.y, z + q.z);
    }

    /**
     * Subtracts a quaternion from this quaternion, then returns the resulting quaternion.
     *
     * @param q The quaternion of which to subtract from this quaternion
     * @return The resulting quaternion
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public Quaternion subtract(Quaternion q) {
        return new Quaternion(w - q.w, x - q.x, y - q.y, z - q.z);
    }

    /**
     * Multiplies this quaternion by the provided quaternion {@code q}, then returns the resulting
     * quaternion. This performs quaternion left-multiplication where this quaternion is on the left,
     * and the provided quaternion {@code q} is on the right.
     *
     * @param q The quaternion of which to multiply this quaternion by
     * @return The resulting quaternion
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    public Quaternion multiply(Quaternion q) {
        return new Quaternion(
                w * q.w - x * q.x - y * q.y - z * q.z,
                w * q.x + x * q.w + y * q.z - z * q.y,
                w * q.y - x * q.z + y * q.w + z * q.x,
                w * q.z + x * q.y - y * q.x + z * q.w
        );
    }

    /**
     * Returns the dot product between this quaternion and the provided quaternion {@code q}.
     *
     * @param q The quaternion of which to get the dot product between
     * @return The dot product between this quaternion and the provided quaternion {@code q}
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public double dot(Quaternion q) {
        return w * q.w + x * q.x + y * q.y + z * q.z;
    }

    /**
     * Returns the absolute value of this quaternion.
     *
     * @return The absolute value of this quaternion
     */
    @Override
    public Quaternion abs() {
        return new Quaternion(Math.abs(w), Math.abs(x), Math.abs(y), Math.abs(z));
    }

    /**
     * Returns the rounded value of this quaternion.
     *
     * @return The rounded value of this quaternion
     */
    @Override
    public Quaternion round() {
        return new Quaternion(Math.round(w), Math.round(x), Math.round(y), Math.round(z));
    }

    /**
     * Returns the negation of this quaternion.
     *
     * @return The negation of this quaternion
     */
    @Override
    public Quaternion negate() {
        return new Quaternion(-w, -x, -y, -z);
    }

    /**
     * Returns the normalized value of this quaternion.
     *
     * @return The normalized value of this quaternion
     * @throws ArithmeticException When the Euclidean norm of this quaternion is zero
     */
    @Override
    public Quaternion normalize() throws ArithmeticException {
        double s = Math.sqrt(w * w + x * x + y * y + z * z);
        if (s == 0) throw new DivisionByZeroException();
        double i = 1 / s;
        return new Quaternion(w * i, x * i, y * i, z * i);
    }

    /**
     * Returns the conjugate of this quaternion.
     *
     * @return The conjugate of this quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Returns the multiplicative inverse of this quaternion.
     *
     * @return The multiplicative inverse of this quaternion
     * @throws ArithmeticException When the squared Euclidean norm of this quaternion is zero
     */
    public Quaternion inverse() throws ArithmeticException {
        double n2 = w * w + x * x + y * y + z * z;
        if (n2 == 0) throw new DivisionByZeroException();
        double i = 1 / n2;
        return new Quaternion(w * i, -x * i, -y * i, -z * i);
    }

    /**
     * Returns the minimum quaternion between this quaternion and the provided quaternion {@code q}.
     *
     * @param q The boundary quaternion of which to compare to
     * @return The minimum quaternion
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public Quaternion min(Quaternion q) {
        return new Quaternion(
                Math.min(w, q.w),
                Math.min(x, q.x),
                Math.min(y, q.y),
                Math.min(z, q.z)
        );
    }

    /**
     * Returns the maximum quaternion between this quaternion and the provided quaternion {@code q}.
     *
     * @param q The boundary quaternion of which to compare to
     * @return The maximum quaternion
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public Quaternion max(Quaternion q) {
        return new Quaternion(
                Math.max(w, q.w),
                Math.max(x, q.x),
                Math.max(y, q.y),
                Math.max(z, q.z)
        );
    }

    /**
     * Clamps this quaternion component-wise to the range of {@code [min, max]}, then returns the
     * clamped quaternion.
     *
     * @param min The minimum boundary quaternion of which to compare to
     * @param max The maximum boundary quaternion of which to compare to
     * @return The clamped quaternion
     * @throws NullPointerException When either of the provided boundary quaternions is {@code null}
     */
    @Override
    public Quaternion clamp(Quaternion min, Quaternion max) {
        return new Quaternion(
                Math.min(Math.max(w, min.w), max.w),
                Math.min(Math.max(x, min.x), max.x),
                Math.min(Math.max(y, min.y), max.y),
                Math.min(Math.max(z, min.z), max.z)
        );
    }

    /**
     * Applies the provided mapper function to each component of this quaternion, then returns a new
     * quaternion whose components are populated from that of the return values of the provided mapper
     * function.
     *
     * @param mapper The mapper function of which to apply to each component of this quaternion
     * @return The resulting quaternion
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    @Override
    public Quaternion map(DoubleUnaryOperator mapper) {
        return new Quaternion(
                mapper.applyAsDouble(w),
                mapper.applyAsDouble(x),
                mapper.applyAsDouble(y),
                mapper.applyAsDouble(z)
        );
    }

    /**
     * Applies the provided merger function to each corresponding pair of components between this
     * quaternion and the provided quaternion {@code q}, then returns a new quaternion whose components
     * are populated from that of the return values of the provided merger function.
     *
     * @param q      The quaternion of which to merge this quaternion with
     * @param merger The merger function of which to handle the merging of the two quaternions
     * @return The resulting quaternion
     * @throws NullPointerException When either the provided quaternion {@code q} or the provided
     *                              merger function is {@code null}
     */
    @Override
    public Quaternion merge(Quaternion q, DoubleBinaryOperator merger) {
        return new Quaternion(
                merger.applyAsDouble(w, q.w),
                merger.applyAsDouble(x, q.x),
                merger.applyAsDouble(y, q.y),
                merger.applyAsDouble(z, q.z)
        );
    }

    /**
     * Returns the Euclidean distance to the provided quaternion {@code q}.
     *
     * @param q The quaternion of which to get the Euclidean distance to
     * @return The Euclidean distance to the provided quaternion {@code q}
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public double distance(Quaternion q) {
        double dw = w - q.w;
        double dx = x - q.x;
        double dy = y - q.y;
        double dz = z - q.z;

        return Math.sqrt(dw * dw + dx * dx + dy * dy + dz * dz);
    }

    /**
     * Returns the squared Euclidean distance to the provided quaternion {@code q}.
     *
     * @param q The quaternion of which to get the squared Euclidean distance to
     * @return The squared Euclidean distance to the provided quaternion {@code q}
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public double distanceSquared(Quaternion q) {
        double dw = w - q.w;
        double dx = x - q.x;
        double dy = y - q.y;
        double dz = z - q.z;

        return dw * dw + dx * dx + dy * dy + dz * dz;
    }

    /**
     * Returns the Manhattan distance to the provided quaternion {@code q}.
     *
     * @param q The quaternion of which to get the Manhattan distance to
     * @return The Manhattan distance to the provided quaternion {@code q}
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    @Override
    public double distanceManhattan(Quaternion q) {
        double aw = Math.abs(w - q.w);
        double ax = Math.abs(x - q.x);
        double ay = Math.abs(y - q.y);
        double az = Math.abs(z - q.z);

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
     * Converts this quaternion into a {@code 3x3} rotation matrix, then returns
     * the converted matrix.
     *
     * @return The rotation matrix representation of this quaternion
     */
    public Matrix toRotationMatrix() {
        double xx = x * x;
        double xy = x * y;
        double xz = x * z;
        double xw = x * w;

        double yy = y * y;
        double yz = y * z;
        double yw = y * w;

        double zz = z * z;
        double zw = z * w;

        double[] values = {
                1 - 2 * (yy + zz),
                2 * (xy - zw),
                2 * (xz + yw),
                2 * (xy + zw),
                1 - 2 * (xx + zz),
                2 * (yz - xw),
                2 * (xz - yw),
                2 * (yz + xw),
                1 - 2 * (xx + yy)
        };

        return new Matrix(3, 3, (r, c) -> values[r * 3 + c]);
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
