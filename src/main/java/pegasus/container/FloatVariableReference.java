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

    @Override
    public boolean isNaN() {
        return Float.isNaN(getter.getAsFloat());
    }

    @Override
    public boolean isFinite() {
        return Float.isFinite(getter.getAsFloat());
    }

    @Override
    public boolean isInfinite() {
        return Float.isInfinite(getter.getAsFloat());
    }

    @Override
    public float get() {
        return getter.getAsFloat();
    }

    @Override
    public void set(float value) {
        setter.accept(value);
    }

    @Override
    public void update(FloatUnaryOperator operator) {
        setter.accept(operator.applyAsFloat(getter.getAsFloat()));
    }

    @Override
    public void accumulate(float next, FloatBinaryOperator accumulator) {
        setter.accept(accumulator.applyAsFloat(getter.getAsFloat(), next));
    }

    @Override
    public float mapToValue(FloatUnaryOperator mapper) {
        return mapper.applyAsFloat(getter.getAsFloat());
    }

    @Override
    public FloatContainer map(FloatUnaryOperator mapper) {
        return FloatContainer.of(mapper.applyAsFloat(getter.getAsFloat()));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(FloatFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(getter.getAsFloat()));
    }

    @Override
    public DoubleContainer mapToDouble(FloatToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(getter.getAsFloat()));
    }

    @Override
    public LongContainer mapToLong(FloatToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(getter.getAsFloat()));
    }

    @Override
    public IntContainer mapToInt(FloatToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(getter.getAsFloat()));
    }

    @Override
    public float mergeToValue(FloatContainer c, FloatBinaryOperator merger) {
        return merger.applyAsFloat(getter.getAsFloat(), c.get());
    }

    @Override
    public FloatContainer merge(FloatContainer c, FloatBinaryOperator merger) {
        return FloatContainer.of(merger.applyAsFloat(getter.getAsFloat(), c.get()));
    }

    @Override
    public Stream<Float> stream() {
        return Stream.of(getter.getAsFloat());
    }

    @Override
    public void peek(FloatConsumer action) {
        action.accept(getter.getAsFloat());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getter.getAsFloat());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FloatContainer c)) return false;
        return getter.getAsFloat() == c.get();
    }

    @Override
    public String toString() {
        return Objects.toString(getter.getAsFloat());
    }
}
