package pegasus.geometry;

import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;
import pegasus.tuple.Tuple;

import java.util.Objects;

/**
 * A non-rotatable two-dimensional square represented by two boundary vectors.
 */
public class BoundingSquare {
    /**
     * Creates a bounding square of the provided vertices.
     *
     * @param vertices The vertices of which to represent
     * @return The created bounding square
     * @throws NullPointerException When the provided array is {@code null}, or it
     *                              contains a {@code null} vector
     */
    public static BoundingSquare of(Vector2... vertices) {
        return new BoundingSquare(Tensors.min(vertices), Tensors.max(vertices));
    }

    /**
     * Creates a new bounding square
     *
     * @param min The minimum boundary vector
     * @param max The maximum boundary vector
     * @throws NullPointerException When either of the provided boundary vectors is {@code null}
     */
    public BoundingSquare(Vector2 min, Vector2 max) {
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    /**
     * Creates a new bounding square.
     *
     * @param other The square of which to copy boundary vectors from
     * @throws NullPointerException When the provided boundary square is {@code null}
     */
    public BoundingSquare(BoundingSquare other) {
        this.min = other.min;
        this.max = other.max;
    }

    /**
     * The minimum boundary.
     */
    protected final Vector2 min;

    /**
     * The maximum boundary.
     */
    protected final Vector2 max;

    /**
     * Returns whether this bounding square contains the provided vertex {@code v}.
     *
     * @param v The vertex of which to check for containment
     * @return {@code true} if this bounding square contains the provided vertex {@code v}
     * @throws NullPointerException When the provided vertex {@code v} is {@code null}
     */
    public boolean contains(Vector2 v) {
        return Tensors.isInRange(v, min, max);
    }

    /**
     * Returns the minimum boundary vector of this bounding square.
     *
     * @return The minimum boundary vector of this bounding square
     */
    public Vector2 min() {
        return min;
    }

    /**
     * Returns the maximum boundary vector of this bounding square.
     *
     * @return The maximum boundary vector of this bounding square
     */
    public Vector2 max() {
        return max;
    }


    /**
     * Returns a tuple containing the corners of this bounding square.
     *
     * @return A tuple of the corners of this bounding square
     */
    public Tuple<Vector2> corners() {
        return Tuple.of(
                min,
                new Vector2(min.x(), max.y()),
                new Vector2(max.x(), min.y()),
                max
        );
    }


    /**
     * Serializes this bounding square into a string.
     *
     * @return The string representation of this bounding square
     */
    @Override
    public String toString() {
        return "BoundingSquare{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
