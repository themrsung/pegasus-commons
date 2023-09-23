package pegasus.util;

/**
 * A unit of length.
 *
 * @see MassUnit
 * @see SpeedUnit
 * @see TemperatureUnit
 */
public enum LengthUnit {
    //
    // Metric Units
    //

    MILLIMETER(1000),
    CENTIMETER(100),
    METER(1),
    KILOMETER(1d / 1000),

    //
    // Imperial Units
    //

    INCH(39.3701),
    FOOT(3.28083),
    YARD(1.09361),
    MILE(0.000621371),
    NAUTICAL_MILE(0.000539957),

    //
    // Scientific Units
    //

    LIGHT_YEAR(1.057e-16);

    /**
     * Creates a new unit of length.
     *
     * @param meterConversionFactor The scale factor required to convert to meters
     */
    LengthUnit(double meterConversionFactor) {
        this.meterConversionFactor = meterConversionFactor;
    }

    /**
     * The scale factor required to convert this unit to meters.
     */
    private final double meterConversionFactor;

    /**
     * Converts the provided length into meters.
     *
     * @param length The length of which to convert into meters
     * @return The provided length in meters
     */
    public double toMeter(double length) {
        return length * meterConversionFactor;
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
    public static double convert(LengthUnit src, LengthUnit dest, double value) {
        return value / src.meterConversionFactor * dest.meterConversionFactor;
    }
}
