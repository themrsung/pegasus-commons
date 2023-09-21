package pegasus.tuple;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A triple of objects.
 *
 * @param a   The first object
 * @param b   The second object
 * @param c   The third object
 * @param <A> The first object's type
 * @param <B> The second object's type
 * @param <C> The third object's type
 * @see Tuple
 */
public record Triple<A, B, C>(A a, B b, C c) implements Tuple<Object> {
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return 3;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(Object value) {
        return Objects.equals(a, value) || Objects.equals(b, value) || Objects.equals(c, value);
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
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
            case 2 -> c;
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
        return Tuple.of(mapper.apply(a), mapper.apply(b), mapper.apply(c));
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
    public LongTuple mapToLong(ToLongFunction<? super Object> mapper) {
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
    public IntTuple mapToInt(ToIntFunction<? super Object> mapper) {
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
        return Tuple.of((U) a, (U) b, (U) c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Object> stream() {
        return Stream.of(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return new Object[]{a, b, c};
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
        return Objects.hash(a, b, c);
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
        if (t.size() != 3) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1)) && Objects.equals(c, t.get(2));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + a + ", " + b + ", " + "]";
    }
}
