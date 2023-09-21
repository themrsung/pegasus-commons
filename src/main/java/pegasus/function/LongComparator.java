package pegasus.function;

/**
 * A comparator function for primitive {@code long} values.
 */
@FunctionalInterface
public interface LongComparator {
    /**
     * Compares the provided values.
     *
     * @param left  The left value
     * @param right The right value
     * @return {@code 0} if the values are equal, {@code 1} is the left value
     * is greater, {@code -1} otherwise
     */
    int compare(long left, long right);
}
