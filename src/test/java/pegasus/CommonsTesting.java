package pegasus;


import pegasus.container.DoubleContainer;
import pegasus.container.ObjectContainer;
import pegasus.tensor.Vector3;

public class CommonsTesting {
    public static void main(String[] args) {
        ObjectContainer<Vector3> vector = ObjectContainer.of(Vector3.POSITIVE_X);
        vector.update(Vector3::normalize);

        System.out.println(vector);
    }
}
