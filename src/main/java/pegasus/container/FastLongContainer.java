package pegasus.container;

import pegasus.function.LongToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * A {@code long} container with no thread safety.
 *
 * @see LongContainer
 */
public class FastLongContainer implements LongContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new container.
     *
     * @param value The value of this container
     */
    public FastLongContainer(long value) {
        this.value = value;
    }

    /**
     * The value of this container.
     */
    protected long value;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long get() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(long value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(LongUnaryOperator operator) {
        this.value = operator.applyAsLong(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(long next, LongBinaryOperator accumulator) {
        this.value = accumulator.applyAsLong(value, next);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public long mapToValue(LongUnaryOperator mapper) {
        return mapper.applyAsLong(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongContainer map(LongUnaryOperator mapper) {
        return LongContainer.of(mapper.applyAsLong(value));
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
    public <U> ObjectContainer<U> mapToObj(LongFunction<? extends U> mapper) {
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
    public DoubleContainer mapToDouble(LongToDoubleFunction mapper) {
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
    public FloatContainer mapToFloat(LongToFloatFunction mapper) {
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
    public IntContainer mapToInt(LongToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(value));
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
    public long mergeToValue(LongContainer c, LongBinaryOperator merger) {
        return merger.applyAsLong(value, c.get());
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
    public LongContainer merge(LongContainer c, LongBinaryOperator merger) {
        return LongContainer.of(merger.applyAsLong(value, c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public LongStream stream() {
        return LongStream.of(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(LongConsumer action) {
        action.accept(value);
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
        if (!(obj instanceof LongContainer c)) return false;
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
