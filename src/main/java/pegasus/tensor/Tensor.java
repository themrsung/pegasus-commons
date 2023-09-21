package pegasus.tensor;

import java.io.Serializable;
import java.util.stream.DoubleStream;

/**
 * A mathematical tensor.
 *
 * @see Vector
 * @see Matrix
 */
public interface Tensor extends Serializable {
    /**
     * Returns the size of this tensor.
     *
     * @return The number of scalar components this tensor contains
     */
    int size();

    /**
     * Returns whether this tensor is not a number.
     *
     * @return {@code true} if this tensor contains at least one component whose
     * value is not a number
     */
    boolean isNaN();

    /**
     * Returns whether this tensor is finite.
     *
     * @return {@code true} if every component of this tensor is confirmed to be finite
     */
    boolean isFinite();

    /**
     * Returns whether this tensor is infinite.
     *
     * @return {@code true} if this tensor contains at least one component whose
     * value is infinite
     */
    boolean isInfinite();

    /**
     * Returns a stream whose source is the scalar components of this tensor.
     *
     * @return A stream of this tensor
     */
    DoubleStream stream();

    /**
     * Returns an array containing the scalar components this tensor.
     *
     * @return The array representation of this tensor
     */
    double[] toArray();

    /**
     * Returns the hash code of this tensor.
     *
     * @return The hash code of this tensor
     */
    int hashCode();

    /**
     * Checks for equality between this tensor and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a tensor of the same type,
     * and the dimensions and component values are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this tensor into a string.
     *
     * @return The string representation of this tensor
     */
    String toString();
}
