package pegasus;

import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;

public class CommonsTesting {
    public static void main(String[] args) {
        Vector2 v = new Vector2(0, 100);
        Vector2 p = v.rotate(Math.PI / 2);

        System.out.println(p);

    }
}
