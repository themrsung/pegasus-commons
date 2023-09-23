package pegasus.util;

import java.util.function.DoubleUnaryOperator;

/**
 * A unit of temperature.
 *
 * @see LengthUnit
 * @see MassUnit
 * @see SpeedUnit
 */
public enum TemperatureUnit {
    CELSIUS(DoubleUnaryOperator.identity(), DoubleUnaryOperator.identity()),
    FAHRENHEIT(x -> (x - 32) * (5d / 9), x -> x * (9d / 5) + 32),
    KELVIN(x -> x - 273.15, x -> x + 273.15);

    /**
     * Creates a new unit of temperature.
     *
     * @param toCelsiusConversionFunction   The conversion function used to convert to Celsius
     * @param fromCelsiusConversionFunction The conversion function used to convert from Celsius
     */
    TemperatureUnit(
            DoubleUnaryOperator toCelsiusConversionFunction,
            DoubleUnaryOperator fromCelsiusConversionFunction
    ) {
        this.toCelsiusConversionFunction = toCelsiusConversionFunction;
        this.fromCelsiusConversionFunction = fromCelsiusConversionFunction;
    }

    /**
     * The conversion function to Celsius.
     */
    private final DoubleUnaryOperator toCelsiusConversionFunction;

    /**
     * The conversion function from Celsius.
     */
    private final DoubleUnaryOperator fromCelsiusConversionFunction;

    /**
     * Converts the provided temperature into Celsius.
     *
     * @param temperature The temperature of which to convert into Celsius
     * @return The provided temperature in Celsius
     */
    public double toCelsius(double temperature) {
        return toCelsiusConversionFunction.applyAsDouble(temperature);
    }

    /**
     * Converts the provided value from the source unit into the destination unit.
     *
     * @param src   The source unit
     * @param dest  The destination unit
     * @param value The value to convert
     * @return The converted value
     * @throws NullPointerException When a {@code null} unit is provided
     */
    public static double convert(TemperatureUnit src, TemperatureUnit dest, double value) {
        double celsius = src.toCelsiusConversionFunction.applyAsDouble(value);
        return dest.fromCelsiusConversionFunction.applyAsDouble(celsius);
    }
}
