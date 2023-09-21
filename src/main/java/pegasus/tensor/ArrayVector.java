package pegasus.tensor;

import pegasus.exception.DivisionByZeroException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.DoubleStream;

/**
 * An array-based vector.
 *
 * @param <V> The vector itself
 */
abstract class ArrayVector<V extends ArrayVector<V>> implements Vector<V> {
    /**
     * Direct assignment constructor.
     *
     * @param values The values of which to directly assign
     */
    protected ArrayVector(double... values) {
        this.values = Objects.requireNonNull(values);
    }

    /**
     * The internal array of values.
     */
    private final double[] values;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return values.length;
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
        return values[i];
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
    public V add(double s) {
        return map(x -> x + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to subtract from this vector
     * @return {@inheritDoc}
     */
    @Override
    public V subtract(double s) {
        return map(x -> x - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to multiply this vector by
     * @return {@inheritDoc}
     */
    @Override
    public V multiply(double s) {
        return map(x -> x * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar of which to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public V divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double inv = 1 / s;
        return map(x -> x * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to add to this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public V add(V v) {
        return merge(v, Double::sum);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to subtract from this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public V subtract(V v) {
        return merge(v, (x, y) -> x - y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public double dot(V v) {
        return merge(v, (x, y) -> x * y).stream().sum();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V abs() {
        return map(Math::abs);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V round() {
        return map(Math::round);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V negate() {
        return multiply(-1);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public V normalize() throws ArithmeticException {
        double s = Math.sqrt(stream().map(x -> x * x).sum());
        if (s == 0) throw new DivisionByZeroException();
        double inv = 1 / s;
        return map(x1 -> x1 * inv);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public V min(V v) {
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
    public V max(V v) {
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
    public V clamp(V min, V max) {
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
    public double distance(V v) {
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
    public double distanceSquared(V v) {
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
    public double distanceManhattan(V v) {
        return subtract(v).normManhattan();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressWarnings("RedundantCast")
    public int hashCode() {
        return Objects.hash((Object) values);
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
        if (values.length != v.size()) return false;
        return Arrays.equals(values, v.toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
