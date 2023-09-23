package pegasus;


import pegasus.pointer.DoubleArrayPointer;
import pegasus.pointer.DoublePointer;

public class CommonsTesting {
    public static void main(String[] args) {
        DoublePointer doubles = new DoubleArrayPointer(10, i -> Math.PI * i);
        doubles.getReference(3).set(-1);

        System.out.println(doubles);
    }
}
