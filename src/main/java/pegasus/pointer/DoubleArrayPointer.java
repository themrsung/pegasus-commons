package pegasus.pointer;

import pegasus.container.DoubleArrayElementReference;
import pegasus.container.DoubleContainer;
import pegasus.exception.IncompatibleDimensionsException;
import pegasus.function.DoubleComparator;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * An array-based {@code double} pointer.
 *
 * @see DoublePointer
 */
public class DoubleArrayPointer implements DoublePointer {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array pointer.
     *
     * @param size The size of this pointer
     */
    public DoubleArrayPointer(int size) {
        this.values = new double[size];
    }

    /**
     * Creates a new array pointer.
     *
     * @param size      The size of this pointer
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    public DoubleArrayPointer(int size, IntToDoubleFunction generator) {
        this(size);
        setAll(generator);
    }

    /**
     * Creates a new array pointer.
     *
     * @param values The array of which to reference
     * @throws NullPointerException When the provided array is {@code null}
     */
    public DoubleArrayPointer(double[] values) {
        this.values = Objects.requireNonNull(values);
    }

    /**
     * The internal array of values.
     */
    protected final double[] values;

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
    public boolean contains(double value) {
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
    public boolean containsAll(DoublePointer p) {
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
    public double get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of which to reference
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public DoubleContainer getReference(int i) throws IndexOutOfBoundsException {
        return new DoubleArrayElementReference(values, i, false);
    }

    /**
     * {@inheritDoc}
     *
     * @param i     The index of the value to set
     * @param value The value of which to set to
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(int i, double value) throws IndexOutOfBoundsException {
        values[i] = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to query
     * @return {@inheritDoc}
     */
    @Override
    public int firstIndexOf(double value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) return i;
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
    public int lastIndexOf(double value) {
        for (int i = (values.length - 1); i >= 0; i--) {
            if (values[i] == value) return i;
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
    public Set<Integer> indicesOf(double value) {
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) result.add(i);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to fill this pointer with
     */
    @Override
    public void fill(double value) {
        Arrays.fill(values, value);
    }

    /**
     * {@inheritDoc}
     *
     * @param startIndex The starting index at which to start filling values (inclusive)
     * @param endIndex   The ending index at which to stop filling values (exclusive)
     * @param value      The value of which to fill the specified range with
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void fillRange(int startIndex, int endIndex, double value) throws IndexOutOfBoundsException {
        Arrays.fill(values, startIndex, endIndex, value);
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
    public void fillRange(int startIndex, int endIndex, DoublePointer p)
            throws IndexOutOfBoundsException {
        for (int i = startIndex; i < endIndex; i++) {
            values[i] = p.get(i - startIndex);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param generator The generator function of which to use to populate this pointer
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void setAll(IntToDoubleFunction generator) {
        Arrays.setAll(values, generator);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply to each value of this pointer
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void replace(DoubleUnaryOperator operator) {
        Arrays.setAll(values, i -> operator.applyAsDouble(values[i]));
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    @Override
    public void replaceFirst(double oldValue, double newValue) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] != oldValue) continue;
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
    public void replaceLast(double oldValue, double newValue) {
        for (int i = (values.length - 1); i >= 0; i--) {
            if (values[i] != oldValue) continue;
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
    public void replaceAll(double oldValue, double newValue) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] != oldValue) continue;
            values[i] = newValue;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoublePointer map(DoubleUnaryOperator mapper) {
        return DoublePointer.to(stream().map(mapper).toArray());
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
    public <U> ObjectPointer<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return ObjectPointer.to((U[]) stream().mapToObj(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each value of this pointer
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongPointer mapToLong(DoubleToLongFunction mapper) {
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
    public IntPointer mapToInt(DoubleToIntFunction mapper) {
        return IntPointer.to(stream().mapToInt(mapper).toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param p      The pointer of which to merge this pointer with
     * @param merger The merger function of which to handle the merging of the two pointers
     * @return {@inheritDoc}
     * @throws IncompatibleDimensionsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public DoublePointer merge(DoublePointer p, DoubleBinaryOperator merger)
            throws IncompatibleDimensionsException {
        return new DoubleArrayPointer(values.length, i -> merger.applyAsDouble(values[i], p.get(i)));
    }

    /**
     * {@inheritDoc}
     *
     * @param size The size of which to resize to
     * @return {@inheritDoc}
     */
    @Override
    public DoublePointer resize(int size) {
        return DoublePointer.to(Arrays.copyOfRange(values, 0, size));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort() {
        Arrays.sort(values);
    }

    /**
     * {@inheritDoc}
     *
     * @param sorter The sorter function of which to handle the sorting of the values
     */
    @Override
    public void sort(DoubleComparator sorter) {
        Pointers.sortArray(values, sorter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        Pointers.sortArray(values, Pointers::reverseCompare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        Pointers.shuffleArray(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public DoubleStream stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double[] asArray() {
        return values;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<Double> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressWarnings("RedundantCast")
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
        if (!(obj instanceof DoublePointer p)) return false;
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
