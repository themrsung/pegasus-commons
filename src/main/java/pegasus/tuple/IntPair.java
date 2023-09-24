package pegasus.tuple;

import pegasus.container.IntContainer;
import pegasus.container.IntVariableReference;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A pair of {@code int} values.
 *
 * @param x The first value
 * @param y The second value
 * @see IntTuple
 */
public record IntPair(int x, int y) implements IntTuple {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 2;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(int value) {
        return x == value || y == value;
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IndexOutOfBoundsException(i);
        };
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public IntContainer getReference(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> new IntVariableReference(() -> x, IntVariableReference.readOnlySetter());
            case 1 -> new IntVariableReference(() -> y, IntVariableReference.readOnlySetter());
            default -> throw new IndexOutOfBoundsException(i);
        };
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
        return new IntPair(mapper.applyAsInt(x), mapper.applyAsInt(y));
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
        return Tuple.of(mapper.apply(x), mapper.apply(y));
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
        return new DoublePair(mapper.applyAsDouble(x), mapper.applyAsDouble(y));
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
        return new LongPair(mapper.applyAsLong(x), mapper.applyAsLong(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public IntStream stream() {
        return IntStream.of(x, y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int[] toArray() {
        return new int[]{x, y};
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
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
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
        if (t.size() != 2) return false;
        return x == t.get(0) && y == t.get(1);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
