package pegasus.util;

import pegasus.function.BinaryIndexedIntConsumer;

/**
 * A binary indexed iterable object of {@code int} values.
 */
public interface BinaryIndexedIntIterable extends Iterable<Integer> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    void forEachIndexed(BinaryIndexedIntConsumer action);
}
