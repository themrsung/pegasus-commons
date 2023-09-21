package pegasus.util;

import pegasus.function.BinaryIndexedConsumer;

/**
 * A binary indexed iterable object.
 *
 * @param <T> The type of element this iterable object iterates through
 */
public interface BinaryIndexedIterable<T> extends Iterable<T> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    void forEachIndexed(BinaryIndexedConsumer<? super T> action);
}
