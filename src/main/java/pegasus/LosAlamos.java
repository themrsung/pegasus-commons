package pegasus;

import pegasus.tensor.Quaternion;
import pegasus.tensor.Vector3;

public class LosAlamos {
    public static void main(String[] args) {
        double quarterTurn = Math.PI / 2;
        Vector3 axis = Vector3.POSITIVE_Z;

        Quaternion rotation = Quaternion.from(quarterTurn, 0, 0);
        Vector3 identity = new Vector3(0, 100, 0);

        System.out.println(rotation.direction());
    }
}
