package pegasus.tuple;

import pegasus.exception.IllegalInstanceException;
import pegasus.pointer.*;
import pegasus.tensor.*;

import java.util.Arrays;

/**
 * Contains tuple utilities.
 *
 * @see BaseTuple
 * @see Tuple
 * @see DoubleTuple
 * @see LongTuple
 * @see IntTuple
 */
public final class Tuples {
    //
    // Joining
    //

    /**
     * Joins the provided tuples into a single tuple. Note that IntelliJ IDEA throws a
     * warning for unchecked generic array handling, but it can be safely ignored.
     *
     * @param tuples The tuples to join into one tuple
     * @param <T>    The type of element to join to
     * @return The joint tuple
     * @throws NullPointerException When the provided array of tuples is {@code null}, or there
     *                              is a {@code null} tuple within the array
     */
    @SuppressWarnings("unchecked")
    public static <T> Tuple<T> join(Tuple<? extends T>... tuples) {
        int totalSize = Arrays.stream(tuples).mapToInt(Tuple::size).sum();
        int i = 0;

        T[] values = (T[]) new Object[totalSize];

        for (Tuple<? extends T> tuple : tuples) {
            for (int j = 0; j < tuple.size(); j++) {
                values[i++] = tuple.get(j);
            }
        }

        return Tuple.of(values);
    }

    //
    // Boxing / Unboxing
    //

    /**
     * Converts the provided tuple {@code t} into boxed object form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into boxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Tuple<Double> boxed(DoubleTuple t) {
        return Tuple.from(t.stream().boxed());
    }

    /**
     * Converts the provided tuple {@code t} into boxed object form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into boxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Tuple<Long> boxed(LongTuple t) {
        return Tuple.from(t.stream().boxed());
    }

    /**
     * Converts the provided tuple {@code t} into boxed object form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into boxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Tuple<Integer> boxed(IntTuple t) {
        return Tuple.from(t.stream().boxed());
    }

    /**
     * Converts the provided tuple {@code t} into unboxed primitive form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into unboxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static DoubleTuple unboxToDouble(Tuple<? extends Number> t) {
        return DoubleTuple.from(t.stream().mapToDouble(Number::doubleValue));
    }

    /**
     * Converts the provided tuple {@code t} into unboxed primitive form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into unboxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static LongTuple unboxToLong(Tuple<? extends Number> t) {
        return LongTuple.from(t.stream().mapToLong(Number::longValue));
    }

    /**
     * Converts the provided tuple {@code t} into unboxed primitive form, then returns
     * the converted tuple.
     *
     * @param t The tuple of which to convert into unboxed form
     * @return The converted tuple
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static IntTuple unboxToInt(Tuple<? extends Number> t) {
        return IntTuple.from(t.stream().mapToInt(Number::intValue));
    }

    //
    // Pointer Conversion
    //

    /**
     * Returns a pointer created from the values of the provided tuple {@code t}.
     *
     * @param t   The tuple of which to extract the values from
     * @param <T> The type of element of which to reference
     * @return The converted pointer
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static <T> ObjectPointer<T> toPointer(Tuple<? extends T> t) {
        return ObjectPointer.from(t.stream());
    }

    /**
     * Returns a pointer created from the values of the provided tuple {@code t}.
     *
     * @param t The tuple of which to extract the values from
     * @return The converted pointer
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static DoublePointer toPointer(DoubleTuple t) {
        return DoublePointer.from(t.stream());
    }

    /**
     * Returns a pointer created from the values of the provided tuple {@code t}.
     *
     * @param t The tuple of which to extract the values from
     * @return The converted pointer
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static LongPointer toPointer(LongTuple t) {
        return LongPointer.from(t.stream());
    }

    /**
     * Returns a pointer created from the values of the provided tuple {@code t}.
     *
     * @param t The tuple of which to extract the values from
     * @return The converted pointer
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static IntPointer toPointer(IntTuple t) {
        return IntPointer.from(t.stream());
    }

    //
    // Vector Conversion
    //

    /**
     * Converts the provided tuple {@code t} into a vector, then returns the converted vector.
     *
     * @param t The tuple of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Vector2 toVector2(DoubleTuple t) {
        if (t.size() != 2) {
            throw new IllegalArgumentException("The provided tuple's size is not 2.");
        }

        return new Vector2(t.get(0), t.get(1));
    }

    /**
     * Converts the provided tuple {@code t} into a vector, then returns the converted vector.
     *
     * @param t The tuple of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Vector3 toVector3(DoubleTuple t) {
        if (t.size() != 3) {
            throw new IllegalArgumentException("The provided tuple's size is not 3.");
        }

        return new Vector3(t.get(0), t.get(1), t.get(2));
    }

    /**
     * Converts the provided tuple {@code t} into a vector, then returns the converted vector.
     *
     * @param t The tuple of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Vector4 toVector4(DoubleTuple t) {
        if (t.size() != 4) {
            throw new IllegalArgumentException("The provided tuple's size is not 4.");
        }

        return new Vector4(t.get(0), t.get(1), t.get(2), t.get(3));
    }

    /**
     * Converts the provided tuple {@code t} into a vector, then returns the converted vector.
     *
     * @param t The tuple of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Quaternion toQuaternion(DoubleTuple t) {
        if (t.size() != 4) {
            throw new IllegalArgumentException("The provided tuple's size is not 4.");
        }

        return new Quaternion(t.get(0), t.get(1), t.get(2), t.get(3));
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Tuples() {
        throw new IllegalInstanceException(this);
    }
}
