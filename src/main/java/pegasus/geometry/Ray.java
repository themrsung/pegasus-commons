package pegasus.geometry;

import pegasus.tensor.Vector;

public interface Ray<V extends Vector<V>> {
    V origin();

    V direction();

    V destination(double t);
}
