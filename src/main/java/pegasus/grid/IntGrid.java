package pegasus.grid;

import pegasus.exception.BinaryIndexOutOfBoundsException;
import pegasus.exception.IncompatibleDimensionsException;
import pegasus.util.BinaryIndexedIntIterable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A value-based primitive grid of {@code int} values.
 *
 * @see BaseGrid
 * @see IntArrayGrid
 */
public interface IntGrid extends BaseGrid<Integer>, BinaryIndexedIntIterable {
    /**
     * Creates a new grid containing the provided values.
     *
     * @param values The rectangular array of values
     * @return The constructed grid
     * @throws IllegalArgumentException When the array is not rectangular
     * @throws NullPointerException     When the array contains a {@code null} row
     */
    static IntGrid of(int[][] values) {
        if (Arrays.stream(values).mapToInt(Array::getLength).distinct().count() > 1) {
            throw new IllegalArgumentException("The provided array is not rectangular.");
        }

        int rows = values.length;
        int columns = rows > 0 ? values[0].length : 0;

        IntArrayGrid result = new IntArrayGrid(rows, columns);

        int i = 0;
        for (int[] row : values) {
            for (int c = 0; c < columns; c++) {
                result.values[i++] = row[c];
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int size();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int rows();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int columns();

    /**
     * Returns the {@code r}th row of this grid as an array.
     *
     * @param r The row index
     * @return The {@code r}th row of this grid
     * @throws IndexOutOfBoundsException When the provided index {@code r} is out of bounds
     */
    int[] row(int r) throws IndexOutOfBoundsException;

    /**
     * Returns the {@code c}th column of this grid as an array.
     *
     * @param c The column index
     * @return The {@code c}th column of this grid
     * @throws IndexOutOfBoundsException When the provided index {@code c} is out of bounds
     */
    int[] column(int c) throws IndexOutOfBoundsException;

    /**
     * Returns whether this grid contains the provided value.
     *
     * @param value The value of which to check for containment
     * @return {@code true} if this grid contains the provided value
     */
    boolean contains(int value);

    /**
     * Returns whether this grid contains every element of the provided grid {@code g}.
     *
     * @param g The grid of which to check for containment
     * @return {@code true} if this grid contains every element of the provided grid {@code g}
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    boolean containsAll(IntGrid g);

    /**
     * Returns the value at the specified index.
     *
     * @param r The row index
     * @param c The column index
     * @return The value at the specified index
     * @throws BinaryIndexOutOfBoundsException When the provided index is out of bounds
     */
    int get(int r, int c) throws BinaryIndexOutOfBoundsException;

    /**
     * Sets the value at the specified index.
     *
     * @param r     The row index
     * @param c     The column index
     * @param value The value to set to
     * @throws BinaryIndexOutOfBoundsException When the provided index is out of bounds
     */
    void set(int r, int c, int value) throws BinaryIndexOutOfBoundsException;

    /**
     * Fills this grid with the provided value.
     *
     * @param value The value of which to fill this grid with
     */
    void fill(int value);

    /**
     * Fills a sub-portion of this grid with the values of the provided value.
     *
     * @param r1    The starting row index (inclusive)
     * @param c1    The starting column index (inclusive)
     * @param r2    The ending row index (exclusive)
     * @param c2    The ending column index (exclusive)
     * @param value The value of which to fill the sub-portion with
     * @throws BinaryIndexOutOfBoundsException When the range is invalid, or is out of bounds
     */
    void fillRange(int r1, int c1, int r2, int c2, int value) throws BinaryIndexOutOfBoundsException;

    /**
     * Fills a sub-portion of this grid with the values of the provided grid {@code g}.
     *
     * @param r1 The starting row index (inclusive)
     * @param c1 The starting column index (inclusive)
     * @param r2 The ending row index (exclusive)
     * @param c2 The ending column index (exclusive)
     * @param g  The source grid of which to retrieve elements from
     * @throws BinaryIndexOutOfBoundsException When the range is invalid, or is out of bounds
     * @throws NullPointerException            When the provided grid {@code g} is {@code null}
     */
    void fillRange(int r1, int c1, int r2, int c2, IntGrid g)
            throws BinaryIndexOutOfBoundsException;

    /**
     * Sets all elements of this grid using the provided generator function.
     *
     * @param generator The generator function of which to use to populate this grid
     * @throws NullPointerException When the provided generator function is {@code null}
     */
    void setAll(IntBinaryOperator generator);

    /**
     * Applies the provided update function to each element of this grid, then assigns each element
     * to the return value of the provided update function.
     *
     * @param operator The update function of which to apply to each element of this grid
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void replace(IntUnaryOperator operator);

    /**
     * Replaces all instances of the old value to the new value.
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace to
     */
    void replaceAll(int oldValue, int newValue);

    /**
     * Applies the provided mapper function to each element of this grid, then returns a new grid whose
     * elements are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return The resulting grid
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    IntGrid map(IntUnaryOperator mapper);

    /**
     * Applies the provided mapper function to each element of this grid, then returns a new grid whose
     * elements are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @param <U>    The type of element to map this grid into
     * @return The resulting grid
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    <U> Grid<U> mapToObj(IntFunction<? extends U> mapper);

    /**
     * Applies the provided mapper function to each element of this grid, then returns a new grid whose
     * elements are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return The resulting grid
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    DoubleGrid mapToDouble(IntToDoubleFunction mapper);

    /**
     * Applies the provided mapper function to each element of this grid, then returns a new grid whose
     * elements are populated from that of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return The resulting grid
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    LongGrid mapToLong(IntToLongFunction mapper);

    /**
     * Applies the provided merger function to each corresponding pair of elements between this grid and
     * the provided grid {@code g}, then returns a new grid whose elements are populated from that of the
     * return values of the provided merger function.
     *
     * @param g      The grid of which to merge this grid with
     * @param merger The merger function of which to handle the merging of the two grids
     * @return The resulting grid
     * @throws IncompatibleDimensionsException When the grids' dimensions are different
     * @throws NullPointerException            When either the provided grid {@code g} or the merger function is {@code null}
     */
    IntGrid merge(IntGrid g, IntBinaryOperator merger)
            throws IncompatibleDimensionsException;

    /**
     * Returns a new grid with the provided dimensions whose elements are populated from that of this
     * grid's elements, trimmed or padded with {@code 0} values as necessary.
     *
     * @param rows    The number of rows to resize to
     * @param columns The number of columns to resize to
     * @return The resized grid
     * @throws IllegalArgumentException When the dimensions produce negative area
     */
    IntGrid resize(int rows, int columns);

    /**
     * Returns the transpose of this grid.
     *
     * @return The transpose of this grid
     */
    IntGrid transpose();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    IntStream stream();

    /**
     * Returns an array containing the elements of this grid.
     *
     * @return The array representation of this grid
     */
    int[] toArray();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    Iterator<Integer> iterator();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    int hashCode();

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    boolean equals(Object obj);

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    String toString();
}
