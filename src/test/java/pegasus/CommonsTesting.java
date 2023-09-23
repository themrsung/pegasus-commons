package pegasus;

import pegasus.util.MassUnit;

public class CommonsTesting {
    public static void main(String[] args) {
        double massKg = 1055;
        double massLb = MassUnit.convert(MassUnit.KILOGRAM, MassUnit.POUND, massKg);

        System.out.println(massLb);
    }
}
