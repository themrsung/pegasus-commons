package pegasus;


import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Matrix;
import pegasus.tuple.DoubleTuple;

import java.util.function.DoubleSupplier;

public class CommonsTesting {
    public static void main(String[] args) {
        DoubleContainer container = DoubleContainer.of(1);

    }

    public static void printDouble(DoubleSupplier s) {
        System.out.println(s.getAsDouble());
    }
}
