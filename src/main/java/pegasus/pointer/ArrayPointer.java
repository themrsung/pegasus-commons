package pegasus.pointer;

import pegasus.exception.IncompatibleDimensionsException;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * An array-based generic pointer.
 *
 * @param <T> The type of object to reference
 * @see ObjectPointer
 */
public class ArrayPointer<T> implements ObjectPointer<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array pointer.
     *
     * @param size The size of this pointer
     */
    @SuppressWarnings("unchecked")
    public ArrayPointer(int size) {
        this.values = (T[]) new Object[size];
    }

    /**
     * Creates a new array pointer.
     *
     * @param size      The size of this pointer
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    public ArrayPointer(int size, IntFunction<? extends T> generator) {
        this(size);
        setAll(generator);
    }

    /**
     * Creates a new array pointer.
     *
     * @param values The array of which to reference
     * @throws NullPointerException When the provided array is {@code null}
     */
    public ArrayPointer(T[] values) {
        this.values = Objects.requireNonNull(values);
    }

    /**
     * The internal array of values.
     */
    protected final T[] values;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        return stream().anyMatch(v -> Objects.equals(v, value));
    }

    /**
     * {@inheritDoc}
     *
     * @param p The pointer of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(ObjectPointer<? extends T> p) {
        return p.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the value to retrieve
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @param i     The index of the value to set
     * @param value The value of which to set to
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(int i, T value) throws IndexOutOfBoundsException {
        values[i] = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to query
     * @return {@inheritDoc}
     */
    @Override
    public int firstIndexOf(T value) {
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], value)) return i;
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to query
     * @return {@inheritDoc}
     */
    @Override
    public int lastIndexOf(T value) {
        for (int i = (values.length - 1); i >= 0; i--) {
            if (Objects.equals(values[i], value)) return i;
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to query
     * @return {@inheritDoc}
     */
    @Override
    public Set<Integer> indicesOf(T value) {
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], value)) result.add(i);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to fill this pointer with
     */
    @Override
    public void fill(T value) {
        Arrays.fill(values, value);
    }

    /**
     * {@inheritDoc}
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param p          The source pointer of which to retrieve values from
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void fillRange(int startIndex, int endIndex, ObjectPointer<? extends T> p)
            throws IndexOutOfBoundsException {
        for (int i = startIndex; i < endIndex; i++) {
            values[i] = p.get(i - startIndex);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param generator The generator function of which to use to populate this pointer
     */
    @Override
    public void setAll(IntFunction<? extends T> generator) {
        Arrays.setAll(values, generator);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply to each value of this pointer
     */
    @Override
    public void replace(UnaryOperator<T> operator) {
        Arrays.setAll(values, i -> operator.apply(values[i]));
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    @Override
    public void replaceFirst(T oldValue, T newValue) {
        for (int i = 0; i < values.length; i++) {
            if (!(Objects.equals(values[i], oldValue))) continue;
            values[i] = newValue;
            return;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    @Override
    public void replaceLast(T oldValue, T newValue) {
        for (int i = (values.length - 1); i >= 0; i--) {
            if (!(Objects.equals(values[i], oldValue))) continue;
            values[i] = newValue;
            return;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    @Override
    public void replaceAll(T oldValue, T newValue) {
        for (int i = 0; i < values.length; i++) {
            if (!(Objects.equals(values[i], oldValue))) continue;
            values[i] = newValue;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> ObjectPointer<U> map(Function<? super T, ? extends U> mapper) {
        return ObjectPointer.to((U[]) stream().map(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoublePointer mapToDouble(ToDoubleFunction<? super T> mapper) {
        return DoublePointer.to(stream().mapToDouble(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongPointer mapToLong(ToLongFunction<? super T> mapper) {
        return LongPointer.to(stream().mapToLong(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntPointer mapToInt(ToIntFunction<? super T> mapper) {
        return IntPointer.to(stream().mapToInt(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param target The target class of which to cast to
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> ObjectPointer<U> cast(Class<? extends U> target) throws ClassCastException {
        return ObjectPointer.to((U[]) values);
    }

    /**
     * {@inheritDoc}
     *
     * @param p      The pointer of which to merge this pointer with
     * @param merger The merger function of which to handle the merging of the two pointers
     * @param <U>    {@inheritDoc}
     * @param <V>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IncompatibleDimensionsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public <U, V> ObjectPointer<V> merge(ObjectPointer<U> p, BiFunction<? super T, ? super U, ? extends V> merger)
            throws IncompatibleDimensionsException {
        if (values.length != p.size()) {
            throw new IncompatibleDimensionsException();
        }

        return new ArrayPointer<>(values.length, i -> merger.apply(values[i], p.get(i)));
    }

    /**
     * {@inheritDoc}
     *
     * @param size The size of which to resize to
     * @return {@inheritDoc}
     */
    @Override
    public ObjectPointer<T> resize(int size) {
        return ObjectPointer.to(Arrays.copyOfRange(values, 0, size));
    }

    /**
     * {@inheritDoc}
     *
     * @throws ClassCastException {@inheritDoc}
     */
    @Override
    public void sort() throws ClassCastException {
        Pointers.sortArray(values);
    }

    /**
     * {@inheritDoc}
     *
     * @param sorter The sorter function of which to handle the sorting of the values
     */
    @Override
    public void sort(Comparator<? super T> sorter) {
        Arrays.sort(values, sorter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        Arrays.sort(values, Pointers::reverseCompare);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public T[] asArray() {
        return values;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash((Object) values);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectPointer<?> p)) return false;
        return Arrays.equals(values, p.asArray());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
