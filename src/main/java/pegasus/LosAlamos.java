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

        Vector3 v = new Vector3(1, 2, 3);
        Vertex3 vx = new Vertex3(1, 2, 3);

        t1 = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            v.norm();
        }

        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);



        t1 = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            vx.norm();
        }

        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
