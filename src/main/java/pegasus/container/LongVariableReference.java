package pegasus.container;

import pegasus.function.LongToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * A reference to a {@code long} variables.
 *
 * @see LongContainer
 */
public class LongVariableReference implements LongContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Returns a new read-only setter.
     *
     * @return The read-only setter
     */
    public static LongConsumer readOnlySetter() {
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
    public LongVariableReference(LongSupplier getter) {
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
    public LongVariableReference(LongSupplier getter, LongConsumer setter) {
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
    }

    /**
     * The getter function.
     */
    protected final LongSupplier getter;

    /**
     * The setter function.
     */
    protected final LongConsumer setter;

    @Override
    public long get() {
        return getter.getAsLong();
    }

    @Override
    public void set(long value) {
        setter.accept(value);
    }

    @Override
    public void update(LongUnaryOperator operator) {
        setter.accept(operator.applyAsLong(getter.getAsLong()));
    }

    @Override
    public void accumulate(long next, LongBinaryOperator accumulator) {
        setter.accept(accumulator.applyAsLong(getter.getAsLong(), next));
    }

    @Override
    public long mapToValue(LongUnaryOperator mapper) {
        return mapper.applyAsLong(getter.getAsLong());
    }

    @Override
    public LongContainer map(LongUnaryOperator mapper) {
        return LongContainer.of(mapper.applyAsLong(getter.getAsLong()));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(LongFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(getter.getAsLong()));
    }

    @Override
    public DoubleContainer mapToDouble(LongToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(getter.getAsLong()));
    }

    @Override
    public FloatContainer mapToFloat(LongToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(getter.getAsLong()));
    }

    @Override
    public IntContainer mapToInt(LongToIntFunction mapper) {
        return IntContainer.of(mapper.applyAsInt(getter.getAsLong()));
    }

    @Override
    public long mergeToValue(LongContainer c, LongBinaryOperator merger) {
        return merger.applyAsLong(getter.getAsLong(), c.get());
    }

    @Override
    public LongContainer merge(LongContainer c, LongBinaryOperator merger) {
        return LongContainer.of(merger.applyAsLong(getter.getAsLong(), c.get()));
    }

    @Override
    public LongStream stream() {
        return LongStream.of(getter.getAsLong());
    }

    @Override
    public void peek(LongConsumer action) {
        action.accept(getter.getAsLong());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getter.getAsLong());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LongContainer c)) return false;
        return getter.getAsLong() == c.get();
    }

    @Override
    public String toString() {
        return Objects.toString(getter.getAsLong());
    }
}
