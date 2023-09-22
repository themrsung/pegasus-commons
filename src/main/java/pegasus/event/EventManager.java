package pegasus.event;

import pegasus.pointer.ObjectPointer;
import pegasus.tuple.Tuple;

import java.util.Collection;

/**
 * An event manager.
 */
public interface EventManager {
    /**
     * Starts processing events in the event queue.
     */
    void start();

    /**
     * Stops processing events in the event queue.
     */
    void interrupt();

    /**
     * Clears the event queue, removing all events from the queue.
     */
    void clearQueue();

    /**
     * Enqueues the provided event to this event manager's queue.
     *
     * @param event The event of which to enqueue
     * @param <E>   The type of event of which to enqueue
     * @throws NullPointerException When the provided event is {@code null}
     */
    <E extends Handleable> void enqueue(E event);

    /**
     * Registers an event listener to this event manager.
     *
     * @param listener The listener of which to register to this event manager
     * @throws NullPointerException When the provided listener is {@code null}
     */
    void registerListener(Object listener);

    /**
     * Registers the provided listeners to this event manager.
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException When the provided array is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void registerListeners(Object... listeners);

    /**
     * Registers the provided listeners to this event manager.
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException When the provided collection is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void registerListeners(Collection<?> listeners);

    /**
     * Registers the provided listeners to this event manager.
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException When the provided tuple is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void registerListeners(Tuple<?> listeners);

    /**
     * Registers the provided listeners to this event manager.
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException When the provided pointer is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void registerListeners(ObjectPointer<?> listeners);

    /**
     * Unregisters an event listener from this event manager.
     *
     * @param listener The listener of which to unregister from this event manager
     * @throws NullPointerException When the provided listener is {@code null}
     */
    void unregisterListener(Object listener);

    /**
     * Unregisters the provided listeners from this event manager.
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException When the provided array is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void unregisterListeners(Object... listeners);

    /**
     * Unregisters the provided listeners from this event manager.
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException When the provided collection is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void unregisterListeners(Collection<?> listeners);

    /**
     * Unregisters the provided listeners from this event manager.
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException When the provided tuple is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void unregisterListeners(Tuple<?> listeners);

    /**
     * Unregisters the provided listeners from this event manager.
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException When the provided pointer is {@code null}, or when
     *                              it contains a {@code null} value
     */
    void unregisterListeners(ObjectPointer<?> listeners);
}
