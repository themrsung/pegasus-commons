package pegasus.container;

import pegasus.function.LongToFloatFunction;

import java.util.function.*;
import java.util.stream.LongStream;

/**
 * A value-based primitive container of a {@code long} value.
 *
 * @see BaseContainer
 */
public interface LongContainer extends BaseContainer<Long> {
    static LongContainer of() {
        return null;
    }

    static LongContainer of(long value) {
        return null;
    }

    /**
     * Returns the value of this container.
     *
     * @return The value of this container
     */
    long get();

    /**
     * Sets the value of this container.
     *
     * @param value The value of this container
     */
    void set(long value);

    /**
     * Updates the value of this container.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void update(LongUnaryOperator operator);

    /**
     * Applies the provided accumulator function to this container's value and the provided
     * identity value, then sets the value of this container to the return value of the provided
     * accumulator function.
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException When the provided accumulator function is {@code null}
     */
    void accumulate(long next, LongBinaryOperator accumulator);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting value
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    long mapToValue(LongUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongContainer map(LongUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    The type of object to map this container's value to
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectContainer<U> mapToObj(LongFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleContainer mapToDouble(LongToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    FloatContainer mapToFloat(LongToFloatFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntContainer mapToInt(LongToIntFunction mapper);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting value
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    long mergeToValue(LongContainer c, LongBinaryOperator merger);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns a new container containing the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting container
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    LongContainer merge(LongContainer c, LongBinaryOperator merger);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    LongStream stream();

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
