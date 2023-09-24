package pegasus.container;

import pegasus.function.ToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * An atomized container.
 *
 * @param <T> The type of object of which to reference
 * @see ObjectContainer
 */
public class AtomicContainer<T> extends AtomicReference<T> implements ObjectContainer<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new atomic container.
     *
     * @param initialValue The initial value of this container
     */
    public AtomicContainer(T initialValue) {
        super(initialValue);
    }

    /**
     * Creates a new atomic container with an initial value of {@code null}.
     */
    public AtomicContainer() {
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNull() {
        return get() == null;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(UnaryOperator<T> operator) {
        getAndUpdate(operator);
    }

    /**
     * {@inheritDoc}
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(T next, BinaryOperator<T> accumulator) {
        getAndUpdate(current -> accumulator.apply(current, next));
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
    public <U> U mapToValue(Function<? super T, ? extends U> mapper) {
        return mapper.apply(get());
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
    public <U> AtomicContainer<U> map(Function<? super T, ? extends U> mapper) {
        return new AtomicContainer<>(mapper.apply(get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleContainer mapToDouble(ToDoubleFunction<? super T> mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongContainer mapToLong(ToLongFunction<? super T> mapper) {
        return LongContainer.of(mapper.applyAsLong(get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer mapToFloat(ToFloatFunction<? super T> mapper) {
        return FloatContainer.of(mapper.applyAsFloat(get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer mapToInt(ToIntFunction<? super T> mapper) {
        return IntContainer.of(mapper.applyAsInt(get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @param <U>    {@inheritDoc}
     * @param <V>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U, V> V mergeToValue(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return merger.apply(get(), c.get());
    }

    /**
     * {@inheritDoc}
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @param <U>    {@inheritDoc}
     * @param <V>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U, V> AtomicContainer<V> merge(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return new AtomicContainer<>(merger.apply(get(), c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return Stream.of(get());
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(Consumer<? super T> action) {
        action.accept(get());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(get());
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
        return Objects.equals(get(), c.get());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(get());
    }
}
