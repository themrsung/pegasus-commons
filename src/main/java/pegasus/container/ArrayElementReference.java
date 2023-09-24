package pegasus.container;

import pegasus.function.ToFloatFunction;

import java.io.Serial;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A reference to an array's element.
 *
 * @param <T> The type of object of which to reference
 * @see ObjectContainer
 */
public final class ArrayElementReference<T> implements ObjectContainer<T> {
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
    public ArrayElementReference(T[] source, int index, boolean readOnly) {
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
    private final T[] source;

    /**
     * The index of this reference.
     */
    private final int index;

    /**
     * Whether this reference is read-only.
     */
    private final boolean readOnly;

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNull() {
        return source[index] == null;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public T get() {
        return source[index];
    }

    /**
     * {@inheritDoc}
     * @param value The value of this container
     */
    @Override
    public void set(T value) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = value;
    }

    /**
     * {@inheritDoc}
     * @param operator The update function of which to apply
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void update(UnaryOperator<T> operator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = operator.apply(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void accumulate(T next, BinaryOperator<T> accumulator) {
        if (readOnly) {
            throw new UnsupportedOperationException("This reference is read-only.");
        }

        source[index] = accumulator.apply(source[index], next);
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> U mapToValue(Function<? super T, ? extends U> mapper) {
        return mapper.apply(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> ObjectContainer<U> map(Function<? super T, ? extends U> mapper) {
        return ObjectContainer.of(mapper.apply(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleContainer mapToDouble(ToDoubleFunction<? super T> mapper) {
        return DoubleContainer.of(mapper.applyAsDouble(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongContainer mapToLong(ToLongFunction<? super T> mapper) {
        return LongContainer.of(mapper.applyAsLong(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public FloatContainer mapToFloat(ToFloatFunction<? super T> mapper) {
        return FloatContainer.of(mapper.applyAsFloat(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param mapper The mapper function of which to apply to this container's value
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntContainer mapToInt(ToIntFunction<? super T> mapper) {
        return IntContainer.of(mapper.applyAsInt(source[index]));
    }

    /**
     * {@inheritDoc}
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @param <V> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U, V> V mergeToValue(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return merger.apply(source[index], c.get());
    }

    /**
     * {@inheritDoc}
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return {@inheritDoc}
     * @param <U> {@inheritDoc}
     * @param <V> {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U, V> ObjectContainer<V> merge(ObjectContainer<U> c, BiFunction<? super T, ? super U, ? extends V> merger) {
        return ObjectContainer.of(merger.apply(source[index], c.get()));
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return Stream.of(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param action The action to be performed for this container's value
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void peek(Consumer<? super T> action) {
        action.accept(source[index]);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(source[index]);
    }

    /**
     * {@inheritDoc}
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectContainer<?> c)) return false;
        return Objects.equals(source[index], c.get());
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toString(source[index]);
    }
}
