package pegasus.pointer;

import pegasus.container.IntContainer;
import pegasus.exception.IncompatibleDimensionsException;
import pegasus.function.IntComparator;
import pegasus.util.IndexedIntIterable;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A value-based primitive pointer of {@code int} values.
 *
 * @see BasePointer
 * @see IntArrayPointer
 */
public interface IntPointer extends BasePointer<Integer>, IndexedIntIterable {
    /**
     * Creates a new pointer referencing the provided array of values.
     *
     * @param values The values of which to reference
     * @return The constructed pointer
     * @throws NullPointerException When the provided array is {@code null}
     */
    static IntPointer to(int... values) {
        return new IntArrayPointer(values);
    }

    /**
     * Creates a new pointer from the values of the provided stream {@code s}.
     *
     * @param s The stream of which to retrieve the values from
     * @return The constructed pointer
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static IntPointer from(IntStream s) {
        return new IntArrayPointer(s.toArray());
    }

    /**
     * Creates and returns a shallow copy of the provided pointer {@code p}.
     *
     * @param p The pointer of which to copy
     * @return A shallow copy of the provided pointer {@code p}
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    static IntPointer copyOf(IntPointer p) {
        return from(p.stream());
    }

    /**
     * Creates and returns a shallow copy of the provided pointer {@code p}'s values within
     * the range of {@code [from, to)}.
     *
     * @param p    The pointer of which to copy
     * @param from The index at which to start the copy at (inclusive)
     * @param to   The index at which to stop the copy at (exclusive)
     * @return A shallow copy of the provided pointer {@code p}
     * @throws NullPointerException      When the provided pointer {@code p} is {@code null}
     * @throws IllegalArgumentException  When the range is invalid
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    static IntPointer copyOfRange(IntPointer p, int from, int to) {
        return to(Arrays.copyOfRange(p.asArray(), from, to));
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
    boolean contains(int value);

    /**
     * Returns whether this pointer contains every value of the provided pointer {@code p}.
     *
     * @param p The pointer of which to check for containment
     * @return {@code true} if this pointer contains every value of the provided pointer {@code p}
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    boolean containsAll(IntPointer p);

    /**
     * Returns the {@code i}th value of this pointer.
     *
     * @param i The index of the value to retrieve
     * @return The {@code i}th value of this pointer
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    int get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns a reference to the {@code i}th value of this pointer.
     *
     * @param i The index of which to reference
     * @return A reference to the {@code i}th value of this pointer
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    IntContainer getReference(int i) throws IndexOutOfBoundsException;

    /**
     * Sets the {@code i}th value of this pointer.
     *
     * @param i     The index of the value to set
     * @param value The value of which to set to
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    void set(int i, int value) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The first index of the provided value
     */
    int firstIndexOf(int value);

    /**
     * Returns the index of the last occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The last index of the provided value
     */
    int lastIndexOf(int value);

    /**
     * Returns a set containing every index of the provided value.
     *
     * @param value The value of which to query
     * @return A set of every index of the provided value
     */
    Set<Integer> indicesOf(int value);

    /**
     * Fills this pointer with the provided value.
     *
     * @param value The value of which to fill this pointer with
     */
    void fill(int value);

    /**
     * Fills the specified range of this pointer with the provided value.
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param value      The value of which to fill the specified range with
     * @throws IndexOutOfBoundsException When the range is invalid, or is out of bounds
     */
    void fillRange(int startIndex, int endIndex, int value) throws IndexOutOfBoundsException;

    /**
     * Fills the specified range of this pointer with the provided values.
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param p          The source pointer of which to retrieve values from
     * @throws IndexOutOfBoundsException When the range is invalid, or is out of bounds
     * @throws NullPointerException      When the provided pointer is {@code null}
     */
    void fillRange(int startIndex, int endIndex, IntPointer p)
            throws IndexOutOfBoundsException;

    /**
     * Sets all values of this pointer using the provided generator function.
     *
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    void setAll(IntUnaryOperator generator);

    /**
     * Applies the provided update function to each value of this pointer, then assigns each
     * value to the return value of the provided update function.
     *
     * @param operator The update function of which to apply to each value of this pointer
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void replace(IntUnaryOperator operator);

    /**
     * Replaces only the first instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceFirst(int oldValue, int newValue);

    /**
     * Replaces only the last instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceLast(int oldValue, int newValue);

    /**
     * Replaces all instances of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceAll(int oldValue, int newValue);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntPointer map(IntUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @param <U>    The type of object to map this pointer to
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectPointer<U> mapToObj(IntFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoublePointer mapToDouble(IntToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongPointer mapToLong(IntToLongFunction mapper);

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
    IntPointer merge(IntPointer p, IntBinaryOperator merger)
            throws IncompatibleDimensionsException;

    /**
     * Returns a new pointer with of the provided size whose values are populated from that of this
     * pointer's values, trimmed or padded with {@code 0} values as necessary.
     *
     * @param size The size of which to resize to
     * @return The resized pointer
     * @throws IllegalArgumentException When the provided size is negative
     */
    IntPointer resize(int size);

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
    void sort(IntComparator sorter);

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
    IntStream stream();

    /**
     * Extracts the internal array of this pointer, and returns a direct reference.
     *
     * @return A direct reference to the internal array of this pointer
     */
    int[] asArray();

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
