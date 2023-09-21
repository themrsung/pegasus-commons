package pegasus.tuple;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * An array-based {@code double} tuple.
 * @see DoubleTuple
 */
public class DoubleArrayTuple implements DoubleTuple {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array tuple.
     * @param values The values of which to contain
     * @throws NullPointerException When the provided array is {@code null}
     */
    public DoubleArrayTuple(double[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    /**
     * Creates a new array tuple.
     * @param s The stream of which to retrieve elements from
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    public DoubleArrayTuple(DoubleStream s) {
        this.values = s.toArray();
    }

    /**
     * The internal array of values.
     */
    private final double[] values;

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * {@inheritDoc}
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(double value) {
        return stream().anyMatch(v -> v == value);
    }

    /**
     * {@inheritDoc}
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsAll(DoubleTuple t) {
        return t.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public double get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleTuple map(DoubleUnaryOperator mapper) {
        return DoubleTuple.from(stream().map(mapper));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> Tuple<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return Tuple.from(stream().mapToObj(mapper));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongTuple mapToLong(DoubleToLongFunction mapper) {
        return LongTuple.from(stream().mapToLong(mapper));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntTuple mapToInt(DoubleToIntFunction mapper) {
        return IntTuple.from(stream().mapToInt(mapper));
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<Double> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @SuppressWarnings("RedundantCast")
    @Override
    public int hashCode() {
        return Objects.hash((Object) values);
    }

    /**
     * {@inheritDoc}
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DoubleTuple t)) return false;
        if (values.length != t.size()) return false;

        for (int i = 0; i < values.length; i++) {
            if (values[i] != t.get(i)) return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
