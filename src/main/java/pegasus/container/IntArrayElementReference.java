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

    @Override
    public int get() {
        return source[index];
    }

    @Override
    public void set(int value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    @Override
    public void update(IntUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsInt(source[index]);
    }

    @Override
    public void accumulate(int next, IntBinaryOperator accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsInt(source[index], next);
    }

    @Override
    public int mapToValue(IntUnaryOperator mapper) {
        return mapper.applyAsInt(source[index]);
    }

    @Override
    public IntContainer map(IntUnaryOperator mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(IntFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    @Override
    public DoubleContainer mapToDouble(IntToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    @Override
    public FloatContainer mapToFloat(IntToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    @Override
    public LongContainer mapToLong(IntToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    @Override
    public int mergeToValue(IntContainer c, IntBinaryOperator merger) {
        return merger.applyAsInt(source[index], c.get());
    }

    @Override
    public IntContainer merge(IntContainer c, IntBinaryOperator merger) {
        return IntContainer.of(merger.applyAsInt(source[index], c.get()));
    }

    @Override
    public IntStream stream() {
        return IntStream.of(source[index]);
    }

    @Override
    public void peek(IntConsumer action) {
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
