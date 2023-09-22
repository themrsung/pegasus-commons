package pegasus.pointer;

import pegasus.exception.IllegalInstanceException;
import pegasus.function.*;
import pegasus.tensor.*;
import pegasus.tuple.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Contains pointer utilities.
 */
public final class Pointers {
    //
    // Tuple Conversion
    //

    /**
     * Converts the provided pointer {@code p} into a tuple, then returns the converted tuple.
     *
     * @param p   The pointer of which to convert into a tuple
     * @param <T> The type of value to convert
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static <T> Tuple<T> toTuple(ObjectPointer<? extends T> p) {
        return Tuple.from(p.stream());
    }

    /**
     * Converts the provided pointer {@code p} into a tuple, then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static DoubleTuple toTuple(DoublePointer p) {
        return DoubleTuple.from(p.stream());
    }

    /**
     * Converts the provided pointer {@code p} into a tuple, then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static LongTuple toTuple(LongPointer p) {
        return LongTuple.from(p.stream());
    }

    /**
     * Converts the provided pointer {@code p} into a tuple, then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static IntTuple toTuple(IntPointer p) {
        return IntTuple.from(p.stream());
    }

    /**
     * Converts the provided pointer {@code p} into a boxed reference-based tuple,
     * then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a boxed tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Tuple<Double> toBoxedTuple(DoublePointer p) {
        return Tuple.from(p.stream().boxed());
    }

    /**
     * Converts the provided pointer {@code p} into a boxed reference-based tuple,
     * then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a boxed tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Tuple<Long> toBoxedTuple(LongPointer p) {
        return Tuple.from(p.stream().boxed());
    }

    /**
     * Converts the provided pointer {@code p} into a boxed reference-based tuple,
     * then returns the converted tuple.
     *
     * @param p The pointer of which to convert into a boxed tuple
     * @return The converted tuple
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Tuple<Integer> toBoxedTuple(IntPointer p) {
        return Tuple.from(p.stream().boxed());
    }

    //
    // Vector Conversion
    //

    /**
     * Converts the provided pointer {@code p} into a vector.
     *
     * @param p The pointer of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Vector2 toVector2(DoublePointer p) {
        if (p.size() != 2) {
            throw new IllegalArgumentException("The provided pointer's size is not 2.");
        }

        return new Vector2(p.get(0), p.get(1));
    }

    /**
     * Converts the provided pointer {@code p} into a vector.
     *
     * @param p The pointer of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Vector3 toVector3(DoublePointer p) {
        if (p.size() != 3) {
            throw new IllegalArgumentException("The provided pointer's size is not 3.");
        }

        return new Vector3(p.get(0), p.get(1), p.get(2));
    }

    /**
     * Converts the provided pointer {@code p} into a vector.
     *
     * @param p The pointer of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Vector4 toVector4(DoublePointer p) {
        if (p.size() != 4) {
            throw new IllegalArgumentException("The provided pointer's size is not 4.");
        }

        return new Vector4(p.get(0), p.get(1), p.get(2), p.get(3));
    }

    /**
     * Converts the provided pointer {@code p} into a quaternion.
     *
     * @param p The pointer of which to convert into a quaternion
     * @return The converted quaternion
     * @throws NullPointerException When the provided pointer {@code p} is {@code null}
     */
    public static Quaternion toQuaternion(DoublePointer p) {
        if (p.size() != 4) {
            throw new IllegalArgumentException("The provided pointer's size is not 4.");
        }

        return new Quaternion(p.get(0), p.get(1), p.get(2), p.get(3));
    }

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
