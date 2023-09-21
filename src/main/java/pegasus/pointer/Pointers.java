package pegasus.pointer;

import pegasus.exception.IllegalInstanceException;
import pegasus.function.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Contains pointer utilities.
 */
public final class Pointers {
    //
    // Internal
    //

    /**
     * Shuffles the provided array.
     *
     * @param values The values of which to shuffle
     * @param <T>    The type of value to shuffle
     * @throws NullPointerException When the provided array is {@code null}
     */
    static <T> void shuffleArray(T[] values) {
        Random random = new Random();
        int size = values.length;

        for (int i = (size - 1); i > 0; i--) {
            int j = random.nextInt(i);

            T temp = values[j];
            values[j] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Shuffles the provided array.
     *
     * @param values The values of which to shuffle
     * @throws NullPointerException When the provided array is {@code null}
     */
    static void shuffleArray(double[] values) {
        Random random = new Random();
        int size = values.length;

        for (int i = (size - 1); i > 0; i--) {
            int j = random.nextInt(i);

            var temp = values[j];
            values[j] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Shuffles the provided array.
     *
     * @param values The values of which to shuffle
     * @throws NullPointerException When the provided array is {@code null}
     */
    static void shuffleArray(long[] values) {
        Random random = new Random();
        int size = values.length;

        for (int i = (size - 1); i > 0; i--) {
            int j = random.nextInt(i);

            var temp = values[j];
            values[j] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Shuffles the provided array.
     *
     * @param values The values of which to shuffle
     * @throws NullPointerException When the provided array is {@code null}
     */
    static void shuffleArray(int[] values) {
        Random random = new Random();
        int size = values.length;

        for (int i = (size - 1); i > 0; i--) {
            int j = random.nextInt(i);

            var temp = values[j];
            values[j] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Sorts the provided array in their natural ascending order.
     *
     * @param values The values of which to sort
     * @param <T>    The type of value to sort
     * @throws ClassCastException   When the object is not comparable
     * @throws NullPointerException When the provided array is {@code null}
     */
    @SuppressWarnings("unchecked")
    static <T> void sortArray(T[] values) {
        Arrays.sort(values, (v1, v2) -> ((Comparable<T>) v1).compareTo(v2));
    }

    /**
     * Sorts the provided array using the provided comparator function.
     *
     * @param values The values of which to sort
     * @param sorter The comparator function of which to use
     * @throws NullPointerException When the provided comparator function is {@code null}
     */
    static void sortArray(double[] values, DoubleComparator sorter) {
        var sorted = Arrays.stream(values).boxed().sorted(sorter::compare).toArray(Double[]::new);
        for (int i = 0; i < values.length; i++) {
            values[i] = sorted[i];
        }
    }

    /**
     * Sorts the provided array using the provided comparator function.
     *
     * @param values The values of which to sort
     * @param sorter The comparator function of which to use
     * @throws NullPointerException When the provided comparator function is {@code null}
     */
    static void sortArray(long[] values, LongComparator sorter) {
        var sorted = Arrays.stream(values).boxed().sorted(sorter::compare).toArray(Long[]::new);
        for (int i = 0; i < values.length; i++) {
            values[i] = sorted[i];
        }
    }

    /**
     * Sorts the provided array using the provided comparator function.
     *
     * @param values The values of which to sort
     * @param sorter The comparator function of which to use
     * @throws NullPointerException When the provided comparator function is {@code null}
     */
    static void sortArray(int[] values, IntComparator sorter) {
        var sorted = Arrays.stream(values).boxed().sorted(sorter::compare).toArray(Integer[]::new);
        for (int i = 0; i < values.length; i++) {
            values[i] = sorted[i];
        }
    }

    /**
     * Reverses the order of the two elements.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return {@code -1}
     */
    static int reverseCompare(Object v1, Object v2) {
        return -1;
    }

    /**
     * Reverses the order of the two elements.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return {@code -1}
     */
    static int reverseCompare(double v1, double v2) {
        return -1;
    }

    /**
     * Reverses the order of the two elements.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return {@code -1}
     */
    static int reverseCompare(long v1, long v2) {
        return -1;
    }

    /**
     * Reverses the order of the two elements.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return {@code -1}
     */
    static int reverseCompare(int v1, int v2) {
        return -1;
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Pointers() {
        throw new IllegalInstanceException(this);
    }
}
