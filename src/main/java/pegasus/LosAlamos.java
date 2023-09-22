package pegasus;

import pegasus.tuple.StringTuple;
import pegasus.tuple.Tuple;

public class LosAlamos {
    public static void main(String[] args) {
        StringTuple test = Tuple.of("hello", "world");
        var test2 = test.map(String::toLowerCase);
    }
}
