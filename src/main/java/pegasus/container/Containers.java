package pegasus.container;

import pegasus.exception.IllegalInstanceException;

/**
 * Contains container utilities.
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
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Containers() {
        throw new IllegalInstanceException(this);
    }
}
