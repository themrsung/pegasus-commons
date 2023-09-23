package pegasus;

import pegasus.util.TemperatureUnit;

public class CommonsTesting {
    public static void main(String[] args) {
        double temp = 20;
        double converted = TemperatureUnit.convert(TemperatureUnit.CELSIUS, TemperatureUnit.FAHRENHEIT, temp);

        System.out.println(converted);
    }
}
