package pegasus;


import pegasus.container.DoubleContainer;
import pegasus.container.ObjectContainer;
import pegasus.tensor.Vector3;

public class CommonsTesting {
    public static void main(String[] args) {
        DoubleContainer container = DoubleContainer.of(13);
        container.peek(System.out::println);
    }
}
