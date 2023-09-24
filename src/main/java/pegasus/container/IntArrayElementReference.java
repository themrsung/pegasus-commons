package pegasus.container;

import pegasus.function.IntToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A reference to an array's element.
 */
public class IntArrayElementReference implements IntContainer {
    @Serial
    private static final int serialVersionUID = 0;

    /**
     * Creates a new reference.
     *
     * @param source   The source array
     * @param index    The element's index
     * @param readOnly Whether this reference is read-only
     * @throws NullPointerException      When the provided array is {@code null}
     * @throws IndexOutOfBoundsException When the provided index is out of bounds
     */
    public IntArrayElementReference(int[] source, int index, boolean readOnly) {
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
    private final int[] source;

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
     *
     * @return {@inheritDoc}
     */
    @Override
    public int get() {
        return source[index];
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(int value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(IntUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsInt(source[index]);
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
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsInt(source[index], next);
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
        return mapper.applyAsInt(source[index]);
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
        return IntContainer.of(mapper.applyAsInt(source[index]));
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
        return ObjectContainer.of(mapper.apply(source[index]));
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
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
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
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
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
        return LongContainer.of(mapper.applyAsLong(source[index]));
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
        return merger.applyAsInt(source[index], c.get());
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
        return IntContainer.of(merger.applyAsInt(source[index], c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public IntStream stream() {
        return IntStream.of(source[index]);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(IntConsumer action) {
        action.accept(source[index]);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(source[index]);
    }

    /**
     * {@inheritDoc}
     *
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
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(source[index]);
    }
}
