package pegasus;


import pegasus.container.Containers;
import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Matrix;
import pegasus.tuple.DoubleTuple;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.DoubleSupplier;
import java.util.logging.Logger;

public class CommonsTesting {
    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<>();
    }

    public static void printDouble(DoubleSupplier s) {
        System.out.println(s.getAsDouble());
    }
}
