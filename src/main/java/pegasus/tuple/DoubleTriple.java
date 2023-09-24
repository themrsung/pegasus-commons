package pegasus.tuple;

import pegasus.container.DoubleContainer;
import pegasus.container.DoubleVariableReference;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * A triple of {@code double} values.
 *
 * @param x The first value
 * @param y The second value
 * @param z The third value
 * @see DoubleTuple
 */
public record DoubleTriple(double x, double y, double z) implements DoubleTuple {
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
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(double value) {
        return x == value || y == value || z == value;
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(DoubleTuple t) {
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
    public double get(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
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
    public DoubleContainer getReference(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> new DoubleVariableReference(() -> x, DoubleVariableReference.readOnlySetter());
            case 1 -> new DoubleVariableReference(() -> y, DoubleVariableReference.readOnlySetter());
            case 2 -> new DoubleVariableReference(() -> z, DoubleVariableReference.readOnlySetter());
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
    public DoubleTuple map(DoubleUnaryOperator mapper) {
        return new DoubleTriple(
                mapper.applyAsDouble(x),
                mapper.applyAsDouble(y),
                mapper.applyAsDouble(z)
        );
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
    public <U> Tuple<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return Tuple.of(mapper.apply(x), mapper.apply(y), mapper.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongTuple mapToLong(DoubleToLongFunction mapper) {
        return new LongTriple(
                mapper.applyAsLong(x),
                mapper.applyAsLong(y),
                mapper.applyAsLong(z)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntTuple mapToInt(DoubleToIntFunction mapper) {
        return new IntTriple(
                mapper.applyAsInt(x),
                mapper.applyAsInt(y),
                mapper.applyAsInt(z)
        );
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
    public Iterator<Double> iterator() {
        return stream().iterator();
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
        if (!(obj instanceof DoubleTuple t)) return false;
        if (t.size() != 3) return false;
        return x == t.get(0) && y == t.get(1) && z == t.get(2);
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
