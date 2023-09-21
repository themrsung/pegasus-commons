package pegasus.geometry;

import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;
import pegasus.tuple.Tuple;

/**
 * A non-rotatable two-dimensional square represented by two boundary vectors.
 */
public class BoundingSquare {
    public static BoundingSquare of(Vector2... vertices) {
        return new BoundingSquare(Tensors.min(vertices), Tensors.max(vertices));
    }

    public BoundingSquare(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
    }

    public BoundingSquare(BoundingSquare other) {
        this.min = other.min;
        this.max = other.max;
    }

    protected final Vector2 min;
    protected final Vector2 max;

    public boolean contains(Vector2 v) {
        return Tensors.isInRange(v, min, max);
    }

    public Vector2 min() {
        return min;
    }

    public Vector2 max() {
        return max;
    }

    public Tuple<Vector2> corners() {
        return Tuple.of(
                min,
                new Vector2(min.x(), max.y()),
                new Vector2(max.x(), min.y()),
                max
        );
    }

    @Override
    public String toString() {
        return "BoundingSquare{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
