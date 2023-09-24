package pegasus.container;

import pegasus.function.*;

import java.io.Serial;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A reference to a {@code float} variables.
 *
 * @see FloatContainer
 */
public class FloatVariableReference implements FloatContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Returns a new read-only setter.
     *
     * @return The read-only setter
     */
    public static FloatConsumer readOnlySetter() {
        return v -> {
            throw new UnsupportedOperationException("This reference if read-only.");
        };
    }

    /**
     * Creates a new read-only variables reference.
     *
     * @param getter The getter function
     * @throws NullPointerException When the provided getter function is {@code null}
     */
    public FloatVariableReference(FloatSupplier getter) {
        this(getter, readOnlySetter());
    }

    /**
     * Creates a new variable reference.
     *
     * @param getter The getter function
     * @param setter The setter function
     * @throws NullPointerException When a {@code null} function is provided
     * @see #readOnlySetter() Read-only
     */
    public FloatVariableReference(FloatSupplier getter, FloatConsumer setter) {
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
    }

    /**
     * The getter function.
     */
    protected final FloatSupplier getter;

    /**
     * The setter function.
     */
    protected final FloatConsumer setter;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Float.isNaN(getter.getAsFloat());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Float.isFinite(getter.getAsFloat());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Float.isInfinite(getter.getAsFloat());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float getAsFloat() {
        return getter.getAsFloat();
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(float value) {
        setter.accept(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(FloatUnaryOperator operator) {
        setter.accept(operator.applyAsFloat(getter.getAsFloat()));
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
        setter.accept(accumulator.applyAsFloat(getter.getAsFloat(), next));
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
        return mapper.applyAsFloat(getter.getAsFloat());
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
        return FloatContainer.of(mapper.applyAsFloat(getter.getAsFloat()));
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
        return ObjectContainer.of(mapper.apply(getter.getAsFloat()));
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
        return DoubleContainer.of(mapper.applyAsDouble(getter.getAsFloat()));
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
        return LongContainer.of(mapper.applyAsLong(getter.getAsFloat()));
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
        return IntContainer.of(mapper.applyAsInt(getter.getAsFloat()));
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
        return merger.applyAsFloat(getter.getAsFloat(), c.getAsFloat());
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
        return FloatContainer.of(merger.applyAsFloat(getter.getAsFloat(), c.getAsFloat()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Float> stream() {
        return Stream.of(getter.getAsFloat());
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(FloatConsumer action) {
        action.accept(getter.getAsFloat());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getter.getAsFloat());
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
        return getter.getAsFloat() == c.getAsFloat();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(getter.getAsFloat());
    }
}
