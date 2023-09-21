package pegasus;

import pegasus.tuple.IntTuple;
import pegasus.tuple.Tuple;

public class LosAlamos {
    public static void main(String[] args) {
        Tuple<String> strings = Tuple.of("hello", "world", "foo", "bar");
        IntTuple lengths = strings.mapToInt(String::length);

        lengths.forEachIndexed((i, v) -> System.out.println(i + ": " + v));
    }
}
