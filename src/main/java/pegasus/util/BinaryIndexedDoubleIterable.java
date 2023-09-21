package pegasus.util;

import pegasus.function.BinaryIndexedDoubleConsumer;

/**
 * A binary indexed iterable object of {@code double} values.
 */
public interface BinaryIndexedDoubleIterable extends Iterable<Double> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    void forEachIndexed(BinaryIndexedDoubleConsumer action);
}
