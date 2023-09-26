package pegasus.tensor;

import pegasus.container.DoubleContainer;
import pegasus.exception.IllegalInstanceException;
import pegasus.pointer.DoublePointer;
import pegasus.tuple.DoubleTuple;
import pegasus.tuple.Tuple;

import java.util.Arrays;

/**
 * Contains tensor utilities.
 */
public final class Tensors {
    //
    // Constants
    //

    /**
     * A very small constant used in various applications.
     */
    public static final double EPSILON = 1e-6;

    /**
     * The mathematical constant &pi;.
     */
    public static final double PI = 3.141592653589793;

    /**
     * The mathematical constant &tau;.
     */
    public static final double TAU = 2 * PI;

    //
    // Scalar Mathematics
    //

    /**
     * Returns a random value within the range of {@code [0, 1)}.
     *
     * @return A random value between {@code [0, 1)}
     */
    public static double random() {
        return Math.random();
    }

    /**
     * Returns a random value within the range of {@code [min, max)}.
     *
     * @param min The minimum allowed value (inclusive)
     * @param max The maximum allowed value (exclusive)
     * @return A random value between {@code [min, max)}
     */
    public static double random(double min, double max) {
        return min + random() * (max - min);
    }

    /**
     * Returns whether the provided value {@code val} is within the range of {@code [min, max]}.
     *
     * @param val The value to validate
     * @param min The minimum allowed value (inclusive)
     * @param max The maximum allowed value (inclusive)
     * @return {@code true} if the value is within the allowed range
     */
    public static boolean isInRange(double val, double min, double max) {
        return val >= min && val <= max;
    }

    /**
     * Clamps the provided value to respect the boundaries of {@code [min, max]}.
     *
     * @param val The value of which to clamp
     * @param min The minimum allowed value (inclusive)
     * @param max The maximum allowed value (inclusive)
     * @return The clamped value
     */
    public static double clamp(double val, double min, double max) {
        if (val > max) return max;
        return Math.max(val, min);
    }

    /**
     * Returns the minimum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The minimum value of the provided values
     */
    public static double min(double v1, double v2) {
        return Math.min(v1, v2);
    }

    /**
     * Returns the minimum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The minimum value of the provided values
     */
    public static double min(double v1, double v2, double v3) {
        return Math.min(Math.min(v1, v2), v3);
    }

    /**
     * Returns the minimum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @param v4 The fourth value to compare
     * @return The minimum value of the provided values
     */
    public static double min(double v1, double v2, double v3, double v4) {
        return Math.min(Math.min(Math.min(v1, v2), v3), v4);
    }

    /**
     * Returns the minimum value of the provided values.
     *
     * @param values The values to compare
     * @return The minimum value of the provided values
     * @throws NullPointerException When the provided array is {@code null}
     */
    public static double min(double... values) {
        return Arrays.stream(values).reduce(Double.MAX_VALUE, Math::min);
    }

    /**
     * Returns the maximum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The maximum value of the provided values
     */
    public static double max(double v1, double v2) {
        return Math.max(v1, v2);
    }

    /**
     * Returns the maximum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The maximum value of the provided values
     */
    public static double max(double v1, double v2, double v3) {
        return Math.max(Math.max(v1, v2), v3);
    }

    /**
     * Returns the maximum value of the provided values.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @param v4 The fourth value to compare
     * @return The maximum value of the provided values
     */
    public static double max(double v1, double v2, double v3, double v4) {
        return Math.max(Math.max(Math.max(v1, v2), v3), v4);
    }

