package pegasus.pointer;

import pegasus.exception.IncompatibleDimensionsException;
import pegasus.function.DoubleComparator;
import pegasus.util.IndexedDoubleIterable;

import java.util.Iterator;
import java.util.Set;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * A value-based primitive pointer of {@code double} values.
 *
 * @see BasePointer
 * @see DoubleArrayPointer
 */
public interface DoublePointer extends BasePointer<Double>, IndexedDoubleIterable {
    /**
     * Creates a new pointer referencing the provided array of values.
     *
     * @param values The values of which to reference
     * @return The constructed pointer
     * @throws NullPointerException When the provided array is {@code null}
     */
    static DoublePointer to(double... values) {
        return new DoubleArrayPointer(values);
    }

    /**
     * Creates a new pointer from the values of the provided stream {@code s}.
     *
     * @param s The stream of which to retrieve the values from
     * @return The constructed pointer
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static DoublePointer from(DoubleStream s) {
        return new DoubleArrayPointer(s.toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int size();

    /**
     * Returns whether this pointer contains the provided value.
     *
     * @param value The value of which to check for containment
     * @return {@code true} if this pointer contains the provided value
     */
    boolean contains(double value);

    /**
     * Returns whether this pointer contains every value of the provided pointer {@code p}.
     *
     * @param p The pointer of which to check for containment
     * @return {@code true} if this pointer contains every value of the provided pointer {@code p}
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    boolean containsAll(DoublePointer p);

    /**
     * Returns the {@code i}th value of this pointer.
     *
     * @param i The index of the value to retrieve
     * @return The {@code i}th value of this pointer
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    double get(int i) throws IndexOutOfBoundsException;

    /**
     * Sets the {@code i}th value of this pointer.
     *
     * @param i     The index of the value to set
     * @param value The value of which to set to
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    void set(int i, double value) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The first index of the provided value
     */
    int firstIndexOf(double value);

    /**
     * Returns the index of the last occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The last index of the provided value
     */
    int lastIndexOf(double value);

    /**
     * Returns a set containing every index of the provided value.
     *
     * @param value The value of which to query
     * @return A set of every index of the provided value
     */
    Set<Integer> indicesOf(double value);

    /**
     * Fills this pointer with the provided value.
     *
     * @param value The value of which to fill this pointer with
     */
    void fill(double value);

    /**
     * Fills the specified range of this pointer with the provided values.
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param p          The source pointer of which to retrieve values from
     * @throws IndexOutOfBoundsException When the range is invalid, or is out of bounds
     * @throws NullPointerException      When the provided pointer is {@code null}
     */
    void fillRange(int startIndex, int endIndex, DoublePointer p)
            throws IndexOutOfBoundsException;

    /**
     * Sets all values of this pointer using the provided generator function.
     *
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    void setAll(IntToDoubleFunction generator);

    /**
     * Applies the provided update function to each value of this pointer, then assigns each
     * value to the return value of the provided update function.
     *
     * @param operator The update function of which to apply to each value of this pointer
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void replace(DoubleUnaryOperator operator);

    /**
     * Replaces only the first instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceFirst(double oldValue, double newValue);

    /**
     * Replaces only the last instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceLast(double oldValue, double newValue);

    /**
     * Replaces all instances of ths old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceAll(double oldValue, double newValue);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of ths provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoublePointer map(DoubleUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of ths provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @param <U>    The type of object to map this pointer to
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectPointer<U> mapToObj(DoubleFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of ths provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongPointer mapToLong(DoubleToLongFunction mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of ths provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntPointer mapToInt(DoubleToIntFunction mapper);

    /**
     * Applies the provided merger function to each corresponding pair of values between this pointer
     * and the provided pointer {@code p}, then returns a new pointer whose values are populated from that
     * of the return values of the provided merger function.
     *
     * @param p      The pointer of which to merge this pointer with
     * @param merger The merger function of which to handle the merging of the two pointers
     * @return The resulting pointer
     * @throws IncompatibleDimensionsException When the pointers' sizes are different
     */
    DoublePointer merge(DoublePointer p, DoubleBinaryOperator merger)
            throws IncompatibleDimensionsException;

    /**
     * Returns a new pointer with of the provided size whose values are populated from that of this
     * pointer's values, trimmed or padded with {@code 0} values as necessary.
     *
     * @param size The size of which to resize to
     * @return The resized pointer
     * @throws IllegalArgumentException When the provided size is negative
     */
    DoublePointer resize(int size);

    /**
     * Sorts this pointer's values in their {@link Comparable natural ascending order}.
     *
     * @throws NullPointerException When the pointer contains {@code null} values
     */
    void sort();

    /**
     * Sorts this pointer's values in accordance to the provided sorter function.
     *
     * @param sorter The sorter function of which to handle the sorting of the values
     * @throws NullPointerException When the provided sorter function is {@code null}
     */
    void sort(DoubleComparator sorter);

    /**
     * Reverses the order of this pointer. For instance, the pointer {@code ["hello", "world"]}
     * will become {@code ["world", "hello"]}.
     */
    void reverse();

    /**
     * Shuffles this pointer's values in a randomized manner.
     */
    void shuffle();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    DoubleStream stream();

    /**
     * Extracts the internal array of this pointer, and returns a direct reference.
     *
     * @return A direct reference to the internal array of this pointer
     */
    double[] asArray();

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
