package pegasus;


import pegasus.event.EventManager;
import pegasus.event.SyncEventManager;
import pegasus.graphics.Triangle;
import pegasus.graphics.Vertex3;
import pegasus.scheduler.AtomicScheduler;
import pegasus.scheduler.Scheduler;
import pegasus.tensor.Vector2;
import pegasus.tensor.Vector3;

public class LosAlamos {
    static EventManager eventManager = new SyncEventManager();
    static Scheduler scheduler = new AtomicScheduler();

    public static void main(String[] args) {
        long t1, t2;

        Vector3 v = Vector3.POSITIVE_Y;
        Vertex3 vx = Vertex3.UP;

        t1 = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            v.norm();
        }

        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);



        t1 = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            vx.norm();
        }

        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
