package pegasus;

import pegasus.tensor.Vector8;

public class LosAlamos {
    public static void main(String[] args) {
        Vector8 v = new Vector8(1, 2, 3, 4, 5, 6, 7, 8);
        Vector8 p = new Vector8(8, 7, 6, 5, 4, 3, 2, 1);
        System.out.println(v.add(p));
    }
}
