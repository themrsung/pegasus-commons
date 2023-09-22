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

    public static <T> Collection<HandlerReference> getHandlersOf(T listener) {
        return getHandlersOf(listener.getClass(), listener);
    }

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
