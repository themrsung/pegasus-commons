package pegasus.scheduler;

/**
 * A synchronous scheduler.
 *
 * @see Scheduler
 */
public class SyncScheduler extends AbstractScheduler {
    /**
     * Creates a new synchronous scheduler.
     */
    public SyncScheduler() {
        super("SyncScheduler");
    }

    /**
     * Creates a new synchronous scheduler.
     *
     * @param name The name of this scheduler
     */
    public SyncScheduler(String name) {
        super(name);
    }
}
