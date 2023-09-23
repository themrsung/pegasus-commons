package pegasus.container;

import pegasus.function.DoubleToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * A reference to a {@code double} variables.
 * @see DoubleContainer
 */
public class DoubleVariableReference implements DoubleContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Returns a new read-only setter.
     * @return The read-only setter
     */
    public static DoubleConsumer readOnlySetter() {
        return v -> {
            throw new UnsupportedOperationException("This reference if read-only.");
        };
    }

    /**
     * Creates a new read-only variables reference.
     * @param getter The getter function
     * @throws NullPointerException When the provided getter function is {@code null}
     */
    public DoubleVariableReference(DoubleSupplier getter) {
        this(getter, readOnlySetter());
    }

    /**
     * Creates a new variable reference.
     * @param getter The getter function
     * @param setter The setter function
     * @throws NullPointerException When a {@code null} function is provided
     * @see #readOnlySetter() Read-only
     */
    public DoubleVariableReference(DoubleSupplier getter, DoubleConsumer setter) {
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
    }

    /**
     * The getter function.
     */
    protected final DoubleSupplier getter;

    /**
     * The setter function.
     */
    protected final DoubleConsumer setter;

    @Override
    public boolean isNaN() {
        return Double.isNaN(getter.getAsDouble());
    }

    @Override
    public boolean isFinite() {
        return Double.isFinite(getter.getAsDouble());
    }

    @Override
    public boolean isInfinite() {
        return Double.isInfinite(getter.getAsDouble());
    }

    @Override
    public double get() {
        return getter.getAsDouble();
    }

    @Override
    public void set(double value) {
        setter.accept(value);
    }

    @Override
    public void update(DoubleUnaryOperator operator) {
        setter.accept(operator.applyAsDouble(getter.getAsDouble()));
    }

    @Override
    public void accumulate(double next, DoubleBinaryOperator accumulator) {
        setter.accept(accumulator.applyAsDouble(getter.getAsDouble(), next));
    }

    @Override
    public double mapToValue(DoubleUnaryOperator mapper) {
        return mapper.applyAsDouble(getter.getAsDouble());
    }

    @Override
    public DoubleContainer map(DoubleUnaryOperator mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(getter.getAsDouble()));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(getter.getAsDouble()));
    }

    @Override
    public LongContainer mapToLong(DoubleToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(getter.getAsDouble()));
    }

    @Override
    public FloatContainer mapToFloat(DoubleToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(getter.getAsDouble()));
    }

    @Override
    public IntContainer mapToInt(DoubleToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(getter.getAsDouble()));
    }

    @Override
    public double mergeToValue(DoubleContainer c, DoubleBinaryOperator merger) {
        return merger.applyAsDouble(getter.getAsDouble(), c.get());
    }

    @Override
    public DoubleContainer merge(DoubleContainer c, DoubleBinaryOperator merger) {
        return DoubleContainer.of(merger.applyAsDouble(getter.getAsDouble(), c.get()));
    }

    @Override
    public DoubleStream stream() {
        return DoubleStream.of(getter.getAsDouble());
    }

    @Override
    public void peek(DoubleConsumer action) {
        action.accept(getter.getAsDouble());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getter.getAsDouble());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DoubleContainer c)) return false;
        return getter.getAsDouble() == c.get();
    }

    @Override
    public String toString() {
        return Objects.toString(getter.getAsDouble());
    }
}
