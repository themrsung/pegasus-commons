package pegasus.geometry;

import pegasus.tensor.Vector2;

public interface Ray2D extends Ray<Vector2> {
    Vector2 origin();

    Vector2 direction();

    Vector2 destination(double t);
}
