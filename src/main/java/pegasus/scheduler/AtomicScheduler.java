package pegasus.scheduler;

import pegasus.tuple.Carousel;

import java.util.Arrays;

/**
 * An atomic asynchronous scheduler which utilizes multiple threads with their independent
 * task lists and metadata. Individual threads have no access to other threads of this scheduler.
 *
 * @see Scheduler
 */
public class AtomicScheduler implements Scheduler {
    /**
     * A low thread count.
     */
    public static final int THREAD_COUNT_LOW = 4;

    /**
     * A medium thread count.
     */
    public static final int THREAD_COUNT_MEDIUM = 8;

    /**
     * A high thread count.
     */
    public static final int THREAD_COUNT_HIGH = 16;

    /**
     * Creates a new atomic scheduler with {@link #THREAD_COUNT_LOW the default number of} threads.
     */
    public AtomicScheduler() {
        this(THREAD_COUNT_LOW);
    }

    /**
     * Creates a new atomic scheduler.
     *
     * @param threadCount The number of threads to initialize
     */
    public AtomicScheduler(int threadCount) {
        this(threadCount, "AtomicScheduler");
    }

    /**
     * Creates a new atomic scheduler.
     *
     * @param threadCount The number of threads to initialize
     * @param name        The name of this scheduler
     */
    public AtomicScheduler(int threadCount, String name) {
        AtomicSchedulerThread[] threadArray = new AtomicSchedulerThread[threadCount];
        Arrays.setAll(threadArray, i -> new AtomicSchedulerThread(name + i));
        this.threads = Carousel.of(threadArray);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        threads.forEach(AtomicSchedulerThread::start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        threads.forEach(AtomicSchedulerThread::interrupt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearTasks() {
        threads.forEach(AtomicSchedulerThread::clearTasks);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task registry object of which to query
     * @return {@inheritDoc}
     */
    @Override
    public boolean isRegistered(TaskRegistry task) {
        return threads.stream().anyMatch(t -> t.isRegistered(task));
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
        return threads.next().registerDelayedTask(task, delay);
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
        return threads.next().registerRepeatingTask(task, interval);
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
        return threads.next().registerRepeatingTask(task, interval, delay);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task registry object referencing the task of which to unregister
     */
    @Override
    public void unregisterTask(TaskRegistry task) {
        threads.forEach(t -> t.unregisterTask(task));
    }

    /**
     * The carousel of scheduler threads.
     */
    protected final Carousel<AtomicSchedulerThread> threads;

    /**
     * An individual scheduler thread of an atmoic scheduler.
     */
    protected static class AtomicSchedulerThread extends AbstractScheduler {
        /**
         * Creates a new scheduler thread.
         *
         * @param name The name of this thread
         */
        public AtomicSchedulerThread(String name) {
            super(name);
        }
    }
}
