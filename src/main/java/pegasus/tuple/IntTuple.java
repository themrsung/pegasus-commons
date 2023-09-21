package pegasus.tuple;

import pegasus.util.IndexedIntIterable;

import java.util.Iterator;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A value-based primitive tuple of {@code int} values.
 *
 * @see BaseTuple
 */
public interface IntTuple extends BaseTuple<Integer>, IndexedIntIterable {
    /**
     * Creates a new tuple from the provided array of values.
     *
     * @param values The values of which to contain
     * @return The created tuple
     * @throws NullPointerException When the provided array is {@code null}
     */
    static IntTuple of(int... values) {
        return new IntArrayTuple(values);
    }

    /**
     * Creates a new tuple from the provided stream of values.
     *
     * @param s The stream of which to retrieve elements from
     * @return The created tuple
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static IntTuple from(IntStream s) {
        return new IntArrayTuple(s);
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
    boolean contains(int value);

    /**
     * Returns whether this tuple contains every element of the provided tuple {@code t}.
     *
     * @param t The tuple of which to check for containment
     * @return {@code true} if this tuple contains every element of ths provided tuple {@code t}
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    boolean containsAll(IntTuple t);

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    int get(int i) throws IndexOutOfBoundsException;

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntTuple map(IntUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    The type of element of which to map this tuple to
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> Tuple<U> mapToObj(IntFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleTuple mapToDouble(IntToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongTuple mapToLong(IntToLongFunction mapper);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    IntStream stream();

    /**
     * Returns an array containing the elements of this tuple.
     *
     * @return The array representation of this tuple
     */
    int[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Iterator<Integer> iterator();

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
