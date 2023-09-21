package pegasus.function;

/**
 * A comparator function for primitive {@code double} values.
 */
@FunctionalInterface
public interface DoubleComparator {
    /**
     * Compares the provided values.
     *
     * @param left  The left value
     * @param right The right value
     * @return {@code 0} if the values are equal, {@code 1} is the left value
     * is greater, {@code -1} otherwise
     */
    int compare(double left, double right);
}
