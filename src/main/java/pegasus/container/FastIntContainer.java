package pegasus.container;

import pegasus.function.IntToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A {@code int} container with no thread safety.
 *
 * @see IntContainer
 */
public class FastIntContainer implements IntContainer {
    @Serial
    private static final int serialVersionUID = 0;

    /**
     * Creates a new container.
     *
     * @param value The value of this container
     */
    public FastIntContainer(int value) {
        this.value = value;
    }

    /**
     * The value of this container.
     */
    protected int value;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int get() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(IntUnaryOperator operator) {
        this.value = operator.applyAsInt(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(int next, IntBinaryOperator accumulator) {
        this.value = accumulator.applyAsInt(value, next);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int mapToValue(IntUnaryOperator mapper) {
        return mapper.applyAsInt(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer map(IntUnaryOperator mapper) {
        return IntContainer.of(mapper.applyAsInt(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> ObjectContainer<U> mapToObj(IntFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleContainer mapToDouble(IntToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer mapToFloat(IntToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongContainer mapToLong(IntToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int mergeToValue(IntContainer c, IntBinaryOperator merger) {
        return merger.applyAsInt(value, c.get());
    }

    /**
     * {@inheritDoc}
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer merge(IntContainer c, IntBinaryOperator merger) {
        return IntContainer.of(merger.applyAsInt(value, c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public IntStream stream() {
        return IntStream.of(value);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntContainer c)) return false;
        return value == c.get();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(value);
    }
}