    /**
     * Returns the maximum value of the provided values.
     *
     * @param values The values to compare
     * @return The maximum value of the provided values
     * @throws NullPointerException When the provided array is {@code null}
     */
    public static double max(double... values) {
        return Arrays.stream(values).reduce(-Double.MAX_VALUE, Math::max);
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     */
    public static double lerp(double s, double e, double t) {
        return s + (e - s) * t;
    }

    /**
     * Performs cubic Bezier interpolation between four scalar values,
     * using the interpolation parameter {@code t}.
     *
     * @param p0 The starting scalar value
     * @param p1 The first control point scalar value
     * @param p2 The second control point scalar value
     * @param p3 The ending scalar value
     * @param t  The interpolation parameter ({@code [0, 1]})
     * @return The interpolated scalar value
     */
    public static double bezier(double p0, double p1, double p2, double p3, double t) {
        final double u = 1.0 - t;
        final double tt = t * t;
        final double uu = u * u;
        final double uuu = uu * u;
        final double ttt = tt * t;

        double result = uuu * p0;
        result += 3 * uu * t * p1;
        result += 3 * u * tt * p2;
        result += ttt * p3;

        return result;
    }

    // Lanczos parameters
    private static final double LANCZOS_G = 7;
    private static final double[] LANCZOS_COEFFICIENTS = {
            0.99999999999980993,
            676.5203681218851,
            -1259.1392167224028,
            771.32342877765313,
            -176.61502916214059,
            12.507343278686905,
            -0.13857109526572012,
            9.9843695780195716e-6,
            1.5056327351493116e-7
    };

    /**
     * Returns the gamma of the given input {@code n}.
     *
     * @param n The value to get the gamma of
     * @return The gamma function value of {@code n}
     */
    public static double gamma(double n) {
        // Copy value for manipulation
        double x = n;

        if (x <= 0.5) {
            return Math.PI / (Math.sin(Math.PI * x) * gamma(1 - x));
        }

        x -= 1;
        double result = LANCZOS_COEFFICIENTS[0];
        for (int i = 1; i < LANCZOS_COEFFICIENTS.length; i++) {
            result += LANCZOS_COEFFICIENTS[i] / (x + i);
        }

        final double t = x + LANCZOS_G + 0.5;
        return Math.sqrt(2 * Math.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * result;
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static double factorial(double n) {
        // Estimate factorial using the Gamma function
        return gamma(n + 1);
    }

    /**
     * Calculates the exact integer factorial of the provided input {@code n}.
     * Note that this requires a non-negative input. Values over {@code 20} will trigger
     * an integer overflow.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static long factorialExact(long n) {
        return n < FACTORIALS.length ? FACTORIALS[(int) n] : (long) factorial((double) n);
    }

    // The pre-computed table of integer factorials.
    private static final long[] FACTORIALS = {
            1L,
            1L,
            2L,
            6L,
            24L,
            120L,
            720L,
            5040L,
            40320L,
            362880L,
            3628800L,
            39916800L,
            479001600L,
            6227020800L,
            87178291200L,
            1307674368000L,
            20922789888000L,
            355687428096000L,
            6402373705728000L,
            121645100408832000L,
            2432902008176640000L
    };

    /**
     * Rotates the provided vector {@code v} by the provided rotation quaternion {@code q}.
     *
     * @param v The vector of which to rotate
     * @param q The rotation quaternion of which to apply to the provided vector {@code v}
     * @return The rotated vector
     * @throws NullPointerException When either the provided vector {@code v} or the provided
     *                              rotation quaternion {@code q} is {@code null}
     */
    public static Vector3 rotate(Vector3 v, Quaternion q) {

        /*
         * This is a simplified implementation of qpq' avoiding intermediary instantiation.
         */

        double qp1 = -q.x * v.x - q.y * v.y - q.z * v.z;
        double qp2 = q.w * v.x + q.y * v.z - q.z * v.y;
        double qp3 = q.w * v.y - q.x * v.z + q.z * v.x;
        double qp4 = q.w * v.z + q.x * v.y - q.y * v.x;

        double c1 = q.w;
        double c2 = -q.x;
        double c3 = -q.y;
        double c4 = -q.z;

        return new Vector3(
                qp1 * c2 + qp2 * c1 + qp3 * c4 - qp4 * c3,
                qp1 * c3 - qp2 * c4 + qp3 * c1 + qp4 * c2,
                qp1 * c4 + qp2 * c3 - qp3 * c2 + qp4 * c1
        );
    }

    /**
     * Transforms the source vector into an absolute coordinate system.
     *
     * @param src      The source vector of which to transform
     * @param rotation The rotation of the source vector
     * @param offset   The offset of the source vector relative to the absolute origin
     * @return The transformed vector
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Vector3 toAbsolute(Vector3 src, Quaternion rotation, Vector3 offset) {
        double qp1 = -rotation.x * src.x - rotation.y * src.y - rotation.z * src.z;
        double qp2 = rotation.w * src.x + rotation.y * src.z - rotation.z * src.y;
        double qp3 = rotation.w * src.y - rotation.x * src.z + rotation.z * src.x;
        double qp4 = rotation.w * src.z + rotation.x * src.y - rotation.y * src.x;

        double c1 = rotation.w;
        double c2 = -rotation.x;
        double c3 = -rotation.y;
        double c4 = -rotation.z;

        double x = qp1 * c2 + qp2 * c1 + qp3 * c4 - qp4 * c3 + offset.x;
        double y = qp1 * c3 - qp2 * c4 + qp3 * c1 + qp4 * c2 + offset.y;
        double z = qp1 * c4 + qp2 * c3 - qp3 * c2 + qp4 * c1 + offset.z;

        return new Vector3(x, y, z);
    }

    /**
     * Transforms the source vector into a relative coordinate system.
     *
     * @param src    THe source vector of which to transform
     * @param origin The new origin of the vector relative to the absolute origin
     * @param angle  The angle of rotation to apply
     * @return The transformed vector
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Vector3 toRelative(Vector3 src, Vector3 origin, Quaternion angle) {
        double vx = src.x + origin.x;
        double vy = src.y + origin.y;
        double vz = src.z + origin.z;

        double qp1 = -angle.x * vx - angle.y * vy - angle.z * vz;
        double qp2 = angle.w * vx + angle.y * vz - angle.z * vy;
        double qp3 = angle.w * vy - angle.x * vz + angle.z * vx;
        double qp4 = angle.w * vz + angle.x * vy - angle.y * vx;

        double c1 = angle.w;
        double c2 = -angle.x;
        double c3 = -angle.y;
        double c4 = -angle.z;

        return new Vector3(
                qp1 * c2 + qp2 * c1 + qp3 * c4 - qp4 * c3,
                qp1 * c3 - qp2 * c4 + qp3 * c1 + qp4 * c2,
                qp1 * c4 + qp2 * c3 - qp3 * c2 + qp4 * c1
        );
    }

    //
    // Randomization
    //

    /**
     * Returns a random unit vector. (a vector with a Euclidean norm of {@code 1})
     *
     * @return A random unit vector
     */
    public static Vector2 random2() {
        try {
            return new Vector2(random(-1, 1), random(-1, 1)).normalize();
        } catch (ArithmeticException e) {
            return Vector2.POSITIVE_X;
        }
    }

    /**
     * Returns a random unit vector. (a vector with a Euclidean norm of {@code 1})
     *
     * @return A random unit vector
     */
    public static Vector3 random3() {
        try {
            return new Vector3(
                    random(-1, 1),
                    random(-1, 1),
                    random(-1, 1)
            ).normalize();
        } catch (ArithmeticException e) {
            return Vector3.POSITIVE_X;
        }
    }

    /**
     * Returns a random unit vector. (a vector with a Euclidean norm of {@code 1})
     *
     * @return A random unit vector
     */
    public static Vector4 random4() {
        try {
            return new Vector4(
                    random(-1, 1),
                    random(-1, 1),
                    random(-1, 1),
                    random(-1, 1)
            ).normalize();
        } catch (ArithmeticException e) {
            return Vector4.POSITIVE_W;
        }
    }

    /**
     * Returns a random rotation quaternion.
     *
     * @return A random rotation quaternion
     */
    public static Quaternion randomQuaternion() {
        try {
            return new Quaternion(
                    random(-1, 1),
                    random(-1, 1),
                    random(-1, 1),
                    random(-1, 1)
            ).normalize();
        } catch (ArithmeticException e) {
            return Quaternion.IDENTITY;
        }
    }

    //
    // Range Validation
    //

    /**
     * Checks if the provided value {@code val} is within the range of {@code [min, max]}.
     *
     * @param val The value to compare
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param <V> The type of vector to use for this operation
     * @return {@code true} if the value is within the range of {@code [min, max]}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static <V extends Vector<?>> boolean isInRange(V val, V min, V max) {
        if (val.size() != min.size() || min.size() != max.size()) return false;

        for (int i = 0; i < val.size(); i++) {
            if (!isInRange(val.valueAt(i), min.valueAt(i), max.valueAt(i))) return false;
        }

        return true;
    }

    /**
     * Checks if the provided value {@code val} is within the range of {@code [min, max]}.
     *
     * @param val The value to compare
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return {@code true} if the value is within the range of {@code [min, max]}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static boolean isInRange(Vector2 val, Vector2 min, Vector2 max) {
        return isInRange(val.x, min.x, max.x) &&
                isInRange(val.y, min.y, max.y);
    }

    /**
     * Checks if the provided value {@code val} is within the range of {@code [min, max]}.
     *
     * @param val The value to compare
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return {@code true} if the value is within the range of {@code [min, max]}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static boolean isInRange(Vector3 val, Vector3 min, Vector3 max) {
        return isInRange(val.x, min.x, max.x) &&
                isInRange(val.y, min.y, max.y) &&
                isInRange(val.z, min.z, max.z);
    }

    /**
     * Checks if the provided value {@code val} is within the range of {@code [min, max]}.
     *
     * @param val The value to compare
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return {@code true} if the value is within the range of {@code [min, max]}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static boolean isInRange(Vector4 val, Vector4 min, Vector4 max) {
        return isInRange(val.w, min.w, max.w) &&
                isInRange(val.x, min.x, max.x) &&
                isInRange(val.y, min.y, max.y) &&
                isInRange(val.z, min.z, max.z);
    }

    //
    // Vector Arithmetic
    //

    /**
     * Returns the sum of the two values.
     *
     * @param v1 The first value to add
     * @param v2 The second value to add
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 sum(Vector2 v1, Vector2 v2) {
        return v1.add(v2);
    }

    /**
     * Returns the sum of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 sum(Vector2... values) {
        double x = 0, y = 0;
        for (Vector2 value : values) {
            x += value.x();
            y += value.y();
        }
        return new Vector2(x, y);
    }

    /**
     * Returns the sum of the two values.
     *
     * @param v1 The first value to add
     * @param v2 The second value to add
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 sum(Vector3 v1, Vector3 v2) {
        return v1.add(v2);
    }

    /**
     * Returns the sum of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 sum(Vector3... values) {
        double x = 0, y = 0, z = 0;
        for (Vector3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }
        return new Vector3(x, y, z);
    }

    /**
     * Returns the sum of the two values.
     *
     * @param v1 The first value to add
     * @param v2 The second value to add
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 sum(Vector4 v1, Vector4 v2) {
        return v1.add(v2);
    }

    /**
     * Returns the sum of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 sum(Vector4... values) {
        double w = 0, x = 0, y = 0, z = 0;
        for (Vector4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }
        return new Vector4(w, x, y, z);
    }

    /**
     * Returns the simple average of the two vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param v1 The first vector to average
     * @param v2 The second vector to average
     * @return The simple average of the two vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 avg(Vector2 v1, Vector2 v2) {
        return v1.add(v2).divide(2);
    }

    /**
     * Returns the simple average of the provided vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param values The vectors to average
     * @return The simple average of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     * @throws ArithmeticException  When the array's length is zero
     */

    public static Vector2 avg(Vector2... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the simple average of the two vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param v1 The first vector to average
     * @param v2 The second vector to average
     * @return The simple average of the two vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 avg(Vector3 v1, Vector3 v2) {
        return v1.add(v2).divide(2);
    }

    /**
     * Returns the simple average of the provided vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param values The vectors to average
     * @return The simple average of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     * @throws ArithmeticException  When the array's length is zero
     */

    public static Vector3 avg(Vector3... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the simple average of the two vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param v1 The first vector to average
     * @param v2 The second vector to average
     * @return The simple average of the two vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 avg(Vector4 v1, Vector4 v2) {
        return v1.add(v2).divide(2);
    }

    /**
     * Returns the simple average of the provided vectors.
     * This is equivalent to the centroid in a geometric context.
     *
     * @param values The vectors to average
     * @throws NullPointerException When a {@code null} parameter is provided
     * @throws ArithmeticException  When the array's length is zero
     */

    public static Vector4 avg(Vector4... values) {
        return sum(values).divide(values.length);
    }

    //
    // Linear Interpolation (LERP)
    //

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static <V extends Vector<V>> V lerp(V s, V e, double t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 lerp(Vector2 s, Vector2 e, double t) {
        final double s1 = s.x;
        final double e1 = e.x;
        final double s2 = s.y;
        final double e2 = e.y;

        return new Vector2(
                s1 + (e1 - s1) * t,
                s2 + (e2 - s2) * t
        );
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 lerp(Vector3 s, Vector3 e, double t) {
        final double s1 = s.x;
        final double e1 = e.x;
        final double s2 = s.y;
        final double e2 = e.y;
        final double s3 = s.z;
        final double e3 = e.z;

        return new Vector3(
                s1 + (e1 - s1) * t,
                s2 + (e2 - s2) * t,
                s3 + (e3 - s3) * t
        );
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 lerp(Vector4 s, Vector4 e, double t) {
        final double s1 = s.w;
        final double e1 = e.w;
        final double s2 = s.x;
        final double e2 = e.x;
        final double s3 = s.y;
        final double e3 = e.y;
        final double s4 = s.z;
        final double e4 = e.z;

        return new Vector4(
                s1 + (e1 - s1) * t,
                s2 + (e2 - s2) * t,
                s3 + (e3 - s3) * t,
                s4 + (e4 - s4) * t
        );
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s} and the ending value {@code e}.
     *
     * @param s The starting value
     * @param e The ending value
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated value between the starting and ending values {@code s} and {@code e}
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Quaternion lerp(Quaternion s, Quaternion e, double t) {
        final double s1 = s.w;
        final double e1 = e.w;
        final double s2 = s.x;
        final double e2 = e.x;
        final double s3 = s.y;
        final double e3 = e.y;
        final double s4 = s.z;
        final double e4 = e.z;

        return new Quaternion(
                s1 + (e1 - s1) * t,
                s2 + (e2 - s2) * t,
                s3 + (e3 - s3) * t,
                s4 + (e4 - s4) * t
        );
    }

    //
    // Spherical Linear Interpolation (SLERP)
    //

    /**
     * Performs spherical linear interpolation (SLERP) between two quaternions.
     * This assumes that the input quaternions are already normalized.
     *
     * @param start The starting quaternion
     * @param end   The end quaternion
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated quaternion
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Quaternion slerp(Quaternion start, Quaternion end, double t) {
        // Get the dot product of the two quaternions
        double dot = start.dot(end);

        // Determine direction and adjust end quaternion if required
        if (dot < 0) {
            end = end.negate();
            dot = -dot;
        }

        if (1 - dot < EPSILON) {
            // Quaternions are very close, use linear interpolation
            return lerp(start, end, t);
        }

        // Calculate the angle between the quaternions
        final double theta0 = Math.acos(dot);
        final double theta1 = theta0 * t;

        // Calculate the interpolation coefficients
        final double s0 = Math.sin((1 - t) * theta0) / Math.sin(theta0);
        final double s1 = Math.sin(theta1) / Math.sin(theta0);

        // Perform spherical linear interpolation
        return start.multiply(s0).add(end.multiply(s1));
    }

    //
    // Vector Clamping
    //

    /**
     * Returns the collective minimum vector between the provided vectors.
     *
     * @param values The vectors of which to get the minimum vector of
     * @return The minimum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 min(Vector2... values) {
        double x = Double.MAX_VALUE, y = Double.MAX_VALUE;

        for (Vector2 value : values) {
            x = Math.min(x, value.x);
            y = Math.min(y, value.y);
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the collective minimum vector between the provided vectors.
     *
     * @param values The vectors of which to get the minimum vector of
     * @return The minimum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 min(Vector3... values) {
        double x = Double.MAX_VALUE, y = Double.MAX_VALUE, z = Double.MAX_VALUE;

        for (Vector3 value : values) {
            x = Math.min(x, value.x);
            y = Math.min(y, value.y);
            z = Math.min(z, value.z);
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the collective minimum vector between the provided vectors.
     *
     * @param values The vectors of which to get the minimum vector of
     * @return The minimum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 min(Vector4... values) {
        double w = Double.MAX_VALUE, x = Double.MAX_VALUE, y = Double.MAX_VALUE, z = Double.MAX_VALUE;

        for (Vector4 value : values) {
            w = Math.min(w, value.w);
            x = Math.min(x, value.x);
            y = Math.min(y, value.y);
            z = Math.min(z, value.z);
        }

        return new Vector4(w, x, y, z);
    }

    /**
     * Returns the collective maximum vector between the provided vectors.
     *
     * @param values The vectors of which to get the maximum vector of
     * @return The maximum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector2 max(Vector2... values) {
        double x = -Double.MAX_VALUE, y = -Double.MAX_VALUE;

        for (Vector2 value : values) {
            x = Math.max(x, value.x);
            y = Math.max(y, value.y);
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the collective maximum vector between the provided vectors.
     *
     * @param values The vectors of which to get the maximum vector of
     * @return The maximum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector3 max(Vector3... values) {
        double x = -Double.MAX_VALUE, y = -Double.MAX_VALUE, z = -Double.MAX_VALUE;

        for (Vector3 value : values) {
            x = Math.max(x, value.x);
            y = Math.max(y, value.y);
            z = Math.max(z, value.z);
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the collective maximum vector between the provided vectors.
     *
     * @param values The vectors of which to get the maximum vector of
     * @return The maximum vector of the provided vectors
     * @throws NullPointerException When a {@code null} parameter is provided
     */

    public static Vector4 max(Vector4... values) {
        double w = -Double.MAX_VALUE, x = -Double.MAX_VALUE, y = -Double.MAX_VALUE, z = -Double.MAX_VALUE;

        for (Vector4 value : values) {
            w = Math.max(w, value.w);
            x = Math.max(x, value.x);
            y = Math.max(y, value.y);
            z = Math.max(z, value.z);
        }

        return new Vector4(w, x, y, z);
    }

    /**
     * Translates a 3D coordinate into a 2D coordinate using the provided focal length.
     * This operation inverts the Y coordinate.
     *
     * @param v           The vector of which to translate
     * @param focalLength The focal length to use in the translation
     * @return The translated coordinate
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     * @see #to2D(Vector3, double, boolean)
     */
    public static Vector2 to2D(Vector3 v, double focalLength) {
        return new Vector2(
                (focalLength / (focalLength + v.z)) * v.x,
                (focalLength / (focalLength + v.z)) * -v.y
        );
    }

    /**
     * Translates a 3D coordinate into a 2D coordinate using the provided focal length.
     *
     * @param v           The vector of which to translate
     * @param focalLength The focal length to use in the translation
     * @param invertY     Whether to invert the Y axis
     * @return The translated coordinate
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     * @see #to2D(Vector3, double)
     */
    public static Vector2 to2D(Vector3 v, double focalLength, boolean invertY) {
        return new Vector2(
                (focalLength / (focalLength + v.z)) * v.x,
                (focalLength / (focalLength + v.z)) * (invertY ? -v.y : v.y)
        );
    }

    /**
     * Inverts the Y component of the provided vector {@code v}, then returns the resulting vector.
     *
     * @param v The vector of which to invert the Y component of
     * @return The resulting vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static Vector2 invertY(Vector2 v) {
        return new Vector2(v.x, -v.y);
    }

    /**
     * Inverts the Y component of the provided vector {@code v}, then returns the resulting vector.
     *
     * @param v The vector of which to invert the Y component of
     * @return The resulting vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static Vector3 invertY(Vector3 v) {
        return new Vector3(v.x, -v.y, v.z);
    }

    //
    // Tuple Conversion
    //

    /**
     * Converts the provided vector {@code v} into a primitive tuple of {@code double} values,
     * then returns the converted tuple.
     *
     * @param v The vector of which to convert into a tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoubleTuple toTuple(Vector<?> v) {
        return DoubleTuple.from(v.stream());
    }

    /**
     * Converts the provided vector {@code v} into tuple of boxed {@link Double} values,
     * then returns the converted tuple.
     *
     * @param v The vector of which to convert into a tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static Tuple<Double> toBoxedTuple(Vector<?> v) {
        return Tuple.from(v.stream().boxed());
    }

    /**
     * Converts the provided matrix {@code m} into a layered tuple.
     *
     * @param m The matrix of which to convert into a layered tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided matrix {@code m} is {@code null}
     */
    public static Tuple<DoubleTuple> toLayeredTuple(Matrix m) {
        DoubleTuple[] rows = new DoubleTuple[m.rows()];

        for (int r = 0; r < m.rows(); r++) {
            rows[r] = DoubleTuple.of(m.rows());
        }

        return Tuple.of(rows);
    }

    //
    // Pointer Conversion
    //

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector<?> v) {
        return DoublePointer.to(v.toArray());
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector1 v) {
        return DoublePointer.to(v.x);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector2 v) {
        return DoublePointer.to(v.x, v.y);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector3 v) {
        return DoublePointer.to(v.x, v.y, v.z);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector4 v) {
        return DoublePointer.to(v.w, v.x, v.y, v.z);
    }

    /**
     * Converts the provided quaternion {@code q} into a pointer.
     *
     * @param q The quaternion of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided quaternion {@code q} is {@code null}
     */
    public static DoublePointer toPointer(Quaternion q) {
        return DoublePointer.to(q.w, q.x, q.y, q.z);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector5 v) {
        return DoublePointer.to(v.i, v.j, v.k, v.l, v.m);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector6 v) {
        return DoublePointer.to(v.i, v.j, v.k, v.l, v.m, v.n);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector7 v) {
        return DoublePointer.to(v.i, v.j, v.k, v.l, v.m, v.n, v.o);
    }

    /**
     * Converts the provided vector {@code v} into a pointer.
     *
     * @param v The vector of which to convert into a pointer
     * @return The converted pointer
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoublePointer toPointer(Vector8 v) {
        return DoublePointer.to(v.i, v.j, v.k, v.l, v.m, v.n, v.o, v.p);
    }

    //
    // Container Conversion
    //

    /**
     * Converts the provided vector {@code v} into a container.
     *
     * @param v The vector of which to convert into a container
     * @return The converted container
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public static DoubleContainer toContainer(Vector1 v) {
        return DoubleContainer.of(v.x);
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Tensors() {
        throw new IllegalInstanceException(this);
    }
}
