package pegasus.tensor;

import java.io.Serial;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/**
 * An immutable twelve-dimensional vector.
 *
 * @see Tensor
 * @see Vector
 * @see Vector0
 * @see Vector1
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
 * @see LargeVector
 * @see Quaternion
 */
public class Vector12 extends ArrayVector<Vector12> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The zero vector.
     */
    public static final Vector12 ZERO =
            new Vector12(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    /**
     * Creates a new vector.
     *
     * @param i The I component of this vector
     * @param j The J component of this vector
     * @param k The K component of this vector
     * @param l The L component of this vector
     * @param m The M component of this vector
     * @param n The N component of this vector
     * @param o The O component of this vector
     * @param p The P component of this vector
     * @param q The Q component of this vector
     * @param r The R component of this vector
     * @param s The S component of this vector
     * @param t The T component of this vector
     */
    public Vector12(
            double i,
            double j,
            double k,
            double l,
            double m,
            double n,
            double o,
            double p,
            double q,
            double r,
            double s,
            double t
    ) {
        super(i, j, k, l, m, n, o, p, q, r, s, t);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 12}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector12(Vector<?> v) {
        super(v.toArray());

        if (v.size() != 12) {
            throw new IllegalArgumentException("The provided vector's size is not 12.");
        }
    }

    /**
     * Direct assignment constructor.
     *
     * @param values The values to directly assign
     */
    private Vector12(double[] values) {
        super(values);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each component of this vector
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Vector12 map(DoubleUnaryOperator mapper) {
        return new Vector12(stream().map(mapper).toArray());
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
    public Vector12 merge(Vector12 v, DoubleBinaryOperator merger) {
        double[] result = new double[12];

        for (int i = 0; i < 12; i++) {
            result[i] = merger.applyAsDouble(valueAt(i), v.valueAt(i));
        }

        return new Vector12(result);
    }
}
