package pegasus;

import pegasus.tensor.Vector4;
import pegasus.tuple.*;

public class LosAlamos {
    public static void main(String[] args) {
        Tuple<String> strings = Tuple.of("hello", "world", "foo", "bar");
        IntTuple lengths = strings.mapToInt(String::length);

        lengths.forEachIndexed((i, v) -> System.out.println(i + ": " + v));

        DoubleTuple doubles = lengths.mapToDouble(x -> x);
        Vector4 vector = Tuples.asVector4(doubles);

        System.out.println(vector);
    }
}
