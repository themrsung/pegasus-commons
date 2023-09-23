package pegasus.container;

import pegasus.function.ToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A reference to variable of a class.
 * @param <T> The type of object of which to reference
 * @see ObjectContainer
 */
public class VariableReference<T> implements ObjectContainer<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Returns a new read-only setter.
     * @return The read-only setter
     * @param <T> The type of object
     */
    public static <T> Consumer<T> readOnlySetter() {
        return v -> {
            throw new UnsupportedOperationException("This reference if read-only.");
        };
    }

    /**
     * Creates a new read-only variables reference.
     * @param getter The getter function
     * @throws NullPointerException When the provided getter function is {@code null}
     */
    public VariableReference(Supplier<? extends T> getter) {
        this(getter, readOnlySetter());
    }

    /**
     * Creates a new variable reference.
     * @param getter The getter function
     * @param setter The setter function
     * @throws NullPointerException When a {@code null} function is provided
     * @see #readOnlySetter() Read-only
     */
    public VariableReference(Supplier<? extends T> getter, Consumer<? super T> setter) {
        this.getter = Objects.requireNonNull(getter);
        this.setter = Objects.requireNonNull(setter);
    }

    /**
     * The getter function.
     */
    protected final Supplier<? extends T> getter;

    /**
     * The setter function.
     */
    protected final Consumer<? super T> setter;

    @Override
    public boolean isNull() {
        return getter.get() == null;
    }

    @Override
    public T get() {
        return getter.get();
    }

    @Override
    public void set(T value) {
        setter.accept(value);
    }

    @Override
    public void update(UnaryOperator<T> operator) {
        setter.accept(operator.apply(getter.get()));
    }

    @Override
    public void accumulate(T next, BinaryOperator<T> accumulator) {
        setter.accept(accumulator.apply(getter.get(), next));
    }

    @Override
    public <U> U mapToValue(Function<? super T, ? extends U> mapper) {
        return mapper.apply(getter.get());
    }

    @Override
    public <U> ObjectContainer<U> map(Function<? super T, ? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(getter.get()));
    }

    @Override
    public DoubleContainer mapToDouble(ToDoubleFunction<? super T> mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(getter.get()));
    }

    @Override
    public LongContainer mapToLong(ToLongFunction<? super T> mapper) {
        return LongContainer.of(mapper.applyAsLong(getter.get()));
    }

    @Override
    public FloatContainer mapToFloat(ToFloatFunction<? super T> mapper) {
        return FloatContainer.of(mapper.applyAsFloat(getter.get()));
    }

    @Override
    public IntContainer mapToInt(ToIntFunction<? super T> mapper) {
        return IntContainer.of(mapper.applyAsInt(getter.get()));
    }

    @Override
    public <U, V> V mergeToValue(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return merger.apply(getter.get(), c.get());
    }

    @Override
    public <U, V> ObjectContainer<V> merge(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return ObjectContainer.of(merger.apply(getter.get(), c.get()));
    }

    @Override
    public Stream<T> stream() {
        return Stream.of(getter.get());
    }

    @Override
    public void peek(Consumer<? super T> action) {
        action.accept(getter.get());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getter.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectContainer<?> c)) return false;
        return Objects.equals(getter.get(), c.get());
    }

    @Override
    public String toString() {
        return Objects.toString(getter.get());
    }
}
