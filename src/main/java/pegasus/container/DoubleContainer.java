package pegasus.container;

import pegasus.function.DoubleToFloatFunction;

import java.util.function.*;
import java.util.stream.DoubleStream;

/**
 * A value-based primitive container of a {@code double} value.
 *
 * @see BaseContainer
 */
public interface DoubleContainer extends BaseContainer<Double> {
    /**
     * Returns the value of this container.
     *
     * @return The value of this container
     */
    double get();

    /**
     * Sets the value of this container.
     *
     * @param value The value of this container
     */
    void set(double value);

    /**
     * Updates the value of this container.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void update(DoubleUnaryOperator operator);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting value
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    double mapToValue(DoubleUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleContainer map(DoubleUnaryOperator mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @param <U>    The type of object to map this container's value to
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> ObjectContainer<U> mapToObj(DoubleFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongContainer mapToLong(DoubleToLongFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    FloatContainer mapToFloat(DoubleToFloatFunction mapper);

    /**
     * Applies the provided mapper function to the value of this container, then returns
     * a new container containing the return value of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to this container's value
     * @return The resulting container
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntContainer mapToInt(DoubleToIntFunction mapper);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting value
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    double mergeToValue(DoubleContainer c, DoubleBinaryOperator merger);

    /**
     * Applies the provided merger function to this container's value and the provided
     * container {@code c}'s value, then returns a new container containing the resulting value.
     *
     * @param c      The container of which to merge this container with
     * @param merger The merger function of which to handle the merging of the two containers
     * @return The resulting container
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    DoubleContainer merge(DoubleContainer c, DoubleBinaryOperator merger);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    DoubleStream stream();

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
