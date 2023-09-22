package pegasus.event;

import java.lang.annotation.*;

/**
 * A marker annotation for declared event handler methods.
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
