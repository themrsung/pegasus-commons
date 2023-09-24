package pegasus.container;

import pegasus.function.ToFloatFunction;

import java.util.function.*;
import java.util.stream.Stream;

/**
 * A reference-based generic container.
 *
 * @param <T> The type of object this container is to hold
 * @see BaseContainer
 * @see FastObjectContainer
 * @see ArrayElementReference
 */
public interface ObjectContainer<T> extends BaseContainer<T>, Supplier<T> {
    /**
     * Returns a container with an initial value of {@code null}.
     *
     * @param <T> The type of object to hold
     * @return The constructed container
     */
    static <T> ObjectContainer<T> of() {
        return new FastObjectContainer<>(null);
    }

    /**
     * Returns a container with the provided initial value.
     *
     * @param value The initial value
     * @param <T>   The type of object to hold
     * @return The constructed container
     */
    static <T> ObjectContainer<T> of(T value) {
        return new FastObjectContainer<>(value);
    }

    /**
     * Returns whether this container's value is {@code null}.
     *
     * @return {@code true} if this container's value is {@code null}
     */
    boolean isNull();

    /**
     * Returns the value of this container.
     *
     * @return The value of this container
     */
    T get();

    /**
     * Sets the value of this container.
     *
     * @param value The value of this container
     */
    void set(T value);

    /**
     * Updates the value of this container.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void update(UnaryOperator<T> operator);

    /**
     * Applies the provided accumulator function to this container's value and the provided
     * identity value, then sets the value of this container to the return value of the provided
     * accumulator function.
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException When the provided accumulator function is {@code null}
     */
    void accumulate(T next, BinaryOperator<T> accumulator);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    The type of object to map this container's value to
     * @return The resulting value
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> U mapToValue(Function<? super T, ? extends U> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    The type of object to map this container's value to
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectContainer<U> map(Function<? super T, ? extends U> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleContainer mapToDouble(ToDoubleFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongContainer mapToLong(ToLongFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    FloatContainer mapToFloat(ToFloatFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntContainer mapToInt(ToIntFunction<? super T> mapper);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @param <U>    The type of object to merge with
     * @param <V>    The type of object to merge to
     * @return The resulting value
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    <U, V> V mergeToValue(ObjectContainer<U> c,
                          BiFunction<? super T, ? super U, ? extends V> merger);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns a new container containing the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @param <U>    The type of object to merge with
     * @param <V>    The type of object to merge to
     * @return The resulting container
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    <U, V> ObjectContainer<V> merge(ObjectContainer<U> c,
                                    BiFunction<? super T, ? super U, ? extends V> merger);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Stream<T> stream();

    /**
     * Performs the provided action for this container's value.
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException When the provided action is {@code null}
     */
    void peek(Consumer<? super T> action);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int hashCode();

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    boolean equals(Object obj);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    String toString();
}
