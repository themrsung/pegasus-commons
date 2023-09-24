package pegasus.container;

import pegasus.exception.IllegalInstanceException;
import pegasus.pointer.*;
import pegasus.tuple.*;

/**
 * Contains container utilities.
 *
 * @see ObjectContainer
 * @see DoubleContainer
 * @see LongContainer
 * @see FloatContainer
 * @see IntContainer
 */
public final class Containers {
    //
    // Read-only
    //

    /**
     * Creates a read-only reference to the provided container {@code c}'s value.
     *
     * @param c   The container of which to reference
     * @param <T> The type of object to reference
     * @return A read-only reference to the provided container {@code c}'s value
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static <T> ObjectContainer<T> readOnly(ObjectContainer<? extends T> c) {
        return new VariableReference<>(c, VariableReference.readOnlySetter());
    }

    /**
     * Creates a read-only reference to the provided container {@code c}'s value.
     *
     * @param c The container of which to reference
     * @return A read-only reference to the provided container {@code c}'s value
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static DoubleContainer readOnly(DoubleContainer c) {
        return new DoubleVariableReference(c, DoubleVariableReference.readOnlySetter());
    }

    /**
     * Creates a read-only reference to the provided container {@code c}'s value.
     *
     * @param c The container of which to reference
     * @return A read-only reference to the provided container {@code c}'s value
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static LongContainer readOnly(LongContainer c) {
        return new LongVariableReference(c, LongVariableReference.readOnlySetter());
    }

    /**
     * Creates a read-only reference to the provided container {@code c}'s value.
     *
     * @param c The container of which to reference
     * @return A read-only reference to the provided container {@code c}'s value
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static FloatContainer readOnly(FloatContainer c) {
        return new FloatVariableReference(c, FloatVariableReference.readOnlySetter());
    }

    /**
     * Creates a read-only reference to the provided container {@code c}'s value.
     *
     * @param c The container of which to reference
     * @return A read-only reference to the provided container {@code c}'s value
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static IntContainer readOnly(IntContainer c) {
        return new IntVariableReference(c, IntVariableReference.readOnlySetter());
    }

    //
    // Pointer Conversion
    //

    /**
     * Converts the provided container {@code c} into a pointer, then returns the converted pointer.
     *
     * @param c   The container of which to convert into a pointer
     * @param <T> The type of object to reference
     * @return The resulting pointer
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static <T> ObjectPointer<T> toPointer(ObjectContainer<? extends T> c) {
        return ObjectPointer.to(c.get());
    }

    /**
     * Converts the provided container {@code c} into a pointer, then returns the converted pointer.
     *
     * @param c The container of which to convert into a pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static DoublePointer toPointer(DoubleContainer c) {
        return DoublePointer.to(c.getAsDouble());
    }

    /**
     * Converts the provided container {@code c} into a pointer, then returns the converted pointer.
     *
     * @param c The container of which to convert into a pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static LongPointer toPointer(LongContainer c) {
        return LongPointer.to(c.getAsLong());
    }

    /**
     * Converts the provided container {@code c} into a pointer, then returns the converted pointer.
     *
     * @param c The container of which to convert into a pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static ObjectPointer<Float> toPointer(FloatContainer c) {
        return ObjectPointer.to(c.getAsFloat());
    }

    /**
     * Converts the provided container {@code c} into a pointer, then returns the converted pointer.
     *
     * @param c The container of which to convert into a pointer
     * @return The resulting pointer
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static IntPointer toPointer(IntContainer c) {
        return IntPointer.to(c.getAsInt());
    }

    //
    // Tuple Conversion
    //

    /**
     * Converts the provided container {@code c} into a tuple, then returns the converted tuple.
     *
     * @param c   The container of which to convert into a tuple
     * @param <T> The object of which to reference
     * @return The resulting tuple
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static <T> Tuple<T> toTuple(ObjectContainer<? extends T> c) {
        return Tuple.of(c.get());
    }

    /**
     * Converts the provided container {@code c} into a tuple, then returns the converted tuple.
     *
     * @param c The container of which to convert into a tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static DoubleTuple toTuple(DoubleContainer c) {
        return DoubleTuple.of(c.getAsDouble());
    }

    /**
     * Converts the provided container {@code c} into a tuple, then returns the converted tuple.
     *
     * @param c The container of which to convert into a tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static LongTuple toTuple(LongContainer c) {
        return LongTuple.of(c.getAsLong());
    }

    /**
     * Converts the provided container {@code c} into a tuple, then returns the converted tuple.
     *
     * @param c The container of which to convert into a tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static Tuple<Float> toTuple(FloatContainer c) {
        return Tuple.of(c.getAsFloat());
    }

    /**
     * Converts the provided container {@code c} into a tuple, then returns the converted tuple.
     *
     * @param c The container of which to convert into a tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided container {@code c} is {@code null}
     */
    public static IntTuple toTuple(IntContainer c) {
        return IntTuple.of(c.getAsInt());
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Containers() {
        throw new IllegalInstanceException(this);
    }
}
