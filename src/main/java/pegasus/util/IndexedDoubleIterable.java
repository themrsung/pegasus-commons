package pegasus.util;

import pegasus.function.IndexedDoubleConsumer;

/**
 * An indexed iterable object of {@code double} values.
 */
public interface IndexedDoubleIterable extends Iterable<Double> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    default void forEachIndexed(IndexedDoubleConsumer action) {
        int i = 0;
        for (Double v : this) {
            double value;
            try {
                value = v;
            } catch (NullPointerException e) {
                value = 0;
            }

            action.accept(i, value);
        }
    }
}
