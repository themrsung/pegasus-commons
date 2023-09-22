package pegasus.tensor;

import java.io.Serial;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

/**
 * A large array-based vector with an arbitrary size. Interoperability between
 * two instances of large vectors can only be guaranteed when the sizes are equal.
 *
 * @see Tensor
 * @see Vector
 * @see Vector2
 * @see Vector3
 * @see Vector4
 * @see Vector5
 * @see Vector6
 * @see Vector7
 * @see Vector8
 * @see Vector9
 * @see Vector10
 * @see Vector11
 * @see Vector12
 * @see Quaternion
 */
public class LargeVector extends ArrayVector<LargeVector> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new vector.
     */
    public LargeVector() {
        super();
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     */
    public LargeVector(double x) {
        super(x);
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public LargeVector(double x, double y) {
        super(x, y);
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public LargeVector(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Creates a new vector.
     *
     * @param w The W component of this vector
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public LargeVector(double w, double x, double y, double z) {
        super(w, x, y, z);
    }

    /**
     * Creates a new vector.
     *
     * @param v1 The first component of this vector
     * @param v2 The second component of this vector
     * @param v3 The third component of this vector
     * @param v4 The fourth component of this vector
     * @param v5 The fifth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5) {
        super(v1, v2, v3, v4, v5);
    }

    /**
     * Creates a new vector.
     *
     * @param v1 The first component of this vector
     * @param v2 The second component of this vector
     * @param v3 The third component of this vector
     * @param v4 The fourth component of this vector
     * @param v5 The fifth component of this vector
     * @param v6 The sixth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6) {
        super(v1, v2, v3, v4, v5, v6);
    }

    /**
     * Creates a new vector.
     *
     * @param v1 The first component of this vector
     * @param v2 The second component of this vector
     * @param v3 The third component of this vector
     * @param v4 The fourth component of this vector
     * @param v5 The fifth component of this vector
     * @param v6 The sixth component of this vector
     * @param v7 The seventh component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7) {
        super(v1, v2, v3, v4, v5, v6, v7);
    }

    /**
     * Creates a new vector.
     *
     * @param v1 The first component of this vector
     * @param v2 The second component of this vector
     * @param v3 The third component of this vector
     * @param v4 The fourth component of this vector
     * @param v5 The fifth component of this vector
     * @param v6 The sixth component of this vector
     * @param v7 The seventh component of this vector
     * @param v8 The eighth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8) {
        super(v1, v2, v3, v4, v5, v6, v7, v8);
    }

    /**
     * Creates a new vector.
     *
     * @param v1 The first component of this vector
     * @param v2 The second component of this vector
     * @param v3 The third component of this vector
     * @param v4 The fourth component of this vector
     * @param v5 The fifth component of this vector
     * @param v6 The sixth component of this vector
     * @param v7 The seventh component of this vector
     * @param v8 The eighth component of this vector
     * @param v9 The ninth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9) {
        super(v1, v2, v3, v4, v5, v6, v7, v8, v9);
    }

    /**
     * Creates a new vector.
     *
     * @param v1  The first component of this vector
     * @param v2  The second component of this vector
     * @param v3  The third component of this vector
     * @param v4  The fourth component of this vector
     * @param v5  The fifth component of this vector
     * @param v6  The sixth component of this vector
     * @param v7  The seventh component of this vector
     * @param v8  The eighth component of this vector
     * @param v9  The ninth component of this vector
     * @param v10 The tenth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10) {
        super(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
    }

    /**
     * Creates a new vector.
     *
     * @param v1  The first component of this vector
     * @param v2  The second component of this vector
     * @param v3  The third component of this vector
     * @param v4  The fourth component of this vector
     * @param v5  The fifth component of this vector
     * @param v6  The sixth component of this vector
     * @param v7  The seventh component of this vector
     * @param v8  The eighth component of this vector
     * @param v9  The ninth component of this vector
     * @param v10 The tenth component of this vector
     * @param v11 The eleventh component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11) {
        super(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11);
    }

    /**
     * Creates a new vector.
     *
     * @param v1  The first component of this vector
     * @param v2  The second component of this vector
     * @param v3  The third component of this vector
     * @param v4  The fourth component of this vector
     * @param v5  The fifth component of this vector
     * @param v6  The sixth component of this vector
     * @param v7  The seventh component of this vector
     * @param v8  The eighth component of this vector
     * @param v9  The ninth component of this vector
     * @param v10 The tenth component of this vector
     * @param v11 The eleventh component of this vector
     * @param v12 The twelfth component of this vector
     */
    public LargeVector(double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9, double v10, double v11, double v12) {
        super(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12);
    }

    /**
     * Creates a new vector.
     *
     * @param values The components of this vector
     * @throws NullPointerException When the provided array is {@code null}
     */
    public LargeVector(double... values) {
        super(Arrays.copyOf(values, values.length));
    }

    /**
     * Creates a new vector.
     *
     * @param s The stream of which to extract component values from
     * @throws NullPointerException When the provided stream {@code s} is {@code null}
     */
    public LargeVector(DoubleStream s) {
        super(s.toArray());
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws NullPointerException When the provided vector {@code v} is {@code null}
     */
    public LargeVector(Vector<?> v) {
        super(v.toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LargeVector map(DoubleUnaryOperator mapper) {
        return new LargeVector(stream().map(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param v      The vector of which to merge this vector with
     * @param merger The merger function of which to handle the merging of the two vectors
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LargeVector merge(LargeVector v, DoubleBinaryOperator merger) {
        AtomicInteger i = new AtomicInteger(0);
        return new LargeVector(stream()
                .map(x -> merger.applyAsDouble(x, v.valueAt(i.getAndIncrement()))));
    }
}
