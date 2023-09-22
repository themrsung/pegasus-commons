package pegasus.scheduler;

import java.util.Objects;

/**
 * Creates when a task is registered to a scheduler. Task registries are used to
 * reference registered tasks.
 */
public class TaskRegistry {
    /**
     * The constant for no delay.
     */
    public static final long NO_DELAY = -1;

    /**
     * The constant for no interval.
     */
    public static final long NO_INTERVAL = -1;

    /**
     * Creates a new task registry.
     *
     * @param task     The task which was registered
     * @param interval The interval of this task
     * @param delay    The initial delay of this task
     * @throws NullPointerException When the provided task is {@code null}
     */
    protected TaskRegistry(Task task, long interval, long delay) {
        this.task = Objects.requireNonNull(task);
        this.interval = interval;
        this.delay = delay;
    }

    /**
     * The task which was registered.
     */
    protected final Task task;

    /**
     * The interval of this task.
     */
    protected final long interval;

    /**
     * The initial delay of this task.
     */
    protected final long delay;

    /**
     * Executes this task. If this is the initial execution, the {@code delta} will be
     * the amount of time since this task's registration.
     *
     * @param time  The current system time in milliseconds
     * @param delta The duration between the last execution and now in milliseconds
     */
    public void execute(long time, long delta) {
        task.execute(time, delta);
    }

    /**
     * Returns the task of this registry.
     *
     * @return The task which was registered
     */
    public Task task() {
        return task;
    }

    /**
     * Returns the interval of this task registry.
     *
     * @return The interval of this task registry in milliseconds
     */
    public long interval() {
        return interval;
    }

    /**
     * Returns the initial delay of this task registry.
     *
     * @return The initial delay of this task registry
     */
    public long delay() {
        return delay;
    }

    /**
     * Returns the hash code of this task registry.
     *
     * @return The hash code of this task registry
     */
    @Override
    public int hashCode() {
        return Objects.hash(task, interval, delay);
    }

    /**
     * Checks for equality between this task registry and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the provided object is the same instance as this task registry
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
