package pegasus.tuple;

import pegasus.util.IndexedIterable;

import java.util.Iterator;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A reference-based generic tuple.
 *
 * @param <T> The type of object this tuple is to hold
 * @see BaseTuple
 * @see Pair
 * @see Triple
 * @see ArrayTuple
 */
public interface Tuple<T> extends BaseTuple<T>, IndexedIterable<T> {
    /**
     * Creates a new tuple from the provided array of values.
     *
     * @param values The values of which to contain
     * @param <T>    The type of object to contain
     * @return The created tuple
     * @throws NullPointerException When the provided array is {@code null}
     */
    @SafeVarargs
    static <T> Tuple<T> of(T... values) {
        return switch (values.length) {
            case 2 -> new UnaryPair<>(values[0], values[1]);
            case 3 -> new UnaryTriple<>(values[0], values[1], values[2]);
            default -> new ArrayTuple<>(values);
        };
    }

    /**
     * Creates a new tuple from the provided array of strings.
     *
     * @param values The values of which to contain
     * @return The created tuple
     * @throws NullPointerException When the provided array is {@code null}
     */
    static StringTuple of(String... values) {
        return new StringTuple(values);
    }

    /**
     * Creates a new tuple from the provided stream of values.
     *
     * @param s   The stream of which to retrieve elements from
     * @param <T> The type of object to contain
     * @return The created tuple
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static <T> Tuple<T> from(Stream<? extends T> s) {
        return new ArrayTuple<>(s);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int size();

    /**
     * Returns whether this tuple contains the provided value.
     *
     * @param value The value of which to check for containment
     * @return {@code true} if this tuple contains the provided value
     */
    boolean contains(T value);

    /**
     * Returns whether this tuple contains every element of the provided tuple {@code t}.
     *
     * @param t The tuple of which to check for containment
     * @return {@code true} if this tuple contains every element of ths provided tuple {@code t}
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    boolean containsAll(Tuple<? extends T> t);

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    T get(int i) throws IndexOutOfBoundsException;

    // TODO: 2023-09-24 Complete this
//
//    /**
//     * Returns a rad
//     * @param i
//     * @return
//     * @throws IndexOutOfBoundsException
//     */
//    ObjectContainer<T> getReference(int i) throws IndexOutOfBoundsException;

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    The type of element of which to map this tuple to
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> Tuple<U> map(Function<? super T, ? extends U> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleTuple mapToDouble(ToDoubleFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongTuple mapToLong(ToLongFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntTuple mapToInt(ToIntFunction<? super T> mapper);

    /**
     * Casts the elements of this tuple to the provided target class, then returns a new tuple
     * containing the cast elements.
     *
     * @param target The target class of which to cast to
     * @param <U>    The type of element of which to cast to
     * @return The resulting tuple
     * @throws ClassCastException   When at least one element of this tuple is not castable to
     *                              the provided target class
     * @throws NullPointerException When the provided target class is {@code null}
     */
    <U> Tuple<U> cast(Class<? extends U> target) throws ClassCastException;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Stream<T> stream();

    /**
     * Returns an array containing the elements of this tuple.
     *
     * @return The array representation of this tuple
     */
    T[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Iterator<T> iterator();

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
