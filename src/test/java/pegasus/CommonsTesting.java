package pegasus;


import pegasus.container.Containers;
import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Matrix;
import pegasus.tuple.DoubleTuple;

import java.util.List;
import java.util.function.DoubleSupplier;

public class CommonsTesting {
    public static void main(String[] args) {
        List<?> list = List.of();
    }

    public static void printDouble(DoubleSupplier s) {
        System.out.println(s.getAsDouble());
    }
}
