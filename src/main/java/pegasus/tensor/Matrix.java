package pegasus.tensor;

import pegasus.exception.*;
import pegasus.function.IntToDoubleBiFunction;
import pegasus.grid.DoubleArrayGrid;
import pegasus.grid.DoubleGrid;

import java.io.Serial;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/**
 * A mathematical matrix.
 *
 * @see Tensor
 * @see DoubleGrid
 * @see DoubleArrayGrid
 */
public class Matrix extends DoubleArrayGrid implements DoubleGrid, Tensor {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new matrix containing the provided values.
     *
     * @param values The rectangular array of values
     * @return The constructed matrix
     * @throws IllegalArgumentException When the array is not rectangular
     * @throws NullPointerException     When the array contains a {@code null} row
     */
    public static Matrix of(double[][] values) {
        if (values.length > 0 && Arrays.stream(values).mapToInt(Array::getLength).distinct().count() != 1) {
            throw new IllegalArgumentException("The provided array is not rectangular.");
        }

        int rows = values.length;
        int columns = rows > 0 ? values[0].length : 0;

        Matrix result = new Matrix(rows, columns);

        int i = 0;
        for (double[] row : values) {
            for (int c = 0; c < columns; c++) {
                result.values[i++] = row[c];
            }
        }

        return result;
    }

    /**
     * Creates a new {@code n}-dimensional identity matrix.
     *
     * @param n The number of rows and columns to initialize
     * @return The constructed identity matrix
     * @throws IllegalArgumentException When {@code n} is negative
     */
    public static Matrix newIdentity(int n) {
        return new Matrix(n, n, (r, c) -> r == c ? 1 : 0);
    }

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows to initialize
     * @param columns The number of columns to initialize
     * @throws IllegalArgumentException When the dimensions are invalid
     */
    public Matrix(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Creates a new matrix.
     *
     * @param rows      The number of rows to initialize
     * @param columns   The number of columns to initialize
     * @param generator The generator function to initialize this grid with
     * @throws IllegalArgumentException When the dimensions are invalid
     * @throws NullPointerException     When the provided generator function is {@code null}
     */
    public Matrix(int rows, int columns, IntToDoubleBiFunction generator) {
        super(rows, columns, generator);
    }

    /**
     * Creates a new matrix.
     *
     * @param g The grid of which to copy elements from
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public Matrix(DoubleGrid g) {
        super(g);
    }

    /**
     * Creates a new matrix.
     *
     * @param g  The grid of which to copy elements from
     * @param r1 The starting row index at which to start copying values from (inclusive)
     * @param c1 The starting column index at which to start copying values from (inclusive)
     * @param r2 The ending row index at which to stop copying values from (exclusive)
     * @param c2 The ending column index at which to stop copying values from (exclusive)
     * @throws IllegalArgumentException        When the range produces negative area
     * @throws NullPointerException            When the provided grid {@code g} is {@code null}
     * @throws BinaryIndexOutOfBoundsException When the range is larger than the
     *                                         provided grid {@code g}'s dimensions
     */
    public Matrix(DoubleGrid g, int r1, int c1, int r2, int c2) {
        super(g, r1, c1, r2, c2);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return stream().anyMatch(Double::isNaN);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return stream().allMatch(Double::isFinite);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return stream().anyMatch(Double::isInfinite);
    }

    /**
     * Returns whether this matrix is a square matrix.
     *
     * @return {@code true} if the row count and column count are equal
     */
    public boolean isSquare() {
        return rows == columns;
    }

    /**
     * Adds a scalar to this matrix, then returns the resulting matrix.
     *
     * @param s The scalar of which to add to this matrix
     * @return The resulting matrix
     */
    public Matrix add(double s) {
        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> values[i] + s);
        return result;
    }

