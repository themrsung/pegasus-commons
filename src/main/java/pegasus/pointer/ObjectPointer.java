package pegasus.pointer;

import pegasus.exception.IncompatibleDimensionsException;
import pegasus.util.IndexedIterable;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A reference-based generic pointer.
 *
 * @param <T> The type of object to reference
 * @see BasePointer
 * @see ArrayPointer
 */
public interface ObjectPointer<T> extends BasePointer<T>, IndexedIterable<T> {
    /**
     * Creates a new pointer referencing the provided array of values.
     *
     * @param values The values of which to reference
     * @param <T>    The type of value to reference
     * @return The constructed pointer
     * @throws NullPointerException When the provided array is {@code null}
     */
    @SafeVarargs
    static <T> ObjectPointer<T> to(T... values) {
        return new ArrayPointer<>(values);
    }

    /**
     * Creates a new pointer from the values of the provided stream {@code s}.
     *
     * @param s The stream of which to retrieve the values from
     * @return The constructed pointer
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    @SuppressWarnings("unchecked")
    static <T> ObjectPointer<T> from(Stream<? extends T> s) {
        return new ArrayPointer<>((T[]) s.toArray());
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
    boolean contains(T value);

    /**
     * Returns whether this pointer contains every value of the provided pointer {@code p}.
     *
     * @param p The pointer of which to check for containment
     * @return {@code true} if this pointer contains every value of the provided pointer {@code p}
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    boolean containsAll(ObjectPointer<? extends T> p);

    /**
     * Returns the {@code i}th value of this pointer.
     *
     * @param i The index of the value to retrieve
     * @return The {@code i}th value of this pointer
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    T get(int i) throws IndexOutOfBoundsException;

    /**
     * Sets the {@code i}th value of this pointer.
     *
     * @param i     The index of the value to set
     * @param value The value of which to set to
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    void set(int i, T value) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The first index of the provided value
     */
    int firstIndexOf(T value);

    /**
     * Returns the index of the last occurrence of the provided value. If no instances are found,
     * this will return {@code -1}.
     *
     * @param value The value of which to query
     * @return The last index of the provided value
     */
    int lastIndexOf(T value);

    /**
     * Returns a set containing every index of the provided value.
     *
     * @param value The value of which to query
     * @return A set of every index of the provided value
     */
    Set<Integer> indicesOf(T value);

    /**
     * Fills this pointer with the provided value.
     *
     * @param value The value of which to fill this pointer with
     */
    void fill(T value);

    /**
     * Fills the specified range of this pointer with the provided value.
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param value      The value of which to fill the specified range with
     * @throws IndexOutOfBoundsException When the range is invalid, or is out of bounds
     */
    void fillRange(int startIndex, int endIndex, T value) throws IndexOutOfBoundsException;

    /**
     * Fills the specified range of this pointer with the provided values.
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param p          The source pointer of which to retrieve values from
     * @throws IndexOutOfBoundsException When the range is invalid, or is out of bounds
     * @throws NullPointerException      When the provided pointer is {@code null}
     */
    void fillRange(int startIndex, int endIndex, ObjectPointer<? extends T> p)
            throws IndexOutOfBoundsException;

    /**
     * Sets all values of this pointer using the provided generator function.
     *
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    void setAll(IntFunction<? extends T> generator);

    /**
     * Applies the provided update function to each value of this pointer, then assigns each
     * value to the return value of the provided update function.
     *
     * @param operator The update function of which to apply to each value of this pointer
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void replace(UnaryOperator<T> operator);

    /**
     * Replaces only the first instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceFirst(T oldValue, T newValue);

    /**
     * Replaces only the last instance of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceLast(T oldValue, T newValue);

    /**
     * Replaces all instances of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceAll(T oldValue, T newValue);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @param <U>    The type of object to map this pointer to
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectPointer<U> map(Function<? super T, ? extends U> mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoublePointer mapToDouble(ToDoubleFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongPointer mapToLong(ToLongFunction<? super T> mapper);

    /**
     * Applies the provided mapper function to each value of this pointer, then returns a new pointer
     * whose values are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntPointer mapToInt(ToIntFunction<? super T> mapper);

    /**
     * Casts the internal array of this pointer to an array of the provided target class, then returns
     * a pointer to the cast array. The resulting pointer will reference the same array as this pointer.
     *
     * @param target The target class of which to cast to
     * @param <U>    The class of which to cast to
     * @return The cast pointer
     * @throws ClassCastException   When the values of this pointer are not an instance of
     *                              the provided class {@code U}
     * @throws NullPointerException When the provided target class is {@code null}
     */
    <U> ObjectPointer<U> cast(Class<? extends U> target) throws ClassCastException;

    /**
     * Applies the provided merger function to each corresponding pair of values between this pointer
     * and the provided pointer {@code p}, then returns a new pointer whose values are populated from that
     * of the return values of the provided merger function.
     *
     * @param p      The pointer of which to merge this pointer with
     * @param merger The merger function of which to handle the merging of the two pointers
     * @param <U>    The type of object to merge this pointer with
     * @param <V>    The type of object to merge the pointers to
     * @return The resulting pointer
     * @throws IncompatibleDimensionsException When the pointers' sizes are different
     * @throws NullPointerException            When either the provided pointer {@code p} or the provided merger
     *                                         function is {@code null}
     */
    <U, V> ObjectPointer<V> merge(ObjectPointer<U> p, BiFunction<? super T, ? super U, ? extends V> merger)
            throws IncompatibleDimensionsException;

    /**
     * Returns a new pointer with of the provided size whose values are populated from that of this
     * pointer's values, trimmed or padded with {@code null} values as necessary.
     *
     * @param size The size of which to resize to
     * @return The resized pointer
     * @throws IllegalArgumentException When the provided size is negative
     */
    ObjectPointer<T> resize(int size);

    /**
     * Sorts this pointer's values in their {@link Comparable natural ascending order}.
     *
     * @throws ClassCastException   When the pointer's values are not {@link Comparable comparable}
     * @throws NullPointerException When the pointer contains {@code null} values
     */
    void sort() throws ClassCastException;

    /**
     * Sorts this pointer's values in accordance to the provided sorter function.
     *
     * @param sorter The sorter function of which to handle the sorting of the values
     * @throws NullPointerException When the provided sorter function is {@code null}
     */
    void sort(Comparator<? super T> sorter);

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
    Stream<T> stream();

    /**
     * Extracts the internal array of this pointer, and returns a direct reference.
     *
     * @return A direct reference to the internal array of this pointer
     */
    T[] asArray();

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
