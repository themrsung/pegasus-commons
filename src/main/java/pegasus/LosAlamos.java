package pegasus;

import pegasus.grid.Grids;
import pegasus.tensor.Quaternion;
import pegasus.tensor.Vector3;

public class LosAlamos {
    public static void main(String[] args) {
        var q = Quaternion.from(2, -3, 1);
        var m = q.toRotationMatrix();
        var vector = new Vector3(1, 2, 3);

        System.out.println(vector.rotate(q));
        System.out.println(m.multiply(vector));

        var map = Grids.toMap(m);

        System.out.println(map);
    }
}
