package pegasus.util;

/**
 * A unit of mass.
 *
 * @see LengthUnit
 * @see SpeedUnit
 * @see TemperatureUnit
 */
public enum MassUnit {
    //
    // Metric Units
    //

    NANOGRAM(1e+12),
    MICROGRAM(1e+9),
    MILLIGRAM(1000000),
    GRAM(1000),
    KILOGRAM(1),
    TON(0.001),

    //
    // Imperial Units
    //

    GRAIN(15432.4),
    STONE(0.15747285713507810923),
    POUND(2.20462),
    OUNCE(35.2739199982575),
    IMPERIAL_TON(0.000984207),

    //
    // Constants
    //

    HAMBURGER(7.05477325959),
    STARGATE(0.00003444722),
    LUNAR_MASS(1.3609751e-23),
    MARS(1.5649452e-24),
    EARTH(1.6744809e-25),
    SOLAR_MASS(5.0276521e-31);

    /**
     * Creates a new unit of mass.
     *
     * @param kilogramConversionFactor The scale factor required to convert to kilograms
     */
    MassUnit(double kilogramConversionFactor) {
        this.kilogramConversionFactor = kilogramConversionFactor;
    }

    /**
     * The scale factor required to convert this unit to kilograms.
     */
    private final double kilogramConversionFactor;

    /**
     * Converts the provided mass into kilograms.
     *
     * @param mass The mass of which to convert into kilograms
     * @return The provided mass in kilograms
     */
    public double toKilogram(double mass) {
        return mass * kilogramConversionFactor;
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
    public static double convert(MassUnit src, MassUnit dest, double value) {
        return value / src.kilogramConversionFactor * dest.kilogramConversionFactor;
    }
}
