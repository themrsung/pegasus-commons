package pegasus.container;

import pegasus.function.*;

import java.io.Serial;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A reference to an array's element.
 */
public class FloatArrayElementReference implements FloatContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new reference.
     *
     * @param source   The source array
     * @param index    The element's index
     * @param readOnly Whether this reference is read-only
     * @throws NullPointerException      When the provided array is {@code null}
     * @throws IndexOutOfBoundsException When the provided index is out of bounds
     */
    public FloatArrayElementReference(float[] source, int index, boolean readOnly) {
        this.source = Objects.requireNonNull(source);

        if (index < 0 || index >= source.length) {
            throw new IndexOutOfBoundsException(index);
        }

        this.index = index;
        this.readOnly = readOnly;
    }

    /**
     * The source array.
     */
    private final float[] source;

    /**
     * The index of this reference.
     */
    private final int index;

    /**
     * Whether this reference is read-only.
     */
    private final boolean readOnly;

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Float.isNaN(source[index]);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Float.isFinite(source[index]);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Float.isInfinite(source[index]);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public float get() {
        return source[index];
    }

    /**
     * {@inheritDoc}
     * @param value The value of this container
     */
    @Override
    public void set(float value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    /**
     * {@inheritDoc}
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(FloatUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsFloat(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(float next, FloatBinaryOperator accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsFloat(source[index], next);
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public float mapToValue(FloatUnaryOperator mapper) {
        return mapper.applyAsFloat(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer map(FloatUnaryOperator mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> ObjectContainer<U> mapToObj(FloatFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleContainer mapToDouble(FloatToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongContainer mapToLong(FloatToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer mapToInt(FloatToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public float mergeToValue(FloatContainer c, FloatBinaryOperator merger) {
        return merger.applyAsFloat(source[index], c.get());
    }

    /**
     * {@inheritDoc}
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer merge(FloatContainer c, FloatBinaryOperator merger) {
        return FloatContainer.of(merger.applyAsFloat(source[index], c.get()));
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Float> stream() {
        return Stream.of(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(FloatConsumer action) {
        action.accept(source[index]);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectContainer<?> c)) return false;
        return Objects.equals(source[index], c.get());
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(source[index]);
    }
}
