package pegasus.container;

import pegasus.function.IntToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A reference to a {@code int} variables.
 *
 * @see IntContainer
 */
public class IntVariableReference implements IntContainer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Returns a new read-only setter.
     *
     * @return The read-only setter
     */
    public static IntConsumer readOnlySetter() {
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
    public IntVariableReference(IntSupplier getter) {
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
    public IntVariableReference(IntSupplier getter, IntConsumer setter) {
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
    }

    /**
     * The getter function.
     */
    protected final IntSupplier getter;

    /**
     * The setter function.
     */
    protected final IntConsumer setter;

    @Override
    public int get() {
        return getter.getAsInt();
    }

    @Override
    public void set(int value) {
        setter.accept(value);
    }

    @Override
    public void update(IntUnaryOperator operator) {
        setter.accept(operator.applyAsInt(getter.getAsInt()));
    }

    @Override
    public void accumulate(int next, IntBinaryOperator accumulator) {
        setter.accept(accumulator.applyAsInt(getter.getAsInt(), next));
    }

    @Override
    public int mapToValue(IntUnaryOperator mapper) {
        return mapper.applyAsInt(getter.getAsInt());
    }

    @Override
    public IntContainer map(IntUnaryOperator mapper) {
        return IntContainer.of(mapper.applyAsInt(getter.getAsInt()));
    }

    @Override
    public <U> ObjectContainer<U> mapToObj(IntFunction<? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(getter.getAsInt()));
    }

    @Override
    public DoubleContainer mapToDouble(IntToDoubleFunction mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(getter.getAsInt()));
    }

    @Override
    public LongContainer mapToLong(IntToLongFunction mapper) {
        return LongContainer.of(mapper.applyAsLong(getter.getAsInt()));
    }

    @Override
    public FloatContainer mapToFloat(IntToFloatFunction mapper) {
        return FloatContainer.of(mapper.applyAsFloat(getter.getAsInt()));
    }

    @Override
    public int mergeToValue(IntContainer c, IntBinaryOperator merger) {
        return merger.applyAsInt(getter.getAsInt(), c.get());
    }

    @Override
    public IntContainer merge(IntContainer c, IntBinaryOperator merger) {
        return IntContainer.of(merger.applyAsInt(getter.getAsInt(), c.get()));
    }

    @Override
    public IntStream stream() {
        return IntStream.of(getter.getAsInt());
    }

    @Override
    public void peek(IntConsumer action) {
        action.accept(getter.getAsInt());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getter.getAsInt());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntContainer c)) return false;
        return getter.getAsInt() == c.get();
    }

    @Override
    public String toString() {
        return Objects.toString(getter.getAsInt());
    }
}
