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
            int j = random.nextInt(i - 1);

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
            int j = random.nextInt(i - 1);

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
            int j = random.nextInt(i - 1);

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
            int j = random.nextInt(i - 1);

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
        mergeSort(values, 0, values.length - 1, sorter);
    }

    /**
     * Sorts the provided array using the provided comparator function.
     *
     * @param values The values of which to sort
     * @param sorter The comparator function of which to use
     * @throws NullPointerException When the provided comparator function is {@code null}
     */
    static void sortArray(long[] values, LongComparator sorter) {
        mergeSort(values, 0, values.length - 1, sorter);
    }

    /**
     * Sorts the provided array using the provided comparator function.
     *
     * @param values The values of which to sort
     * @param sorter The comparator function of which to use
     * @throws NullPointerException When the provided comparator function is {@code null}
     */
    static void sortArray(int[] values, IntComparator sorter) {
        mergeSort(values, 0, values.length - 1, sorter);
    }

    /*
     * Merge Sort
     */

    static void mergeSort(double[] values, int left, int right, DoubleComparator sorter) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort the two halves
            mergeSort(values, left, middle, sorter);
            mergeSort(values, middle + 1, right, sorter);

            // Merge the sorted halves
            merge(values, left, middle, right, sorter);
        }
    }

    static void merge(double[] values, int left, int middle, int right, DoubleComparator sorter) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays to hold the left and right halves
        double[] leftArray = new double[n1];
        double[] rightArray = new double[n2];

        // Copy data to temporary arrays
        System.arraycopy(values, left, leftArray, 0, n1);

        for (int j = 0; j < n2; j++) {
            rightArray[j] = values[middle + 1 + j];
        }

        // Merge the two arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (sorter.compare(leftArray[i], rightArray[j]) <= 0) {
                values[k] = leftArray[i];
                i++;
            } else {
                values[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray and rightArray if any
        while (i < n1) {
            values[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            values[k] = rightArray[j];
            j++;
            k++;
        }
    }

    static void mergeSort(long[] values, int left, int right, LongComparator sorter) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort the two halves
            mergeSort(values, left, middle, sorter);
            mergeSort(values, middle + 1, right, sorter);

            // Merge the sorted halves
            merge(values, left, middle, right, sorter);
        }
    }

    static void merge(long[] values, int left, int middle, int right, LongComparator sorter) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays to hold the left and right halves
        long[] leftArray = new long[n1];
        long[] rightArray = new long[n2];

        // Copy data to temporary arrays
        System.arraycopy(values, left, leftArray, 0, n1);

        for (int j = 0; j < n2; j++) {
            rightArray[j] = values[middle + 1 + j];
        }

        // Merge the two arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (sorter.compare(leftArray[i], rightArray[j]) <= 0) {
                values[k] = leftArray[i];
                i++;
            } else {
                values[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray and rightArray if any
        while (i < n1) {
            values[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            values[k] = rightArray[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int[] values, int left, int right, IntComparator sorter) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Recursively sort the two halves
            mergeSort(values, left, middle, sorter);
            mergeSort(values, middle + 1, right, sorter);

            // Merge the sorted halves
            merge(values, left, middle, right, sorter);
        }
    }

    static void merge(int[] values, int left, int middle, int right, IntComparator sorter) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays to hold the left and right halves
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        System.arraycopy(values, left, leftArray, 0, n1);

        for (int j = 0; j < n2; j++) {
            rightArray[j] = values[middle + 1 + j];
        }

        // Merge the two arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (sorter.compare(leftArray[i], rightArray[j]) <= 0) {
                values[k] = leftArray[i];
                i++;
            } else {
                values[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray and rightArray if any
        while (i < n1) {
            values[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            values[k] = rightArray[j];
            j++;
            k++;
        }
    }

    static int reverseCompare(Object v1, Object v2) {
        return -1;
    }

    static int reverseCompare(double v1, double v2) {
        return -1;
    }

    static int reverseCompare(long v1, long v2) {
        return -1;
    }

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
