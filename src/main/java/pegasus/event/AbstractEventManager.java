package pegasus.event;

import pegasus.pointer.ObjectPointer;
import pegasus.tuple.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * The abstract base class for event manager implementations.
 */
public abstract class AbstractEventManager extends Thread implements EventManager {
    /**
     * Creates a new event manager.
     *
     * @param name The name of this manager
     */
    protected AbstractEventManager(String name) {
        this(new LinkedList<>(), new ArrayList<>(), name);
    }

    /**
     * Creates a new event manager.
     *
     * @param eventQueue The event queue of which to manage
     * @param handlers   The handler list of which to manage
     * @param name       The name of this manager
     * @throws NullPointerException When either the event queue of the handler list is {@code null}
     */
    protected AbstractEventManager(Queue<Handleable> eventQueue, List<HandlerReference> handlers, String name) {
        super(name);
        this.eventQueue = Objects.requireNonNull(eventQueue);
        this.handlers = Objects.requireNonNull(handlers);
    }

    /**
     * The event queue.
     */
    protected final Queue<Handleable> eventQueue;

    /**
     * The handler references.
     */
    protected final List<HandlerReference> handlers;

    /**
     * Infinitely polls from the event queue and handles them until interrupted.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Handleable event = eventQueue.poll();
            if (event == null) continue;

            for (HandlerReference handler : List.copyOf(handlers)) {
                if (!handler.accepts(event.getClass())) continue;

                try {
                    handler.invoke(event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace(System.out);
                }

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearQueue() {
        eventQueue.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @param event The event of which to enqueue
     * @param <E>   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <E extends Handleable> void enqueue(E event) {
        eventQueue.add(Objects.requireNonNull(event));
    }

    /**
     * {@inheritDoc}
     *
     * @param listener The listener of which to register to this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void registerListener(Object listener) {
        handlers.addAll(Events.getHandlersOf(listener));
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void registerListeners(Object... listeners) {
        for (Object listener : listeners) {
            handlers.addAll(Events.getHandlersOf(listener));
        }

        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void registerListeners(Collection<?> listeners) {
        handlers.addAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void registerListeners(Tuple<?> listeners) {
        handlers.addAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to register to this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void registerListeners(ObjectPointer<?> listeners) {
        handlers.addAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listener The listener of which to unregister from this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void unregisterListener(Object listener) {
        handlers.removeAll(Events.getHandlersOf(listener));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void unregisterListeners(Object... listeners) {
        handlers.removeAll(Arrays.stream(listeners).flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void unregisterListeners(Collection<?> listeners) {
        handlers.removeAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void unregisterListeners(Tuple<?> listeners) {
        handlers.removeAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The listeners of which to unregister from this event manager
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void unregisterListeners(ObjectPointer<?> listeners) {
        handlers.removeAll(listeners.stream().flatMap(listener -> Events.getHandlersOf(listener).stream()).toList());
    }
}
