package pegasus.tuple;

import java.io.Serial;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * An array-based carousel implementation.
 *
 * @param <T> The type of element of which to iterate over
 * @see Tuple
 * @see Carousel
 */
class ArrayCarousel<T> extends ArrayTuple<T> implements Carousel<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array carousel.
     *
     * @param values The values of which to contain
     */
    @SafeVarargs
    protected ArrayCarousel(T... values) {
        super(values);
        this.i = 0;
    }

    /**
     * Creates a new array carousel.
     *
     * @param s The stream of which to copy element values from
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    public ArrayCarousel(Stream<? extends T> s) {
        super(s);
        this.i = 0;
    }

    /**
     * Creates a new array carousel.
     *
     * @param t The tuple of which to copy element values from
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public ArrayCarousel(Tuple<? extends T> t) {
        super(t);
        this.i = 0;
    }

    /**
     * The internal counter of this carousel.
     */
    private transient volatile int i;

    /**
     * Returns the current iterator of this carousel.
     *
     * @return The current iterator of this carousel
     */
    protected int i() {
        return i;
    }

    /**
     * Returns and increments the iterator of this carousel.
     *
     * @return The current iterator of this carousel
     */
    protected synchronized int ipp() {
        int current = i;
        i = (i + 1) % size();
        return current;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public T peek() throws NoSuchElementException {
        try {
            return get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Element at index " + i + " not found.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public T next() throws NoSuchElementException {
        try {
            return get(ipp());
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Element at index " + i + " not found.");
        }
    }
}
