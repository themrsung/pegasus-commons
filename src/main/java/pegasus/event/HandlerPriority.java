package pegasus.event;

/**
 * The execution priority of an event handler. Lower enum ordinals will be executed
 * earlier than higher enum ordinals.
 */
public enum HandlerPriority {
    PREEMPTIVE,
    PRE_EARLY,
    EARLY,
    POST_EARLY,
    MEDIUM,
    PRE_LATE,
    LATE,
    POST_LATE,
    PERMISSIVE
}
