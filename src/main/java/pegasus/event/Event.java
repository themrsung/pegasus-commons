package pegasus.event;

import java.io.Serial;
import java.util.UUID;

/**
 * The default implementation of {@link Handleable}. All Pegasus events extend this class.
 *
 * @see Handleable
 * @see EventHandler
 * @see EventManager
 */
public class Event implements Handleable {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new event with a random unique identifier and no cause.
     */
    public Event() {
        this(UUID.randomUUID(), null);
    }

    /**
     * Creates a new event with no cause.
     *
     * @param uniqueId The unique identifier of this event
     */
    public Event(UUID uniqueId) {
        this(uniqueId, null);
    }

    /**
     * Creates a new event with a random unique identifier.
     *
     * @param cause The cause of this event
     */
    public Event(Handleable cause) {
        this(UUID.randomUUID(), cause);
    }

    /**
     * Creates a new event.
     *
     * @param uniqueId The unique identifier of this event
     * @param cause    The cause of this event
     */
    public Event(UUID uniqueId, Handleable cause) {
        this.uniqueId = uniqueId;
        this.cause = cause;
    }

    /**
     * The unique identifier of this event.
     */
    protected final UUID uniqueId;

    /**
     * The cause of this event.
     */
    protected final Handleable cause;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Handleable getCause() {
        return cause;
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "uniqueId=" + uniqueId +
                ", cause=" + cause +
                '}';
    }
}
