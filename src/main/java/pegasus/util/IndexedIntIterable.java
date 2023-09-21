package pegasus.util;

import pegasus.function.IndexedIntConsumer;

/**
 * An indexed iterable object of {@code double} values.
 */
public interface IndexedIntIterable extends Iterable<Integer> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    default void forEachIndexed(IndexedIntConsumer action) {
        int i = 0;
        for (Integer v : this) {
            int value;
            try {
                value = v;
            } catch (NullPointerException e) {
                value = 0;
            }

            action.accept(i, value);
        }
    }
}
