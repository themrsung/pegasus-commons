package pegasus;

import pegasus.tensor.Matrix;
import pegasus.tensor.Vector3;

public class LosAlamos {
    public static void main(String[] args) {
        var identity = Matrix.newIdentity(3);
        var vector = new Vector3(1, 2, 3);

        Vector3 result = identity.multiply(vector);

        System.out.println(result);
    }
}
