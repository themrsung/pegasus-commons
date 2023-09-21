package pegasus.grid;

import pegasus.exception.IllegalInstanceException;

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
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Grids() {
        throw new IllegalInstanceException(this);
    }
}
