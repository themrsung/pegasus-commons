package pegasus;

import pegasus.container.DoubleContainer;
import pegasus.pointer.DoublePointer;

public class CommonsTesting {
    public static void main(String[] args) {
        DoublePointer doubles = DoublePointer.to(1);
        DoubleContainer reference = doubles.getReference(0);

        reference.set(100);
        
        System.out.println(doubles);
    }
}
