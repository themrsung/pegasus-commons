package pegasus;

import pegasus.graphics.Vertex3;
import pegasus.tensor.Vector3;
import pegasus.tuple.Carousel;
import pegasus.tuple.Tuple;

public class CommonsTesting {
    public static void main(String[] args) {
        long n = 10000000000L;
        long t1, t2;

        t1 = System.currentTimeMillis();

        for (long i = 0; i < n; i++) {
            Vector3.POSITIVE_X.normalize();
        }

        t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

        t1 = System.currentTimeMillis();

        for (long i = 0; i < n; i++) {
            Vertex3.RIGHT.normalize();
        }

        t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }
}
