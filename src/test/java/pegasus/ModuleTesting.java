package pegasus;

import pegasus.event.*;
import pegasus.scheduler.AtomicScheduler;
import pegasus.scheduler.Scheduler;

public class ModuleTesting {
    private static final EventManager eventManager = new SyncEventManager();
    private static final Scheduler scheduler = new AtomicScheduler();

    public static void main(String[] args) {
        eventManager.start();
        scheduler.start();

        scheduler.registerDelayedTask((t, d) -> stop(), 10000);
        eventManager.registerListener(new Object() {
            @EventHandler
            public void onEvent(Event event) {
                System.out.println(event);
            }
        });

        scheduler.registerRepeatingTask((t, d) -> eventManager.enqueueEvent(new Event()), 0);
    }

    public static void stop() {
        eventManager.interrupt();
        scheduler.interrupt();

        System.exit(0);
    }
}
