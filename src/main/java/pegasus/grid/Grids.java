package pegasus.grid;

import pegasus.exception.IllegalInstanceException;
import pegasus.function.IntBiFunction;
import pegasus.tuple.IntPair;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains grid utilities.
 *
 * @see BaseGrid
 * @see Grid
 * @see DoubleGrid
 * @see LongGrid
 * @see IntGrid
 */
public final class Grids {
    //
    // Boxing / Unboxing
    //

    /**
     * Returns a boxed grid of the provided grid {@code g}.
     *
     * @param g The grid of which to box into object form
     * @return The boxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Grid<Double> boxed(DoubleGrid g) {
        return new ArrayGrid<>(g.rows(), g.columns(), g::get);
    }

    /**
     * Returns a boxed grid of the provided grid {@code g}.
     *
     * @param g The grid of which to box into object form
     * @return The boxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Grid<Long> boxed(LongGrid g) {
        return new ArrayGrid<>(g.rows(), g.columns(), g::get);
    }

    /**
     * Returns a boxed grid of the provided grid {@code g}.
     *
     * @param g The grid of which to box into object form
     * @return The boxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Grid<Integer> boxed(IntGrid g) {
        return new ArrayGrid<>(g.rows(), g.columns(), g::get);
    }

    /**
     * Returns an unboxed grid of the provided numeric grid {@code g}.
     *
     * @param g The grid of which to unbox into primitive form
     * @return The unboxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static DoubleGrid unboxToDouble(Grid<? extends Number> g) {
        return new DoubleArrayGrid(g.rows(), g.columns(), (r, c) -> g.get(r, c).doubleValue());
    }

    /**
     * Returns an unboxed grid of the provided numeric grid {@code g}.
     *
     * @param g The grid of which to unbox into primitive form
     * @return The unboxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static LongGrid unboxToLong(Grid<? extends Number> g) {
        return new LongArrayGrid(g.rows(), g.columns(), (r, c) -> g.get(r, c).longValue());
    }

    /**
     * Returns an unboxed grid of the provided numeric grid {@code g}.
     *
     * @param g The grid of which to unbox into primitive form
     * @return The unboxed grid
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static IntGrid unboxToInt(Grid<? extends Number> g) {
        return new IntArrayGrid(g.rows(), g.columns(), (r, c) -> g.get(r, c).intValue());
    }

    //
    // Map Conversion
    //

    /**
     * Converts the provided grid into a map of {@link IntPair indices} and values.
     *
     * @param g   The grid of which to convert into a map
     * @param <T> The type of element the grid contains
     * @return The converted map
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static <T> Map<IntPair, T> toMap(Grid<? extends T> g) {
        Map<IntPair, T> result = new HashMap<>();
        g.forEachIndexed((r, c, v) -> result.put(new IntPair(r, c), v));
        return result;
    }

    /**
     * Converts the provided grid into a map of custom keys and values.
     *
     * @param g            The grid of which to convert into a map
     * @param keyGenerator The generator function to construct the keys
     * @param <T>          The type of element the grid contains
     * @param <K>          The key object to use
     * @return The converted map
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static <K, T> Map<K, T> toMap(Grid<? extends T> g, IntBiFunction<? extends K> keyGenerator) {
        Map<K, T> result = new HashMap<>();
        g.forEachIndexed((r, c, v) -> result.put(keyGenerator.apply(r, c), v));
        return result;
    }

    /**
     * Converts the provided grid into a map of {@link IntPair indices} and values.
     *
     * @param g The grid of which to convert into a map
     * @return The converted map
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Map<IntPair, Double> toMap(DoubleGrid g) {
        Map<IntPair, Double> result = new HashMap<>();
        g.forEachIndexed((r, c, v) -> result.put(new IntPair(r, c), v));
        return result;
    }

    /**
     * Converts the provided grid into a map of {@link IntPair indices} and values.
     *
     * @param g The grid of which to convert into a map
     * @return The converted map
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Map<IntPair, Long> toMap(LongGrid g) {
        Map<IntPair, Long> result = new HashMap<>();
        g.forEachIndexed((r, c, v) -> result.put(new IntPair(r, c), v));
        return result;
    }

    /**
     * Converts the provided grid into a map of {@link IntPair indices} and values.
     *
     * @param g The grid of which to convert into a map
     * @return The converted map
     * @throws NullPointerException When the provided grid {@code g} is {@code null}
     */
    public static Map<IntPair, Integer> toMap(IntGrid g) {
        Map<IntPair, Integer> result = new HashMap<>();
        g.forEachIndexed((r, c, v) -> result.put(new IntPair(r, c), v));
        return result;
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Grids() {
        throw new IllegalInstanceException(this);
    }
}
