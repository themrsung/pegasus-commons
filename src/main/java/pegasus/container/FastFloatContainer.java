package pegasus.container;

import pegasus.function.*;

import java.io.Serial;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A {@code float} container with no thread safety.
 *
 * @see FloatContainer
 */
public class FastFloatContainer implements FloatContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new container.
     *
     * @param value The value of this container
     */
    public FastFloatContainer(float value) {
        this.value = value;
    }

    /**
     * The value of this container.
     */
    protected float value;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Float.isNaN(value);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Float.isFinite(value);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Float.isInfinite(value);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float get() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(float value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(FloatUnaryOperator operator) {
        this.value = operator.applyAsFloat(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(float next, FloatBinaryOperator accumulator) {
        this.value = accumulator.applyAsFloat(value, next);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public float mapToValue(FloatUnaryOperator mapper) {
        return mapper.applyAsFloat(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer map(FloatUnaryOperator mapper) {
        return FloatContainer.of(mapper.applyAsFloat(value));
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
    public <U> ObjectContainer<U> mapToObj(FloatFunction<? extends U> mapper) {
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
    public DoubleContainer mapToDouble(FloatToDoubleFunction mapper) {
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
    public LongContainer mapToLong(FloatToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer mapToInt(FloatToIntFunction mapper) {
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
    public float mergeToValue(FloatContainer c, FloatBinaryOperator merger) {
        return merger.applyAsFloat(value, c.get());
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
    public FloatContainer merge(FloatContainer c, FloatBinaryOperator merger) {
        return FloatContainer.of(merger.applyAsFloat(value, c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Float> stream() {
        return Stream.of(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(FloatConsumer action) {
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
        if (!(obj instanceof FloatContainer c)) return false;
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
