package pegasus.scheduler;

/**
 * A scheduler can execute tasks on a regular basis.
 */
public interface Scheduler {
    /**
     * Starts executing tasks registered to this scheduler.
     */
    void start();

    /**
     * Stops executing tasks registered to this scheduler.
     */
    void interrupt();

    /**
     * Clears the task list of this scheduler
     */
    void clearTasks();

    /**
     * Returns whether the provided task is registered to this scheduler.
     *
     * @param task The task registry object of which to query
     * @return {@code true} if the provided task is registered to this scheduler
     */
    boolean isRegistered(TaskRegistry task);

    /**
     * Registers the provided task to this scheduler to be executed once after the provided delay.
     *
     * @param task  The task of which to register to this scheduler
     * @param delay The initial delay of the task in milliseconds
     * @return The resulting task registry object
     * @throws NullPointerException When the provided task is {@code null}
     */
    TaskRegistry registerDelayedTask(Task task, long delay);

    /**
     * Registers the provided task to this scheduler to be executed regularly. The scheduler
     * will wait for the specified interval before the initial execution of the task.
     *
     * @param task     The task of which to register to this scheduler
     * @param interval The interval of the task in milliseconds
     * @return The resulting task registry object
     * @throws NullPointerException When the provided task is {@code null}
     */
    TaskRegistry registerRepeatingTask(Task task, long interval);

    /**
     * Registers the provided task to this scheduler to be executed regularly. The scheduler
     * will wait for the specified delay before the initial execution of the task.
     *
     * @param task     The task of which to register to this scheduler
     * @param interval The interval of the task in milliseconds
     * @param delay    The initial delay of the task in milliseconds
     * @return The resulting task registry object
     * @throws NullPointerException When the provided task is {@code null}
     */
    TaskRegistry registerRepeatingTask(Task task, long interval, long delay);

    /**
     * Unregisters the provided task.
     *
     * @param task The task registry object referencing the task of which to unregister
     */
    void unregisterTask(TaskRegistry task);
}
