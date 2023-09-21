package pegasus.tensor;

import java.io.Serial;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/**
 * An immutable ten-dimensional vector.
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
 * @see Vector12
 * @see Quaternion
 */
public class Vector11 extends ArrayVector<Vector11> {
    @Serial
    private static final long serialVersionUID = 0;

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
     */
    public Vector11(
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
            double s
    ) {
        super(i, j, k, l, m, n, o, p, q, r, s);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IllegalArgumentException When the provided vector {@code v}'s size is not {@code 11}
     * @throws NullPointerException     When the provided vector {@code v} is {@code null}
     */
    public Vector11(Vector<?> v) {
        super(v.toArray());

        if (v.size() != 11) {
            throw new IllegalArgumentException("The provided vector's size is not 11.");
        }
    }

    /**
     * Direct assignment constructor.
     *
     * @param values The values to directly assign
     */
    private Vector11(double[] values) {
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
    public Vector11 map(DoubleUnaryOperator mapper) {
        return new Vector11(stream().map(mapper).toArray());
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
    public Vector11 merge(Vector11 v, DoubleBinaryOperator merger) {
        double[] result = new double[11];

        for (int i = 0; i < 11; i++) {
            result[i] = merger.applyAsDouble(valueAt(i), v.valueAt(i));
        }

        return new Vector11(result);
    }
}
