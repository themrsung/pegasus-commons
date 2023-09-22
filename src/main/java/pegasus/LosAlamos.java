package pegasus;


import pegasus.event.EventManager;
import pegasus.event.SyncEventManager;
import pegasus.scheduler.*;

public class LosAlamos {
    static EventManager eventManager = new SyncEventManager();
    static Scheduler scheduler = new AtomicScheduler();

    public static void main(String[] args) {
        scheduler.start();

        scheduler.registerRepeatingTask(Task.LOG_CURRENT_TIME_AND_DELTA, 50);
        scheduler.registerDelayedTask(Task.println("hello world"), 100);
        scheduler.registerDelayedTask((t, d) -> scheduler.interrupt(), 1000);
    }
}
