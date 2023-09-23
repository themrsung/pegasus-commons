package pegasus.grid;

import pegasus.exception.BinaryIndexOutOfBoundsException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * A reference-based grid which utilizes atomic references to ensure thread safety.
 *
 * @param <T> The type of object of which to reference
 * @see BaseGrid
 * @see Grid
 */
public interface AtomicGrid<T> extends Grid<T> {
    /**
     * Creates a new atomic grid containing the provided values.
     *
     * @param values The rectangular array of values
     * @param <T>    The type of object to contain
     * @return The constructed atomic grid
     * @throws IllegalArgumentException When the array is not rectangular
     * @throws NullPointerException     When the array contains a {@code null} row
     */
    static <T> AtomicGrid<T> of(T[][] values) {
        if (Arrays.stream(values).mapToInt(Array::getLength).distinct().count() > 1) {
            throw new IllegalArgumentException("The provided array is not rectangular.");
        }

        int rows = values.length;
        int columns = rows > 0 ? values[0].length : 0;

        AtomicArrayGrid<T> result = new AtomicArrayGrid<>(rows, columns);

        int i = 0;
        for (T[] row : values) {
            for (int c = 0; c < columns; c++) {
                result.values[i++].set(row[c]);
            }
        }

        return result;
    }

    /**
     * Caches the value at the specified index of this grid in memory, updates the value, then
     * returns the cached value.
     *
     * @param r        The index of the row to update
     * @param c        The index of the column to update
     * @param operator The update function of which to apply
     * @return The {@code i}th value of this grid before the update
     * @throws BinaryIndexOutOfBoundsException When the provided index is out of bounds
     * @throws NullPointerException            When the provided update function is {@code null}
     */
    T getAndUpdate(int r, int c, UnaryOperator<T> operator)
            throws BinaryIndexOutOfBoundsException;

    /**
     * Updates the value at the specified index of this grid, then returns the post-update value.
     *
     * @param r        The index of the row to update
     * @param c        The index of the column to update
     * @param operator The update function of which to apply
     * @return The {@code i}th value of this grid after the update
     * @throws BinaryIndexOutOfBoundsException When the provided index is out of bounds
     * @throws NullPointerException            When the provided update function is {@code null}
     */
    T updateAndGet(int r, int c, UnaryOperator<T> operator)
            throws BinaryIndexOutOfBoundsException;

    /**
     * Caches the value at the specified index of this grid in memory, set the value, then returns the
     * cached value.
     *
     * @param r     The index of the row to set
     * @param c     The index of the column to set
     * @param value The value of which to set to
     * @return The {@code i}th value of this grid before assignment
     * @throws BinaryIndexOutOfBoundsException When the provided index is out of bounds
     */
    T getAndSet(int r, int c, T value) throws BinaryIndexOutOfBoundsException;
}
