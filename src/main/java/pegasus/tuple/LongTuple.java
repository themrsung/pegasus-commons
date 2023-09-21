package pegasus.tuple;

import pegasus.util.IndexedLongIterable;

import java.util.Iterator;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * A value-based primitive tuple of {@code long} values.
 *
 * @see BaseTuple
 */
public interface LongTuple extends BaseTuple<Long>, IndexedLongIterable {
    /**
     * Creates a new tuple from the provided array of values.
     * @param values The values of which to contain
     * @return The created tuple
     * @throws NullPointerException When the provided array is {@code null}
     */
    static LongTuple of(long... values) {
        return new LongArrayTuple(values);
    }

    /**
     * Creates a new tuple from the provided stream of values.
     * @param s The stream of which to retrieve elements from
     * @return The created tuple
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static LongTuple from(LongStream s) {
        return new LongArrayTuple(s);
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
    boolean contains(long value);

    /**
     * Returns whether this tuple contains every element of the provided tuple {@code t}.
     *
     * @param t The tuple of which to check for containment
     * @return {@code true} if this tuple contains every element of ths provided tuple {@code t}
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    boolean containsAll(LongTuple t);

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    long get(int i) throws IndexOutOfBoundsException;

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongTuple map(LongUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    The type of element of which to map this tuple to
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> Tuple<U> mapToObj(LongFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleTuple mapToDouble(LongToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntTuple mapToInt(LongToIntFunction mapper);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    LongStream stream();

    /**
     * Returns an array containing the elements of this tuple.
     *
     * @return The array representation of this tuple
     */
    long[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Iterator<Long> iterator();

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
