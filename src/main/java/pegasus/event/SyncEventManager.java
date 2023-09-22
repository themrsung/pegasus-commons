package pegasus.event;

/**
 * A synchronous event manager with an independent event queue and handler list.
 *
 * @see EventManager
 */
public class SyncEventManager extends AbstractEventManager {
    /**
     * Creates a new synchronous event manager.
     */
    public SyncEventManager() {
        super("SyncEventManager");
    }

    /**
     * Creates a new synchronous event manager.
     *
     * @param name The name of this event manager
     */
    public SyncEventManager(String name) {
        super(name);
    }
}
