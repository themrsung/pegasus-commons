package pegasus.tuple;

import java.io.Serial;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A pair of objects.
 *
 * @param a   The first object
 * @param b   The second object
 * @param <A> The first object's type
 * @param <B> The second object's type
 * @see Tuple
 */
public record Pair<A, B>(A a, B b) implements Tuple<Object> {
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
    public boolean contains(Object value) {
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
    public boolean containsAll(Tuple<?> t) {
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
    public Object get(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> a;
            case 1 -> b;
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
    public <U> Tuple<U> map(Function<? super Object, ? extends U> mapper) {
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
    public DoubleTuple mapToDouble(ToDoubleFunction<? super Object> mapper) {
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
    public LongTuple mapToLong(ToLongFunction<? super Object> mapper) {
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
    public IntTuple mapToInt(ToIntFunction<? super Object> mapper) {
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
    public Stream<Object> stream() {
        return Stream.of(a, b);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return new Object[]{a, b};
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<Object> iterator() {
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
    public boolean equalsIgnoreOrder(Pair<?, ?> other) {
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
