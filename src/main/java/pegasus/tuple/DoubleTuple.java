package pegasus.tuple;

import pegasus.util.IndexedDoubleIterable;

import java.util.Iterator;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * A value-based primitive tuple of {@code double} values.
 *
 * @see BaseTuple
 * @see DoublePair
 * @see DoubleTriple
 * @see DoubleArrayTuple
 */
public interface DoubleTuple extends BaseTuple<Double>, IndexedDoubleIterable {
    /**
     * Creates a new tuple from the provided array of values.
     *
     * @param values The values of which to contain
     * @return The created tuple
     * @throws NullPointerException When the provided array is {@code null}
     */
    static DoubleTuple of(double... values) {
        return switch (values.length) {
            case 2 -> new DoublePair(values[0], values[1]);
            case 3 -> new DoubleTriple(values[0], values[1], values[2]);
            default -> new DoubleArrayTuple(values);
        };
    }

    /**
     * Creates a new tuple from the provided stream of values.
     *
     * @param s The stream of which to retrieve elements from
     * @return The created tuple
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static DoubleTuple from(DoubleStream s) {
        return new DoubleArrayTuple(s);
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
    boolean contains(double value);

    /**
     * Returns whether this tuple contains every element of the provided tuple {@code t}.
     *
     * @param t The tuple of which to check for containment
     * @return {@code true} if this tuple contains every element of ths provided tuple {@code t}
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    boolean containsAll(DoubleTuple t);

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    double get(int i) throws IndexOutOfBoundsException;

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleTuple map(DoubleUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    The type of element of which to map this tuple to
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> Tuple<U> mapToObj(DoubleFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongTuple mapToLong(DoubleToLongFunction mapper);

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntTuple mapToInt(DoubleToIntFunction mapper);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    DoubleStream stream();

    /**
     * Returns an array containing the elements of this tuple.
     *
     * @return The array representation of this tuple
     */
    double[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Iterator<Double> iterator();

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