    /**
     * Subtracts a scalar from this matrix, then returns the resulting matrix.
     *
     * @param s The scalar of which to subtract from this matrix
     * @return The resulting matrix
     */
    public Matrix subtract(double s) {
        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> values[i] - s);
        return result;
    }

    /**
     * Multiplies this matrix by a scalar, then returns the resulting matrix.
     *
     * @param s The scalar of which to multiply this matrix by
     * @return The resulting matrix
     */
    public Matrix multiply(double s) {
        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> values[i] * s);
        return result;
    }

    /**
     * Divides this matrix by a scalar, then returns the resulting matrix.
     *
     * @param s The scalar of which to divide this matrix by
     * @return The resulting matrix
     * @throws ArithmeticException When the provided denominator {@code s} is zero
     */
    public Matrix divide(double s) throws ArithmeticException {
        if (s == 0) throw new DivisionByZeroException();
        double inv = 1 / s;

        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> values[i] * inv);
        return result;
    }

    /**
     * Adds a matrix to this matrix, then returns the resulting matrix.
     *
     * @param m The matrix of which to add to this matrix
     * @return The resulting matrix
     * @throws IncompatibleDimensionsException When the dimensions are different
     * @throws NullPointerException            When the provided matrix {@code m} is {@code null}
     */
    public Matrix add(Matrix m) throws IncompatibleDimensionsException {
        if (rows != m.rows() || columns != m.columns()) {
            throw new IncompatibleDimensionsException();
        }

        Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;
                result.values[i] = values[i] + m.get(r, c);
            }
        }

        return result;
    }

    /**
     * Subtracts a matrix from this matrix, then returns the resulting matrix.
     *
     * @param m The matrix of which to subtract from this matrix
     * @return The resulting matrix
     * @throws IncompatibleDimensionsException When the dimensions are different
     * @throws NullPointerException            When the provided matrix {@code m} is {@code null}
     */
    public Matrix subtract(Matrix m) throws IncompatibleDimensionsException {
        if (rows != m.rows() || columns != m.columns()) {
            throw new IncompatibleDimensionsException();
        }

        Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;
                result.values[i] = values[i] - m.get(r, c);
            }
        }

        return result;
    }

    /**
     * Multiplies this matrix by another matrix, then returns the resulting matrix.
     *
     * @param m The matrix of which to multiply with this matrix
     * @return The resulting matrix
     * @throws IncompatibleDimensionsException When the dimensions are incompatible for multiplication
     * @throws NullPointerException            When the provided matrix {@code m} is {@code null}
     */
    public Matrix multiply(Matrix m) throws IncompatibleDimensionsException {
        if (columns != m.rows()) {
            throw new IncompatibleDimensionsException();
        }

        Matrix result = new Matrix(rows, m.columns());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.columns(); j++) {
                double sum = 0;
                for (int k = 0; k < columns; k++) {
                    sum += this.values[i * columns + k] * m.get(k, j);
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    /**
     * Performs matrix-vector multiplication, then returns the resulting vector.
     *
     * @param v   The vector of which to multiply by this matrix
     * @param <W> The actual input vector's type
     * @param <V> The base vector's type
     * @return The resulting vector
     * @throws IncompatibleDimensionsException When the number of columns is different from the provided
     *                                         vector {@code v}'s size
     * @throws NullPointerException            When the provided vector {@code v} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public <W extends V, V extends Vector<V>> V multiply(W v)
            throws IncompatibleDimensionsException {
        if (v.size() != columns) {
            throw new IncompatibleDimensionsException();
        }

        double[] vector = v.toArray();
        double[] result = new double[rows];

        for (int r = 0; r < rows; r++) {
            double sum = 0;
            for (int c = 0; c < columns; c++) {
                sum += values[r * columns + c] * vector[c];
            }
            result[r] = sum;
        }

        return (V) Vector.newVector(result);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Matrix map(DoubleUnaryOperator mapper) {
        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> mapper.applyAsDouble(values[i]));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param g      The grid of which to merge this grid with
     * @param merger The merger function of which to handle the merging of the two grids
     * @return {@inheritDoc}
     * @throws IncompatibleDimensionsException {@inheritDoc}
     */
    @Override
    public Matrix merge(DoubleGrid g, DoubleBinaryOperator merger) throws IncompatibleDimensionsException {
        if (rows != g.rows() || columns != g.columns()) {
            throw new IncompatibleDimensionsException();
        }

        Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;
                result.values[i] = merger.applyAsDouble(values[i], g.get(r, c));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param rows    The number of rows to resize to
     * @param columns The number of columns to resize to
     * @return {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public Matrix resize(int rows, int columns) {
        Matrix result = new Matrix(rows, columns);

        int minRows = Math.min(rows, this.rows);
        int minColumns = Math.min(columns, this.columns);

        for (int r = 0; r < minRows; r++) {
            System.arraycopy(values, r * this.columns, result.values, r * columns, minColumns);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Matrix transpose() {
        Matrix result = new Matrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c * columns + r] = values[r * columns + c];
            }
        }

        return result;
    }

    /**
     * Returns the negation of this matrix.
     *
     * @return The negation of this matrix
     */
    public Matrix negate() {
        Matrix result = new Matrix(rows, columns);
        Arrays.setAll(result.values, i -> -values[i]);
        return result;
    }
}
