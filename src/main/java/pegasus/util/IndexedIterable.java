package pegasus.util;

import pegasus.function.IndexedConsumer;

/**
 * An indexed iterable object.
 *
 * @param <T> The type of element this iterable object iterates through
 */
public interface IndexedIterable<T> extends Iterable<T> {
    /**
     * Executes the provided action once for each element of this iterable object.
     *
     * @param action The action to be performed for each element
     */
    default void forEachIndexed(IndexedConsumer<? super T> action) {
        int i = 0;
        for (T t : this) {
            action.accept(i++, t);
        }
    }
}
