package pegasus.container;

import pegasus.function.ToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * An object container with no thread safety.
 *
 * @param <T> The type of object to hold
 * @see ObjectContainer
 */
public class FastObjectContainer<T> implements ObjectContainer<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new container.
     *
     * @param value The value of this container
     */
    public FastObjectContainer(T value) {
        this.value = value;
    }

    /**
     * The value of this container.
     */
    protected T value;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNull() {
        return value == null;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public T get() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of this container
     */
    @Override
    public void set(T value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(UnaryOperator<T> operator) {
        value = operator.apply(value);
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
        this.value = accumulator.apply(value, next);
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
        return mapper.apply(value);
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
    public <U> ObjectContainer<U> map(Function<? super T, ? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(value));
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
        return DoubleContainer.of(mapper.applyAsDouble(value));
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
        return LongContainer.of(mapper.applyAsLong(value));
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
        return FloatContainer.of(mapper.applyAsFloat(value));
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
        return IntContainer.of(mapper.applyAsInt(value));
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
        return merger.apply(value, c.get());
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
    public <U, V> ObjectContainer<V> merge(ObjectContainer<U> c,
                                           BiFunction<? super T, ? super U, ? extends V> merger) {
        return ObjectContainer.of(merger.apply(value, c.get()));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return Stream.of(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(Consumer<? super T> action) {
        action.accept(value);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectContainer<?> c)) return false;
        return Objects.equals(value, c.get());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(value);
    }
}
