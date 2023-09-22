package pegasus.scheduler;

import java.util.Objects;

/**
 * A task which can be registered to a scheduler.
 */
@FunctionalInterface
public interface Task {
    /**
     * Executes this task. If this is the initial execution, the {@code delta} will be
     * the amount of time since this task's registration.
     *
     * @param time  The current system time in milliseconds
     * @param delta The duration between the last execution and now in milliseconds
     */
    void execute(long time, long delta);

    /**
     * Logs the current system time to the default print stream.
     */
    Task LOG_CURRENT_TIME = (t, d) -> System.out.println("Current system time: " + t);

    /**
     * Logs the current system time and delta to the default print stream.
     */
    Task LOG_CURRENT_TIME_AND_DELTA = (t, d) -> System.out.println("Current system time: " + t + ", Delta: " + d);

    /**
     * Returns a task which prints the provided content message to the default print stream.
     *
     * @param content The content of which to print
     * @return The constructed task
     */
    static Task println(String content) {
        return (t, d) -> System.out.println(content);
    }

    /**
     * Returns a task which prints the provided object's serialized form to the default print stream.
     *
     * @param obj The object of which to print
     * @return The constructed task
     */
    static Task println(Object obj) {
        return (t, c) -> System.out.println(obj);
    }

    /**
     * Returns a task which unregisters the provided scheduler.
     * @param scheduler The scheduler of which to interrupt
     * @return The constructed task
     * @throws NullPointerException When the provided scheduler is {@code null}
     */
    static Task interruptScheduler(Scheduler scheduler) {
        Objects.requireNonNull(scheduler);
        return (t, c) -> scheduler.interrupt();
    }
}
