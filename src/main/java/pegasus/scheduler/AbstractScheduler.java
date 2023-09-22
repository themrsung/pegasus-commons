package pegasus.scheduler;

import java.util.*;

/**
 * The abstract base class for scheduler implementations.
 */
public abstract class AbstractScheduler extends Thread implements Scheduler {
    /**
     * Creates a new scheduler.
     *
     * @param name The name of this scheduler
     */
    protected AbstractScheduler(String name) {
        this(new ArrayList<>(), new HashMap<>(), new HashMap<>(), name);
    }

    /**
     * Creates a new scheduler.
     *
     * @param tasks             The list of tasks to manage
     * @param registrationTimes The map of registration times to manage
     * @param executionTimes    The map of execution times to manage
     * @param name              The name of this scheduler
     * @throws NullPointerException When either the task list or the execution times map is {@code null}
     */
    protected AbstractScheduler(
            List<TaskRegistry> tasks,
            Map<TaskRegistry, Long> registrationTimes,
            Map<TaskRegistry, Long> executionTimes,
            String name
    ) {
        super(name);
        this.tasks = tasks;
        this.registrationTimes = registrationTimes;
        this.executionTimes = executionTimes;
    }

    /**
     * The list of tasks.
     */
    protected final List<TaskRegistry> tasks;

    /**
     * The map of registration times.
     */
    protected final Map<TaskRegistry, Long> registrationTimes;

    /**
     * The map of the most recent execution times.
     */
    protected final Map<TaskRegistry, Long> executionTimes;

    /**
     * Infinitely loops through the list of tasks, and executes them.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            for (TaskRegistry task : List.copyOf(tasks)) {
                long time = System.currentTimeMillis();
                long previous = executionTimes.getOrDefault(task, -1L);

                long delta;
                boolean executed;

                if (previous == -1) {
                    delta = time - registrationTimes.getOrDefault(task, time);
                    executed = false;
                } else {
                    delta = time - previous;
                    executed = true;
                }

                if (executed ? delta < task.interval : delta < task.delay) continue;

                task.execute(time, delta);
                executionTimes.put(task, time);

                if (task.interval == TaskRegistry.NO_INTERVAL) unregisterTask(task);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearTasks() {
        tasks.clear();
        registrationTimes.clear();
        executionTimes.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task registry object of which to query
     * @return {@inheritDoc}
     */
    @Override
    public boolean isRegistered(TaskRegistry task) {
        return tasks.contains(task);
    }

    /**
     * {@inheritDoc}
     *
     * @param task  The task of which to register to this scheduler
     * @param delay The initial delay of the task in milliseconds
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public TaskRegistry registerDelayedTask(Task task, long delay) {
        return registerRepeatingTask(task, TaskRegistry.NO_INTERVAL, delay);
    }

    /**
     * {@inheritDoc}
     *
     * @param task     The task of which to register to this scheduler
     * @param interval The interval of the task in milliseconds
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public TaskRegistry registerRepeatingTask(Task task, long interval) {
        return registerRepeatingTask(task, interval, TaskRegistry.NO_DELAY);
    }

    /**
     * {@inheritDoc}
     *
     * @param task     The task of which to register to this scheduler
     * @param interval The interval of the task in milliseconds
     * @param delay    The initial delay of the task in milliseconds
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public TaskRegistry registerRepeatingTask(Task task, long interval, long delay) {
        TaskRegistry registry = new TaskRegistry(task, interval, delay);

        tasks.add(registry);
        registrationTimes.put(registry, System.currentTimeMillis());

        return registry;
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task registry object referencing the task of which to unregister
     */
    @Override
    public void unregisterTask(TaskRegistry task) {
        tasks.remove(task);
        registrationTimes.remove(task);
        executionTimes.remove(task);
    }
}
