package pegasus.tuple;

import pegasus.container.ArrayElementReference;
import pegasus.container.ObjectContainer;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * An array-based tuple.
 *
 * @param <T> The type of object this tuple is to hold
 * @see Tuple
 */
public class ArrayTuple<T> implements Tuple<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array tuple.
     *
     * @param values The values of which to contain
     * @throws NullPointerException When the provided array is {@code null}
     */
    public ArrayTuple(T[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    /**
     * Creates a new array tuple.
     *
     * @param s The stream of which to retrieve elements from
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public ArrayTuple(Stream<? extends T> s) {
        this.values = (T[]) s.toArray();
    }

    /**
     * Creates a new array tuple.
     *
     * @param t The tuple of which to copy elements from
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    protected ArrayTuple(Tuple<? extends T> t) {
        this.values = t.toArray();
    }

    /**
     * Direct assignment constructor.
     *
     * @param values  The array to assign directly
     * @param ignored Ignored
     */
    private ArrayTuple(T[] values, boolean ignored) {
        this.values = values;
    }

    /**
     * The internal array of values.
     */
    private final T[] values;

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
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(Tuple<? extends T> t) {
        return t.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the element to get
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
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public ObjectContainer<T> getReference(int i) throws IndexOutOfBoundsException {
        return new ArrayElementReference<>(values, i, true);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> Tuple<U> map(Function<? super T, ? extends U> mapper) {
        return Tuple.from(stream().map(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleTuple mapToDouble(ToDoubleFunction<? super T> mapper) {
        return DoubleTuple.from(stream().mapToDouble(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongTuple mapToLong(ToLongFunction<? super T> mapper) {
        return LongTuple.from(stream().mapToLong(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntTuple mapToInt(ToIntFunction<? super T> mapper) {
        return IntTuple.from(stream().mapToInt(mapper));
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
    public <U> Tuple<U> cast(Class<? extends U> target) {
        return new ArrayTuple<>((U[]) values, false);
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
    public T[] toArray() {
        return Arrays.copyOf(values, values.length);
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
        if (!(obj instanceof Tuple<?> t)) return false;
        if (values.length != t.size()) return false;

        for (int i = 0; i < values.length; i++) {
            if (!(Objects.equals(values[i], t.get(i)))) return false;
        }

        return true;
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
