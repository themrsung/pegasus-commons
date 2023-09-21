package pegasus.util;

import pegasus.function.BinaryIndexedLongConsumer;

/**
 * A binary indexed iterable object of {@code long} values.
 */
public interface BinaryIndexedLongIterable extends Iterable<Long> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    void forEachIndexed(BinaryIndexedLongConsumer action);
}
