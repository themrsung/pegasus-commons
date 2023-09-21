package pegasus.tuple;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * An array-based {@code int} tuple.
 *
 * @see IntTuple
 */
public class IntArrayTuple implements IntTuple {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array tuple.
     *
     * @param values The values of which to contain
     * @throws NullPointerException When the provided array is {@code null}
     */
    public IntArrayTuple(int[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    /**
     * Creates a new array tuple.
     *
     * @param s The stream of which to retrieve elements from
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    public IntArrayTuple(IntStream s) {
        this.values = s.toArray();
    }

    /**
     * The internal array of values.
     */
    private final int[] values;

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
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(int value) {
        return stream().anyMatch(v -> v == value);
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsAll(IntTuple t) {
        return t.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public int get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntTuple map(IntUnaryOperator mapper) {
        return IntTuple.from(stream().map(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> Tuple<U> mapToObj(IntFunction<? extends U> mapper) {
        return Tuple.from(stream().mapToObj(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleTuple mapToDouble(IntToDoubleFunction mapper) {
        return DoubleTuple.from(stream().mapToDouble(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongTuple mapToLong(IntToLongFunction mapper) {
        return LongTuple.from(stream().mapToLong(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public IntStream stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<Integer> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @SuppressWarnings("RedundantCast")
    @Override
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
        if (!(obj instanceof IntTuple t)) return false;
        if (values.length != t.size()) return false;

        for (int i = 0; i < values.length; i++) {
            if (values[i] != t.get(i)) return false;
        }

        return true;
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
