package pegasus.grid;

import pegasus.container.LongArrayElementReference;
import pegasus.container.LongContainer;
import pegasus.exception.BinaryIndexOutOfBoundsException;
import pegasus.exception.IncompatibleDimensionsException;
import pegasus.function.BinaryIndexedLongConsumer;
import pegasus.function.IntToLongBiFunction;

import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.LongStream;

/**
 * An array-based {@code long} grid.
 *
 * @see LongGrid
 */
public class LongArrayGrid implements LongGrid {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new array grid.
     *
     * @param rows    The number of rows to initialize
     * @param columns The number of columns to initialize
     * @throws IllegalArgumentException When the dimensions are invalid
     */
    public LongArrayGrid(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        this.values = new long[rows * columns];
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Creates a new array grid.
     *
     * @param rows      The number of rows to initialize
     * @param columns   The number of columns to initialize
     * @param generator The generator function to initialize this grid with
     * @throws IllegalArgumentException When the dimensions are invalid
     * @throws NullPointerException     When the provided generator function is {@code null}
     */
    public LongArrayGrid(int rows, int columns, IntToLongBiFunction generator) {
        this(rows, columns);
        setAll(generator);
    }

    /**
     * Creates a new array grid.
     *
     * @param g The grid of which to copy elements from
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public LongArrayGrid(LongGrid g) {
        this.rows = g.rows();
        this.columns = g.columns();
        this.values = new long[rows * columns];

        fillRange(0, 0, rows, columns, g);
    }

    /**
     * Creates a new array grid.
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
    public LongArrayGrid(LongGrid g, int r1, int c1, int r2, int c2) {
        this.rows = r2 - r1;
        this.columns = c2 - c1;

        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        this.values = new long[rows * columns];

        int i = 0;
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[i++] = g.get(r, c);
            }
        }
    }

    /**
     * The internal array of values.
     */
    protected final long[] values;

    /**
     * The number of rows.
     */
    protected final int rows;

    /**
     * The number of columns.
     */
    protected final int columns;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int rows() {
        return rows;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int columns() {
        return columns;
    }

    /**
     * {@inheritDoc}
     *
     * @param r The row index
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public long[] row(int r) throws IndexOutOfBoundsException {
        return Arrays.copyOfRange(values, r * columns, (r + 1) * columns);
    }

    /**
     * {@inheritDoc}
     *
     * @param c The column index
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public long[] column(int c) throws IndexOutOfBoundsException {
        long[] result = new long[rows];

        for (int i = 0; i < rows; i++) {
            result[i] = values[i * columns + c];
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(long value) {
        return stream().anyMatch(v -> v == value);
    }

    /**
     * {@inheritDoc}
     *
     * @param g The grid of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(LongGrid g) {
        return g.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     *
     * @param r The row index
     * @param c The column index
     * @return {@inheritDoc}
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public long get(int r, int c) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return values[r * columns + c];
    }

    /**
     * {@inheritDoc}
     *
     * @param r The row index
     * @param c The column index
     * @return {@inheritDoc}
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public LongContainer getReference(int r, int c) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return new LongArrayElementReference(values, r * columns + c, false);
    }

    /**
     * {@inheritDoc}
     *
     * @param r     The row index
     * @param c     The column index
     * @param value The value to set to
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(int r, int c, long value) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        values[r * columns + c] = value;
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to fill this grid with
     */
    @Override
    public void fill(long value) {
        Arrays.fill(values, value);
    }

    /**
     * {@inheritDoc}
     *
     * @param r1    The starting row index (inclusive)
     * @param c1    The starting column index (inclusive)
     * @param r2    The ending row index (exclusive)
     * @param c2    The ending column index (exclusive)
     * @param value The value of which to fill the sub-portion with
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void fillRange(int r1, int c1, int r2, int c2, long value) throws BinaryIndexOutOfBoundsException {
        if (r1 < 0 || r1 > rows || c1 < 0 || c1 > columns) {
            throw new BinaryIndexOutOfBoundsException(r1, c1);
        }

        if (r2 < 0 || r2 > rows || c2 < 0 || c2 > columns) {
            throw new BinaryIndexOutOfBoundsException(r2, c2);
        }

        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[r * columns + c] = value;
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param r1 The starting row index (inclusive)
     * @param c1 The starting column index (inclusive)
     * @param r2 The ending row index (exclusive)
     * @param c2 The ending column index (exclusive)
     * @param g  The source grid of which to retrieve elements from
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public void fillRange(int r1, int c1, int r2, int c2, LongGrid g)
            throws BinaryIndexOutOfBoundsException {
        if (r1 < 0 || r1 > rows || c1 < 0 || c1 > columns) {
            throw new BinaryIndexOutOfBoundsException(r1, c1);
        }

        if (r2 < 0 || r2 > rows || c2 < 0 || c2 > columns) {
            throw new BinaryIndexOutOfBoundsException(r2, c2);
        }

        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[r * columns + c] = g.get(r - r1, c - c1);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param generator The generator function of which to use to populate this grid
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void setAll(IntToLongBiFunction generator) {
        int i = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[i++] = generator.applyAsLong(r, c);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply to each element of this grid
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void replace(LongUnaryOperator operator) {
        Arrays.setAll(values, i -> operator.applyAsLong(values[i]));
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    @Override
    public void replaceAll(long oldValue, long newValue) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;

                if (values[i] != oldValue) continue;
                values[i] = newValue;
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongGrid map(LongUnaryOperator mapper) {
        LongArrayGrid result = new LongArrayGrid(rows, columns);
        Arrays.setAll(result.values, i -> mapper.applyAsLong(values[i]));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> Grid<U> mapToObj(LongFunction<? extends U> mapper) {
        ArrayGrid<U> result = new ArrayGrid<>(rows, columns);
        Arrays.setAll(result.values, i -> mapper.apply(values[i]));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleGrid mapToDouble(LongToDoubleFunction mapper) {
        DoubleArrayGrid result = new DoubleArrayGrid(rows, columns);
        Arrays.setAll(result.values, i -> mapper.applyAsDouble(values[i]));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntGrid mapToInt(LongToIntFunction mapper) {
        IntArrayGrid result = new IntArrayGrid(rows, columns);
        Arrays.setAll(result.values, i -> mapper.applyAsInt(values[i]));
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param g      The grid of which to merge this grid with
     * @param merger The merger function of which to handle the merging of the two grids
     * @return {@inheritDoc}
     * @throws IncompatibleDimensionsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public LongGrid merge(LongGrid g, LongBinaryOperator merger)
            throws IncompatibleDimensionsException {
        if (rows != g.rows() || columns != g.columns()) {
            throw new IncompatibleDimensionsException();
        }

        LongArrayGrid result = new LongArrayGrid(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;
                result.values[i] = merger.applyAsLong(values[i], g.get(r, c));
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
    public LongGrid resize(int rows, int columns) {
        LongArrayGrid result = new LongArrayGrid(rows, columns);

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
    public LongGrid transpose() {
        LongArrayGrid result = new LongArrayGrid(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c * columns + r] = values[r * columns + c];
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public LongStream stream() {
        return Arrays.stream(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<Long> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void forEachIndexed(BinaryIndexedLongConsumer action) {
        int i = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                action.accept(r, c, values[i++]);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressWarnings("RedundantCast")
    public int hashCode() {
        return Objects.hash((Object) values, rows, columns);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LongGrid g)) return false;
        if (rows != g.rows() || columns != g.columns()) return false;

        int i = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (values[i++] != g.get(r, c)) return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        if (values.length == 0) return "{}";

        StringBuilder result = new StringBuilder("{");

        for (int r = 0; r < rows; r++) {
            result.append("\n  ").append(Arrays.toString(row(r))).append(",");
        }

        result.replace(result.lastIndexOf(","), result.length(), "\n");

        return result.append("}").toString();
    }
}
