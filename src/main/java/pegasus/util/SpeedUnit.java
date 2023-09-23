package pegasus.util;

/**
 * A unit of speed.
 *
 * @see LengthUnit
 * @see MassUnit
 * @see TemperatureUnit
 */
public enum SpeedUnit {
    //
    // Metric Units
    //

    KILOMETERS_PER_HOUR(3.6),
    METERS_PER_SECOND(1),

    //
    // Imperial Units
    //

    FEET_PER_SECOND(3.28084),
    MILES_PER_HOUR(2.23694),
    KNOTS(1.94384),

    //
    // Scientific Units
    //

    LIGHT_SPEED(3.33564e-9);

    /**
     * Creates a new unit of speed.
     *
     * @param metersPerSecondConversionFactor The scale factor required to convert to {@code m/s}
     */
    SpeedUnit(double metersPerSecondConversionFactor) {
        this.metersPerSecondConversionFactor = metersPerSecondConversionFactor;
    }

    /**
     * The scale factor required to convert this unit to {@code m/s}.
     */
    private final double metersPerSecondConversionFactor;
}
