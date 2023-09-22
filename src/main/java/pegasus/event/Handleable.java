package pegasus.event;

import java.io.Serializable;
import java.util.UUID;

/**
 * The base interface for handleable objects. Handleable objects can be enqueued to an
 * event manager as an event in order to be processed.
 */
public interface Handleable extends Serializable {
    /**
     * Returns the unique identifier of this event.
     *
     * @return The unique identifier of this event
     */
    UUID getUniqueId();

    /**
     * Returns the cause of this event. If there is no cause, this will return {@code null}.
     *
     * @return The cause of this event if specified, {@code null} otherwise
     */
    Handleable getCause();

    /**
     * Checks for equality between this event and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return If the provided object is the same instance as this object ({@code ==})
     */
    boolean equals(Object obj);

    /**
     * Serializes this event into a string.
     *
     * @return The string representation of this event
     */
    String toString();
}
