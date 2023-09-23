package pegasus.container;

import pegasus.function.*;

import java.util.stream.Stream;

/**
 * A value-based primitive container of a {@code float} value.
 *
 * @see BaseContainer
 */
public interface FloatContainer extends BaseContainer<Float> {
    static FloatContainer of() {
        return new FastFloatContainer(Float.NaN);
    }

    static FloatContainer of(float value) {
        return new FastFloatContainer(value);
    }

    /**
     * Returns whether this container's value is not a number.
     *
     * @return {@code true} if this container's value is not a number
     */
    boolean isNaN();

    /**
     * Returns whether this container's value is finite.
     *
     * @return {@code true} if container's value is finite
     */
    boolean isFinite();

    /**
     * Returns whether this container's value is infinite.
     *
     * @return {@code true} if this container's value is infinite
     */
    boolean isInfinite();

    /**
     * Returns the value of this container.
     *
     * @return The value of this container
     */
    float get();

    /**
     * Sets the value of this container.
     *
     * @param value The value of this container
     */
    void set(float value);

    /**
     * Updates the value of this container.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void update(FloatUnaryOperator operator);

    /**
     * Applies the provided accumulator function to this container's value and the provided
     * identity value, then sets the value of this container to the return value of the provided
     * accumulator function.
     *
     * @param next        The next value of which to accumulate this container's value with
     * @param accumulator The accumulator function of which to handle the accumulation
     * @throws NullPointerException When the provided accumulator function is {@code null}
     */
    void accumulate(float next, FloatBinaryOperator accumulator);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting value
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    float mapToValue(FloatUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    FloatContainer map(FloatUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    The type of object to map this container's value to
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectContainer<U> mapToObj(FloatFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleContainer mapToDouble(FloatToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongContainer mapToLong(FloatToLongFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntContainer mapToInt(FloatToIntFunction mapper);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting value
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    float mergeToValue(FloatContainer c, FloatBinaryOperator merger);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns a new container containing the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting container
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    FloatContainer merge(FloatContainer c, FloatBinaryOperator merger);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Stream<Float> stream();

    /**
     * Performs the provided action for this container's value.
     *
     * @param action The action to be performed for this container's value
     * @throws NullPointerException When the provided action is {@code null}
     */
    void peek(FloatConsumer action);

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
