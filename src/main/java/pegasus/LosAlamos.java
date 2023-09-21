package pegasus;

import pegasus.tensor.Tensors;
import pegasus.tensor.Vector8;
import pegasus.tuple.Tuple;
import pegasus.tuple.Tuples;

public class LosAlamos {
    public static void main(String[] args) {
        Vector8 v = new Vector8(1, 2, 3, 4, 5, 6, 7, 8);
        Vector8 p = new Vector8(8, 7, 6, 5, 4, 3, 2, 1);

        Tuple<Double> t1 = Tensors.asBoxedTuple(v);
        Tuple<Double> t2 = Tensors.asBoxedTuple(p);
        Tuple<Double> t3 = Tuples.join(t1, t2);

        System.out.println(t3);
    }
}
