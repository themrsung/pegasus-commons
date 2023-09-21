package pegasus;

import pegasus.pointer.DoublePointer;
import pegasus.pointer.ObjectPointer;

public class LosAlamos {
    public static void main(String[] args) {
        DoublePointer doubles = DoublePointer.to(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(doubles);

        doubles.reverse();

        System.out.println(doubles);

        doubles.sort();

        System.out.println(doubles);

        ObjectPointer<String> strings = ObjectPointer.to("hello", "world", "foo", "bar");
        strings.reverse();
        System.out.println(strings);
    }
}
