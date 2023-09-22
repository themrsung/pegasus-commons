package pegasus.event;

import java.lang.annotation.*;

/**
 * A marker annotation for declared event handler methods. Handles methods must have only
 * one input parameter which is an instance of {@link Handleable}, return the primitive type
 * {@code void}, and be marked as an event handler with this annotation. (the return type
 * {@link Void} is invalid)
 *
 * @see Handleable
 * @see Event
 * @see EventManager
 * @see HandlerPriority
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns the execution priority of this event handler.
     *
     * @return The execution priority of this event handler
     */
    HandlerPriority priority() default HandlerPriority.MEDIUM;
}
