package pegasus.tensor;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * A tensor of order {@code 2}. Vectors represent a one-dimensional set of scalar
 * components, which are represented by the primitive type {@code double}. All vectors
 * are immutable, and a new instance is created after each transformative operation.
 * <p>
 * A {@link #newVector(double...) factory method} is available for varargs parameters,
 * although it should not be used as a primary means of instantiation. The individual
 * constructors for final types such as {@link Vector2} or {@link Vector3} should be
 * called instead for optimal performance.
 * </p>
 * <p>
 * Sizes {@code 2-8} are component-based, meaning that the individual components are
 * declared as independent variables. (e.g. {@code x, y, z}) Sizes {@code 9} and above
 * are {@link ArrayVector array-based}, meaning the components are stored as a primitive
 * array of {@code double} values. ({@code double[]})
 * </p>
 * <p>
 * While compound operations can be achieved by method chaining, it is not recommended
 * to do so for complex operations which require several iterations, as unnecessary
 * intermediary vector instances are created in the process. Static methods such as
 * {@link Tensors#rotate(Vector3, Quaternion) quaternion-vector rotation} should be used
 * instead of chaining two quaternion multiplications and two vector-quaternion conversions.
 * (which creates 4 new instances in total)
 * </p>
 *
 * @param <V> The vector itself (the input parameters and return values of various operations)
 * @see Tensor
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
 * @see Quaternion
 */
public interface Vector<V extends Vector<V>> extends Tensor {
    /**
     * Creates and returns a new vector from the provided array of component values.
     *
     * @param values The array of values to construct the vector from
     * @return The created vector
     * @throws IllegalArgumentException When the array's length is invalid ({@code length < 2 || length > 12})
     */
    static Vector<?> newVector(double... values) throws IllegalArgumentException {
        return switch (values.length) {
            case 2 -> new Vector2(values[0], values[1]);
            case 3 -> new Vector3(values[0], values[1], values[2]);
            case 4 -> new Vector4(values[0], values[1], values[2], values[3]);
            case 5 -> new Vector5(values[0], values[1], values[2], values[3], values[4]);
            case 6 -> new Vector6(values[0], values[1], values[2], values[3], values[4], values[5]);
            case 7 -> new Vector7(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
            case 8 ->
                    new Vector8(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);
            case 9 ->
                    new Vector9(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]);
            case 10 ->
                    new Vector10(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
            case 11 ->
                    new Vector11(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10]);
            case 12 ->
                    new Vector12(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11]);
            default -> throw new IllegalArgumentException("There is no defined vector of size " + values.length + ".");
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int size();

    /**
     * Returns the {@code i}th component's value of this vector.
     *
     * @param i The index of the component to get
     * @return The {@code i}th component's value of this vector
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    double valueAt(int i) throws IndexOutOfBoundsException;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    boolean isNaN();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    boolean isFinite();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    boolean isInfinite();

    /**
     * Returns the Euclidean norm of this vector.
     *
     * @return The Euclidean norm of this vector
     */
    double norm();

    /**
     * Returns the squared Euclidean norm of this vector.
     *
     * @return The squared Euclidean norm of this vector
     */
    double normSquared();

    /**
     * Returns the Manhattan norm of this vector.
     *
     * @return The Manhattan norm of this vector
     */
    double normManhattan();

    /**
     * Adds a scalar to this vector, then returns the resulting vector.
     *
     * @param s The scalar of which to add to this vector
     * @return The resulting vector
     */
    V add(double s);

    /**
     * Subtracts a scalar from this vector, then returns the resulting vector.
     *
     * @param s The scalar of which to subtract from this vector
     * @return The resulting vector
     */
    V subtract(double s);

    /**
     * Multiplies this vector by a scalar, then returns the resulting vector.
     *
     * @param s The scalar of which to multiply this vector by
     * @return The resulting vector
     */
    V multiply(double s);

    /**
     * Divides this vector by a scalar, then returns the resulting vector.
     *
     * @param s The scalar of which to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the provide denominator {@code s} is zero
     */
    V divide(double s) throws ArithmeticException;

    /**
     * Adds a vector to this vector, then returns the resulting vector.
     *
     * @param v The vector of which to add to this vector
     * @return The resulting vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    V add(V v);

    /**
     * Subtracts a vector from this vector, then returns the resulting vector.
     *
     * @param v The vector of which to subtract from this vector
     * @return The resulting vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    V subtract(V v);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product between this vector and the provided vector {@code v}
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    double dot(V v);

    /**
     * Returns the absolute value of this vector.
     *
     * @return The absolute value of this vector
     */
    V abs();

    /**
     * Returns the rounded value of this vector.
     *
     * @return The rounded value of this vector
     */
    V round();

    /**
     * Returns the negation of this vector.
     *
     * @return The negation of this vector
     */
    V negate();

    /**
     * Returns the normalized value of this vector.
     *
     * @return The normalized value of this vector
     * @throws ArithmeticException When the Euclidean norm of this vector is zero
     */
    V normalize() throws ArithmeticException;

    /**
     * Applies the provided mapper function to each component of this vector, then returns a new
     * vector whose components are populated from that of the return values of the provided mapper
     * function.
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return The resulting vector
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    V map(DoubleUnaryOperator mapper);

    /**
     * Applies the provided merger function to each corresponding pair of components between this
     * vector and the provided vector {@code v}, then returns a new vector whose components are
     * populated from that of the return values of the provided merger function.
     *
     * @param v      The vector of which to merge this vector with
     * @param merger The merger function of which to handle the merging of the two vectors
     * @return The resulting vector
     * @throws NullPointerException When either the provided vector {@code v} or the
     *                              provided merger function is {@code null}
     */
    V merge(V v, DoubleBinaryOperator merger);

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The boundary vector of which to compare to
     * @return The minimum vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    V min(V v);

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The boundary vector of which to compare to
     * @return The maximum vector
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    V max(V v);

    /**
     * Clamps this vector component-wise to the range of {@code [min, max]}, then returns the
     * clamped vector.
     *
     * @param min The minimum boundary vector of which to compare to
     * @param max The maximum boundary vector of which to compare to
     * @return The clamped vector
     * @throws NullPointerException When a {@code null} value is provided as a boundary vector
     */
    V clamp(V min, V max);

    /**
     * Returns the Euclidean distance to the provided vector {@code v}.
     *
     * @param v The vector of which to get the Euclidean distance to
     * @return The Euclidean distance to the provided vector {@code v}
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    double distance(V v);

    /**
     * Returns the squared Euclidean distance to the provided vector {@code v}.
     *
     * @param v The vector of which to get the squared Euclidean distance to
     * @return The squared Euclidean distance to the provided vector {@code v}
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    double distanceSquared(V v);

    /**
     * Returns the Manhattan distance to the provided vector {@code v}.
     *
     * @param v The vector of which to get the Manhattan distance to
     * @return The Manhattan distance to the provided vector {@code v}
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    double distanceManhattan(V v);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    DoubleStream stream();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    double[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int hashCode();

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    boolean equals(Object obj);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    String toString();
}
