package pegasus;


import pegasus.event.EventManager;
import pegasus.event.SyncEventManager;
import pegasus.graphics.Triangle;
import pegasus.scheduler.AtomicScheduler;
import pegasus.scheduler.Scheduler;
import pegasus.tensor.Vector2;
import pegasus.tensor.Vector3;

public class LosAlamos {
    static EventManager eventManager = new SyncEventManager();
    static Scheduler scheduler = new AtomicScheduler();

    public static void main(String[] args) {
        var triangle = Triangle.of(Vector2.ZERO, Vector2.POSITIVE_X, Vector2.POSITIVE_Y);
        System.out.println(triangle.getArea());
    }
}
