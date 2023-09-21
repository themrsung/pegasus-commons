package pegasus.util;

import pegasus.function.IndexedLongConsumer;

/**
 * An indexed iterable object of {@code long} values.
 */
public interface IndexedLongIterable extends Iterable<Long> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    default void forEachIndexed(IndexedLongConsumer action) {
        int i = 0;
        for (Long v : this) {
            long value;
            try {
                value = v;
            } catch (NullPointerException e) {
                value = 0;
            }

            action.accept(i, value);
        }
    }
}
