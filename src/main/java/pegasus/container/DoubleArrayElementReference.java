package pegasus.container;

import pegasus.function.DoubleToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * A reference to an array's element.
 */
public class DoubleArrayElementReference implements DoubleContainer {
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
    public DoubleArrayElementReference(double[] source, int index, boolean readOnly) {
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
    private final double[] source;

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
        return Double.isNaN(source[index]);
    }

    @Override
    public boolean isFinite() {
        return Double.isFinite(source[index]);
    }

    @Override
    public boolean isInfinite() {
        return Double.isInfinite(source[index]);
    }

    @Override
    public double get() {
        return source[index];
    }

    @Override
    public void set(double value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    @Override
    public void update(DoubleUnaryOperator operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.applyAsDouble(source[index]);
    }

    @Override
    public void accumulate(double next, DoubleBinaryOperator accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.applyAsDouble(source[index], next);
    }

    @Override
    public double mapToValue(DoubleUnaryOperator mapper) {
        return mapper.applyAsDouble(source[index]);
    }

    @Override
    public DoubleContainer map(DoubleUnaryOperator mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    @Override
    public LongContainer mapToLong(DoubleToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    @Override
    public FloatContainer mapToFloat(DoubleToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    @Override
    public IntContainer mapToInt(DoubleToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    @Override
    public double mergeToValue(DoubleContainer c, DoubleBinaryOperator merger) {
        return merger.applyAsDouble(source[index], c.get());
    }

    @Override
    public DoubleContainer merge(DoubleContainer c, DoubleBinaryOperator merger) {
        return DoubleContainer.of(merger.applyAsDouble(source[index], c.get()));
    }

    @Override
    public DoubleStream stream() {
        return DoubleStream.of(source[index]);
    }

    @Override
    public void peek(DoubleConsumer action) {
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
