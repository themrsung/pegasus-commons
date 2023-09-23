package pegasus.event;

import pegasus.exception.IllegalInstanceException;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

/**
 * Contains event utilities.
 *
 * @see Handleable
 * @see Event
 */
public final class Events {
    //
    // Handler Extraction
    //

    /**
     * Returns a collection of every valid event handler of the provided listener object. All methods,
     * including those declared in the listener's superclasses will be collected.
     *
     * @param listener The listener object of which to retrieve event handlers from
     * @param <T>      The type of the listener
     * @return A collection of every valid event handler of the provided event listener
     * @throws NullPointerException When the provided listener is {@code null}
     */
    public static <T> Collection<HandlerReference> getHandlersOf(T listener) {
        return getHandlersOf(listener.getClass(), listener);
    }

    /**
     * Returns a collection of every valid event handler.
     *
     * @param c   The class of the listener
     * @param l   The listener instance
     * @param <T> The type of listener
     * @return A collection of event handlers
     * @throws NullPointerException When a parameter is {@code null}
     */
    static <T> Collection<HandlerReference> getHandlersOf(Class<? extends T> c, T l) {
        Vector<HandlerReference> handlers = new Vector<>();

        for (Method method : c.getDeclaredMethods()) {
            if (!isValidHandler(method)) continue;
            handlers.add(new HandlerReference(l, method));
        }

        Class<?> superclass = c.getSuperclass();
        if (superclass != null) {
            handlers.addAll(getHandlersOf(superclass, l));
        }

        return handlers;
    }

    /**
     * Returns whether the provided method is a valid event handler method.
     *
     * @param handler The handler of which to validate
     * @return {@code true} if the method is a valid event handler
     * @throws NullPointerException When the method is {@code null}
     */
    static boolean isValidHandler(Method handler) {
        if (!handler.isAnnotationPresent(EventHandler.class)) return false;
        if (handler.getReturnType() != void.class) return false;
        if (handler.getParameterCount() != 1) return false;
        return Handleable.class.isAssignableFrom(handler.getParameterTypes()[0]);
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Events() {
        throw new IllegalInstanceException(this);
    }
}
