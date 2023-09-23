package pegasus.container;

import pegasus.function.LongToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * A reference to an array's element.
 */
public class LongArrayElementReference implements LongContainer {
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
    public LongArrayElementReference(long[] source, int index, boolean readOnly) {
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
    private final long[] source;

    /**
     * The index of this reference.
     */
    private final int index;

    /**
     * Whether this reference is read-only.
     */
    private final boolean readOnly;

    @Override
    public long get() {
        return source[index];
    }

    @Override
    public void set(long value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    @Override
    public void update(LongUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsLong(source[index]);
    }

    @Override
    public void accumulate(long next, LongBinaryOperator accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsLong(source[index], next);
    }

    @Override
    public long mapToValue(LongUnaryOperator mapper) {
        return mapper.applyAsLong(source[index]);
    }

    @Override
    public LongContainer map(LongUnaryOperator mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(LongFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    @Override
    public DoubleContainer mapToDouble(LongToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    @Override
    public FloatContainer mapToFloat(LongToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    @Override
    public IntContainer mapToInt(LongToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    @Override
    public long mergeToValue(LongContainer c, LongBinaryOperator merger) {
        return merger.applyAsLong(source[index], c.get());
    }

    @Override
    public LongContainer merge(LongContainer c, LongBinaryOperator merger) {
        return LongContainer.of(merger.applyAsLong(source[index], c.get()));
    }

    @Override
    public LongStream stream() {
        return LongStream.of(source[index]);
    }

    @Override
    public void peek(LongConsumer action) {
        action.accept(source[index]);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(source[index]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectContainer<?> c)) return false;
        return Objects.equals(source[index], c.get());
    }

    @Override
    public String toString() {
        return Objects.toString(source[index]);
    }
}
