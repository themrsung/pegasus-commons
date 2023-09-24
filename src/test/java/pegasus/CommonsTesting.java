package pegasus;


import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Matrix;
import pegasus.tuple.DoubleTuple;

public class CommonsTesting {
    public static void main(String[] args) {
        Matrix m = new Matrix(3, 3);
        m.getReference(1, 1).set(1000);

        System.out.println(m);
    }
}
