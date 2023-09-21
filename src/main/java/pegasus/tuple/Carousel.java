package pegasus.tuple;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * A fixed-size, shallowly immutable infinite queue of elements.
 *
 * @param <T> The type of element to iterate over
 * @see Tuple
 */
public interface Carousel<T> extends Tuple<T> {
    /**
     * Creates and returns a new carousel.
     *
     * @param values The values of which to contain
     * @param <T>    The type of element to contain
     * @return The constructed carousel
     */
    @SafeVarargs
    static <T> Carousel<T> of(T... values) {
        return new ArrayCarousel<>(values);
    }

    /**
     * Creates and returns a copy of the provided tuple {@code t}.
     *
     * @param t   The tuple of which to copy
     * @param <T> The type of element to copy
     * @return The copied carousel
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    static <T> Carousel<T> copyOf(Tuple<? extends T> t) {
        return new ArrayCarousel<>(t);
    }

    /**
     * Creates and returns a new carousel from the provided stream's elements.
     *
     * @param s   The stream of which to copy elements from
     * @param <T> The type of element to copy
     * @return The constructed carousel
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    static <T> Carousel<T> from(Stream<? extends T> s) {
        return new ArrayCarousel<>(s);
    }

    /**
     * Peeks the next element in line, without changing the internal state of this carousel.
     * This is a pure operation.
     *
     * @return The next element in line
     * @throws NoSuchElementException When this carousel is empty
     */
    T peek() throws NoSuchElementException;

    /**
     * Returns the next element in line, then circularly increments the internal counter of
     * this carousel. This is an impure operation.
     *
     * @return The next element in line
     * @throws NoSuchElementException When this carousel is empty
     */
    T next() throws NoSuchElementException;
}
