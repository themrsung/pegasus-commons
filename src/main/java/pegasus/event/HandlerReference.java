package pegasus.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * A reference to a declared handler method.
 */
public class HandlerReference {
    /**
     * Creates a new handler reference.
     *
     * @param instance The listener instance
     * @param method   The handler method
     * @throws IllegalArgumentException When the method is invalid
     * @throws NullPointerException     When a {@code null} parameter is provided
     */
    public HandlerReference(Object instance, Method method) {
        this.instance = Objects.requireNonNull(instance);

        if (!Events.isValidHandler(method)) {
            throw new IllegalArgumentException("Invalid handler method.");
        }

        this.method = method;

        // Attempt to make method visible

        try {
            method.setAccessible(true);
        } catch (Throwable ignored) {
        }
    }

    /**
     * The listener instance.
     */
    protected final Object instance;

    /**
     * The handler method.
     */
    protected final Method method;

    /**
     * Returns whether this event handler accepts the provided event class.
     *
     * @param eventClass The event class of which to check for acceptance
     * @param <E>        The type of event of which to check for acceptance
     * @return {@code true} if this event handler accepts the provided class as its event parameter
     * @throws NullPointerException When the provided event class is {@code null}
     */
    public <E extends Handleable> boolean accepts(Class<E> eventClass) {
        return method.getParameterTypes()[0].isAssignableFrom(eventClass);
    }

    /**
     * Invokes this event handler.
     *
     * @param event The event parameter
     * @param <E>   The type of the event parameter
     * @throws InvocationTargetException When the event handler produces an exception
     * @throws IllegalAccessException    Then the event handler is inaccessible
     */
    public <E extends Handleable> void invoke(E event)
            throws InvocationTargetException, IllegalAccessException {
        method.invoke(instance, event);
    }

    /**
     * Returns the execution priority of this event handler.
     *
     * @return The execution priority of this event handler
     */
    public HandlerPriority priority() {
        return method.getAnnotation(EventHandler.class).priority();
    }
}
