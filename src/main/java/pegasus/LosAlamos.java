package pegasus;

import pegasus.tensor.Tensors;
import pegasus.tensor.Vector8;

public class LosAlamos {
    public static void main(String[] args) {

        Vector8 v = new Vector8(1, 2, 3, 4, 5, 6, 7, 8);
        Vector8 p = v.multiply(100);

        Vector8 q = Tensors.lerp(v, p, 0.5);

        System.out.println(q);
    }
}
