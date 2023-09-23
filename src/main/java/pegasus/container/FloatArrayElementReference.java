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

    @Override
    public boolean isNaN() {
        return Float.isNaN(source[index]);
    }

    @Override
    public boolean isFinite() {
        return Float.isFinite(source[index]);
    }

    @Override
    public boolean isInfinite() {
        return Float.isInfinite(source[index]);
    }

    @Override
    public float get() {
        return source[index];
    }

    @Override
    public void set(float value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    @Override
    public void update(FloatUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsFloat(source[index]);
    }

    @Override
    public void accumulate(float next, FloatBinaryOperator accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsFloat(source[index], next);
    }

    @Override
    public float mapToValue(FloatUnaryOperator mapper) {
        return mapper.applyAsFloat(source[index]);
    }

    @Override
    public FloatContainer map(FloatUnaryOperator mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(FloatFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    @Override
    public DoubleContainer mapToDouble(FloatToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    @Override
    public LongContainer mapToLong(FloatToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    @Override
    public IntContainer mapToInt(FloatToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    @Override
    public float mergeToValue(FloatContainer c, FloatBinaryOperator merger) {
        return merger.applyAsFloat(source[index], c.get());
    }

    @Override
    public FloatContainer merge(FloatContainer c, FloatBinaryOperator merger) {
        return FloatContainer.of(merger.applyAsFloat(source[index], c.get()));
    }

    @Override
    public Stream<Float> stream() {
        return Stream.of(source[index]);
    }

    @Override
    public void peek(FloatConsumer action) {
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
