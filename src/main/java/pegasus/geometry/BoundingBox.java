package pegasus.geometry;

import pegasus.tensor.Tensors;
import pegasus.tensor.Vector3;
import pegasus.tuple.Tuple;

import java.util.Objects;

/**
 * A non-rotatable three-dimensional box represented by two boundary vectors.
 */
public class BoundingBox {
    /**
     * Creates a bounding box of the provided vertices.
     *
     * @param vertices The vertices of which to represent
     * @return The created bounding box
     * @throws NullPointerException When the provided array is {@code null}, or it
     *                              contains a {@code null} vector
     */
    public static BoundingBox of(Vector3... vertices) {
        return new BoundingBox(Tensors.min(vertices), Tensors.max(vertices));
    }

    /**
     * Creates a new bounding box.
     *
     * @param min The minimum boundary vector
     * @param max The maximum boundary vector
     * @throws NullPointerException When either of the provided boundary vectors is {@code null}
     */
    public BoundingBox(Vector3 min, Vector3 max) {
        this.min = Objects.requireNonNull(min);
        this.max = Objects.requireNonNull(max);
    }

    /**
     * Creates a new bounding box.
     *
     * @param other The box of which to copy boundary vectors from
     * @throws NullPointerException When the provided boundary box is {@code null}
     */
    public BoundingBox(BoundingBox other) {
        this.min = other.min;
        this.max = other.max;
    }

    /**
     * The minimum boundary.
     */
    protected final Vector3 min;

    /**
     * The maximum boundary.
     */
    protected final Vector3 max;

    /**
     * Returns whether this bounding box contains the provided vertex {@code v}.
     *
     * @param v The vertex of which to check for containment
     * @return {@code true} if this bounding box contains the provided vertex {@code v}
     * @throws NullPointerException When the provided vertex {@code v} is {@code null}
     */
    public boolean contains(Vector3 v) {
        return Tensors.isInRange(v, min, max);
    }

    /**
     * Returns the minimum boundary vector of this bounding box.
     *
     * @return The minimum boundary vector of this bounding box
     */
    public Vector3 min() {
        return min;
    }

    /**
     * Returns the maximum boundary vector of this bounding box.
     *
     * @return The maximum boundary vector of this bounding box
     */
    public Vector3 max() {
        return max;
    }

    /**
     * Returns a tuple containing the corners of this bounding box.
     *
     * @return A tuple of the corners of this bounding box
     */
    public Tuple<Vector3> corners() {
        return Tuple.of(
                min,
                new Vector3(max.x(), min.y(), min.z()),
                new Vector3(min.x(), max.y(), min.z()),
                new Vector3(max.x(), max.y(), min.z()),
                new Vector3(min.x(), min.y(), max.z()),
                new Vector3(max.x(), min.y(), max.z()),
                new Vector3(min.x(), max.y(), max.z()),
                max
        );
    }

    /**
     * Serializes this bounding box into a string.
     *
     * @return The string representation of this bounding box
     */
    @Override
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
