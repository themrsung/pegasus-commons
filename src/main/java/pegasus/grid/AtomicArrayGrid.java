package pegasus.grid;

import pegasus.exception.BinaryIndexOutOfBoundsException;
import pegasus.exception.IncompatibleDimensionsException;
import pegasus.function.BinaryIndexedConsumer;
import pegasus.function.IntBiFunction;

import java.io.Serial;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * An array-based atomic grid.
 *
 * @param <T> The type of object this grid is to hold
 * @see AtomicGrid
 */
public class AtomicArrayGrid<T> implements AtomicGrid<T> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new atomic grid.
     *
     * @param rows    The number of rows to initialize
     * @param columns The number of columns to initialize
     * @throws IllegalArgumentException When the dimensions are invalid
     */
    @SuppressWarnings("unchecked")
    public AtomicArrayGrid(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        this.values = (AtomicReference<T>[]) Array.newInstance(AtomicReference.class, rows * columns);
        this.rows = rows;
        this.columns = columns;

        for (int i = 0; i < values.length; i++) {
            values[i] = new AtomicReference<>();
        }
    }

    /**
     * Creates a new atomic grid.
     *
     * @param rows      The number of rows to initialize
     * @param columns   The number of columns to initialize
     * @param generator The generator function to initialize this grid with
     * @throws IllegalArgumentException When the dimensions are invalid
     * @throws NullPointerException     When the provided generator function is {@code null}
     */
    public AtomicArrayGrid(int rows, int columns, IntBiFunction<? extends T> generator) {
        this(rows, columns);
        setAll(generator);
    }

    /**
     * Creates a new atomic grid.
     *
     * @param g The grid of which to copy elements from
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public AtomicArrayGrid(Grid<? extends T> g) {
        this.rows = g.rows();
        this.columns = g.columns();
        this.values = (AtomicReference<T>[]) Array.newInstance(AtomicReference.class, rows * columns);

        for (int i = 0; i < values.length; i++) {
            values[i] = new AtomicReference<>();
        }

        fillRange(0, 0, rows, columns, g);
    }

    /**
     * Creates a new atomic grid.
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
    @SuppressWarnings("unchecked")
    public AtomicArrayGrid(Grid<? extends T> g, int r1, int c1, int r2, int c2) {
        this.rows = r2 - r1;
        this.columns = c2 - c1;

        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        this.values = (AtomicReference<T>[]) Array.newInstance(AtomicReference.class, rows * columns);

        for (int i = 0; i < values.length; i++) {
            values[i] = new AtomicReference<>();
        }

        int i = 0;
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[i++].set(g.get(r, c));
            }
        }
    }

    /**
     * The internal array of values.
     */

    protected final AtomicReference<T>[] values;

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
    @SuppressWarnings("unchecked")
    public T[] row(int r) throws IndexOutOfBoundsException {
        return (T[]) Arrays.stream(Arrays.copyOfRange(values, r * columns, (r + 1) * columns))
                .map(AtomicReference::get).toArray();
    }

    /**
     * {@inheritDoc}
     *
     * @param c The column index
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] column(int c) throws IndexOutOfBoundsException {
        T[] result = (T[]) new Object[rows];

        for (int i = 0; i < rows; i++) {
            result[i] = values[i * columns + c].get();
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
    public boolean contains(T value) {
        return stream().anyMatch(v -> Objects.equals(v, value));
    }

    /**
     * {@inheritDoc}
     *
     * @param g The grid of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(Grid<? extends T> g) {
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
    public T get(int r, int c) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return values[r * columns + c].get();
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
    public void set(int r, int c, T value) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        values[r * columns + c].set(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param r        The index of the row to update
     * @param c        The index of the column to update
     * @param operator The update function of which to apply
     * @return {@inheritDoc}
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public T getAndUpdate(int r, int c, UnaryOperator<T> operator)
            throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return values[r * columns + c].getAndUpdate(operator);
    }

    /**
     * {@inheritDoc}
     *
     * @param r        The index of the row to update
     * @param c        The index of the column to update
     * @param operator The update function of which to apply
     * @return {@inheritDoc}
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public T updateAndGet(int r, int c, UnaryOperator<T> operator)
            throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return values[r * columns + c].updateAndGet(operator);
    }

    /**
     * {@inheritDoc}
     *
     * @param r     The index of the row to set
     * @param c     The index of the column to set
     * @param value The value of which to set to
     * @return {@inheritDoc}
     * @throws BinaryIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T getAndSet(int r, int c, T value) throws BinaryIndexOutOfBoundsException {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            throw new BinaryIndexOutOfBoundsException(r, c);
        }

        return values[r * columns + c].getAndSet(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to fill this grid with
     */
    @Override
    public void fill(T value) {
        for (AtomicReference<T> ref : values) {
            ref.set(value);
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
    public void fillRange(int r1, int c1, int r2, int c2, Grid<? extends T> g)
            throws BinaryIndexOutOfBoundsException {
        if (r1 < 0 || r1 > rows || c1 < 0 || c1 > columns) {
            throw new BinaryIndexOutOfBoundsException(r1, c1);
        }

        if (r2 < 0 || r2 > rows || c2 < 0 || c2 > columns) {
            throw new BinaryIndexOutOfBoundsException(r2, c2);
        }

        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[r * columns + c].set(g.get(r - r1, c - c1));
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
    public void setAll(IntBiFunction<? extends T> generator) {
        int i = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[i++].set(generator.apply(r, c));
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
    public void replace(UnaryOperator<T> operator) {
        for (AtomicReference<T> ref : values) {
            ref.set(operator.apply(ref.get()));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param oldValue The old value of which to replace
     * @param newValue The new value of which to replace
     */
    @Override
    public void replaceAll(T oldValue, T newValue) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                var ref = values[r * columns + c];

                if (!(Objects.equals(ref.get(), oldValue))) continue;
                ref.set(newValue);
            }
        }
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
    public <U> Grid<U> map(Function<? super T, ? extends U> mapper) {
        return new AtomicArrayGrid<>(rows, columns, (r, c) -> mapper.apply(values[r * columns + c].get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleGrid mapToDouble(ToDoubleFunction<? super T> mapper) {
        return new DoubleArrayGrid(rows, columns, (r, c) -> mapper.applyAsDouble(values[r * columns + c].get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongGrid mapToLong(ToLongFunction<? super T> mapper) {
        return new LongArrayGrid(rows, columns, (r, c) -> mapper.applyAsLong(values[r * columns + c].get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this grid
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntGrid mapToInt(ToIntFunction<? super T> mapper) {
        return new IntArrayGrid(rows, columns, (r, c) -> mapper.applyAsInt(values[r * columns + c].get()));
    }

    /**
     * {@inheritDoc}
     *
     * @param g      The grid of which to merge this grid with
     * @param merger The merger function of which to handle the merging of the two grids
     * @param <U>    {@inheritDoc}
     * @param <V>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IncompatibleDimensionsException {@inheritDoc}
     * @throws NullPointerException            {@inheritDoc}
     */
    @Override
    public <U, V> Grid<V> merge(Grid<U> g, BiFunction<? super T, ? super U, ? extends V> merger)
            throws IncompatibleDimensionsException {
        if (rows != g.rows() || columns != g.columns()) {
            throw new IncompatibleDimensionsException();
        }

        AtomicArrayGrid<V> result = new AtomicArrayGrid<>(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int i = r * columns + c;
                result.values[i].set(merger.apply(values[i].get(), g.get(r, c)));
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
     */
    @Override
    public Grid<T> resize(int rows, int columns) {
        AtomicArrayGrid<T> result = new AtomicArrayGrid<>(rows, columns);

        int minRows = Math.min(rows, this.rows);
        int minColumns = Math.min(columns, this.columns);

        for (int r = 0; r < minRows; r++) {
            for (int c = 0; c < minColumns; c++) {
                result.values[r * minColumns + c].set(values[r * columns + c].get());
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
    public Grid<T> transpose() {
        AtomicArrayGrid<T> result = new AtomicArrayGrid<>(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c * columns + r].set(values[r * columns + c].get());
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
    public Stream<T> stream() {
        return Arrays.stream(values).map(AtomicReference::get);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) stream().toArray();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each element
     */
    @Override
    public void forEachIndexed(BinaryIndexedConsumer<? super T> action) {
        int i = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                action.accept(r, c, values[i++].get());
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash((Object) toArray());
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Grid<?> g)) return false;
        if (rows != g.rows() || columns != g.columns()) return false;

        int i = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!Objects.equals(values[i++].get(), g.get(r, c))) return false;
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
