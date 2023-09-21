package pegasus.tuple;

import pegasus.exception.IllegalInstanceException;
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
     * Joins the provided tuples into a single tuple.
     *
     * @param tuples The tuples to join into one tuple
     * @param <T>    The type of element to join to
     * @return The joint tuple
     * @throws NullPointerException When the provided array of tuples is {@code null}
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
    // Conversion
    //

    /**
     * Converts the provided tuple {@code t} into a vector, then returns the converted vector.
     *
     * @param t The tuple of which to convert into a vector
     * @return The converted vector
     * @throws NullPointerException When the provided tuple {@code t} is {@code null}
     */
    public static Vector2 asVector2(DoubleTuple t) {
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
    public static Vector3 asVector3(DoubleTuple t) {
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
    public static Vector4 asVector4(DoubleTuple t) {
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
    public static Quaternion asQuaternion(DoubleTuple t) {
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
