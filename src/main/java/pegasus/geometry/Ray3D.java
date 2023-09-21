package pegasus.geometry;

import pegasus.tensor.Vector3;

public interface Ray3D extends Ray<Vector3> {
    Vector3 origin();

    Vector3 direction();

    Vector3 destination(double t);
}
