package pegasus;


import pegasus.container.Containers;
import pegasus.container.DoubleContainer;
import pegasus.pointer.DoubleArrayPointer;
import pegasus.pointer.DoublePointer;
import pegasus.tensor.Matrix;
import pegasus.tuple.DoubleTuple;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.DoubleSupplier;
import java.util.logging.Logger;

public class CommonsTesting {
    public static void main(String[] args) {
        DoublePointer doubles = DoublePointer.to(1, 2, 3, 4, 5);
        
        doubles.forEachIndexed((i, v) -> System.out.println(i + ": " + v));
    }
}
