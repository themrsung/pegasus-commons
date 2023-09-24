package pegasus.tuple;

import pegasus.container.ObjectContainer;
import pegasus.container.VariableReference;

import java.io.Serial;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A pair of objects of the same type.
 *
 * @param a   The first object
 * @param b   The second object
 * @param <T> The objects' type
 * @see Tuple
 */
public record UnaryPair<T>(T a, T b) implements Tuple<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 2;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        return Objects.equals(a, value) || Objects.equals(b, value);
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
        return switch (i) {
            case 0 -> a;
            case 1 -> b;
            default -> throw new IndexOutOfBoundsException(i);
        };
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
        return switch (i) {
            case 0 -> new VariableReference<>(() -> a, VariableReference.readOnlySetter());
            case 1 -> new VariableReference<>(() -> b, VariableReference.readOnlySetter());
            default -> throw new IndexOutOfBoundsException(i);
        };
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
        return Tuple.of(mapper.apply(a), mapper.apply(b));
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
        return DoubleTuple.of(mapper.applyAsDouble(a), mapper.applyAsDouble(b));
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
        return LongTuple.of(mapper.applyAsLong(a), mapper.applyAsLong(b));
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
        return IntTuple.of(mapper.applyAsInt(a), mapper.applyAsInt(b));
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
        return Tuple.of((U) a, (U) b);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return Stream.of(a, b);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) new Object[]{a, b};
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
        return Objects.hash(a, b);
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
        if (t.size() != 2) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1));
    }

    /**
     * Checks for equality without regard to order.
     *
     * @param other The other pair to compare to
     * @return {@code true} if the pairs are equal regardless of their elements' order
     */
    public boolean equalsIgnoreOrder(UnaryPair<?> other) {
        if (other == null) return false;
        return (Objects.equals(a, other.a) && Objects.equals(b, other.b)) &&
                (Objects.equals(a, other.b) && Objects.equals(b, other.a));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }
}
