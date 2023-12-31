package pegasus.graphics;

import pegasus.exception.IllegalInstanceException;
import pegasus.tensor.Tensors;

import java.awt.*;

/**
 * Contains utility methods and constant values related to AWT {@link Color}s.
 */
public final class Colors {
    //
    // Primary & Secondary Colors
    //

    /**
     * The color white.
     */
    public static final Color WHITE = new Color(255, 255, 255);

    /**
     * The color black.
     */
    public static final Color BLACK = new Color(0, 0, 0);

    /**
     * A color with RGBA components of zero. (a transparent shade of black)
     */
    public static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);

    /**
     * The color red.
     */
    public static final Color RED = new Color(255, 0, 0);

    /**
     * The color green.
     */
    public static final Color GREEN = new Color(0, 255, 0);

    /**
     * The color blue.
     */
    public static final Color BLUE = new Color(0, 0, 255);

    /**
     * The color yellow.
     */
    public static final Color YELLOW = new Color(255, 255, 0);

    /**
     * The color cyan.
     */
    public static final Color CYAN = new Color(0, 255, 255);

    /**
     * The color magenta.
     */
    public static final Color MAGENTA = new Color(255, 0, 255);

    /**
     * The color silver.
     */
    public static final Color SILVER = new Color(192, 192, 192);

    /**
     * The color gray.
     */
    public static final Color GRAY = new Color(128, 128, 128);

    /**
     * The color maroon.
     */
    public static final Color MAROON = new Color(128, 0, 0);

    /**
     * The color olive.
     */
    public static final Color OLIVE = new Color(128, 128, 0);

    /**
     * The color dark green.
     */
    public static final Color DARK_GREEN = new Color(0, 128, 0);

    /**
     * The color purple.
     */
    public static final Color PURPLE = new Color(128, 0, 128);

    /**
     * The color teal.
     */
    public static final Color TEAL = new Color(0, 128, 128);

    /**
     * The color navy.
     */
    public static final Color NAVY = new Color(0, 0, 128);

    //
    // Serialization
    //

    /**
     * Serializes a color into a string.
     *
     * @param c The color of which to serialize
     * @return The string representation of the provided color {@code c}
     */
    public static String toString(Color c) {
        if (c == null) return "null";

        return c.getClass().getSimpleName() + "{" +
                "r=" + c.getRed() + ", " +
                "g=" + c.getGreen() + ", " +
                "b=" + c.getBlue() + ", " +
                "a=" + c.getAlpha() +
                "}";
    }

    //
    // Randomization
    //

    /**
     * Returns a new random opaque color.
     *
     * @return A new random opaque color
     */
    public static Color random() {
        return new Color(
                (int) Tensors.random(0, 255 + Double.MIN_VALUE),
                (int) Tensors.random(0, 255 + Double.MIN_VALUE),
                (int) Tensors.random(0, 255 + Double.MIN_VALUE)
        );
    }

    //
    // Inversion
    //

    /**
     * Returns the inverse of the provided color {@code c}.
     *
     * @param c The color of which to get the inverse of
     * @return The inverse of the provided color {@code c}
     */
    public static Color inverse(Color c) {
        return new Color(
                255 - c.getRed(),
                255 - c.getGreen(),
                255 - c.getBlue(),
                c.getAlpha()
        );
    }

    //
    // Linear Interpolation (LERP)
    //

    /**
     * Performs linear interpolation (LERP) between the starting color {@code s}
     * and the ending color {@code e}, using the interpolation parameter {@code t}.
     *
     * @param s The starting color of which to start the interpolation at
     * @param e The ending color of which to end the interpolation at
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated color
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Color lerp(Color s, Color e, double t) {
        final double r1 = s.getRed();
        final double g1 = s.getGreen();
        final double b1 = s.getBlue();
        final double a1 = s.getAlpha();

        final double r2 = e.getRed();
        final double g2 = e.getGreen();
        final double b2 = e.getBlue();
        final double a2 = e.getAlpha();

        return new Color(
                (int) Tensors.lerp(r1, r2, t),
                (int) Tensors.lerp(g1, g2, t),
                (int) Tensors.lerp(b1, b2, t),
                (int) Tensors.lerp(a1, a2, t)
        );
    }

    /**
     * Performs Bezier interpolation between the starting color {@code s}
     * and the ending color {@code e}, using the interpolation parameter {@code t}.
     *
     * @param s The starting color of which to start the interpolation at
     * @param e The ending color of which to end the interpolation at
     * @param t The interpolation parameter ({@code [0, 1]})
     * @return The interpolated color
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static Color bezier(Color s, Color e, double t) {
        final double r1 = s.getRed();
        final double g1 = s.getGreen();
        final double b1 = s.getBlue();
        final double a1 = s.getAlpha();

        final double r2 = e.getRed();
        final double g2 = e.getGreen();
        final double b2 = e.getBlue();
        final double a2 = e.getAlpha();

        // Bezier control points
        final double cpR = 0.5; // Red control point
        final double cpG = 0.2; // Green control point
        final double cpB = 0.8; // Blue control point
        final double cpA = 0.7; // Alpha control point

        // Bezier formula for each color component
        final double r = Tensors.bezier(r1, cpR, cpR, r2, t);
        final double g = Tensors.bezier(g1, cpG, cpG, g2, t);
        final double b = Tensors.bezier(b1, cpB, cpB, b2, t);
        final double a = Tensors.bezier(a1, cpA, cpA, a2, t);

        return new Color((int) r, (int) g, (int) b, (int) a);
    }

    //
    // Non-Primary Colors
    //

    /**
     * The color dark red.
     */
    public static final Color DARK_RED = new Color(139, 0, 0);

    /**
     * The color brown.
     */
    public static final Color BROWN = new Color(165, 42, 42);

    /**
     * The color firebrick.
     */
    public static final Color FIREBRICK = new Color(178, 34, 34);

    /**
     * The color crimson.
     */
    public static final Color CRIMSON = new Color(220, 20, 60);

    /**
     * The color tomato.
     */
    public static final Color TOMATO = new Color(255, 99, 71);

    /**
     * The color coral.
     */
    public static final Color CORAL = new Color(255, 127, 80);

    /**
     * The color indian red.
     */
    public static final Color INDIAN_RED = new Color(205, 92, 92);

    /**
     * The color light coral.
     */
    public static final Color LIGHT_CORAL = new Color(240, 128, 128);

    /**
     * The color dark salmon.
     */
    public static final Color DARK_SALMON = new Color(233, 150, 122);

    /**
     * The color salmon.
     */
    public static final Color SALMON = new Color(250, 128, 114);

    /**
     * The color light salmon.
     */
    public static final Color LIGHT_SALMON = new Color(255, 160, 122);

    /**
     * The color orange red.
     */
    public static final Color ORANGE_RED = new Color(255, 69, 0);

    /**
     * The color dark orange.
     */
    public static final Color DARK_ORANGE = new Color(255, 140, 0);

    /**
     * The color orange.
     */
    public static final Color ORANGE = new Color(255, 165, 0);

    /**
     * The color gold.
     */
    public static final Color GOLD = new Color(255, 215, 0);

    /**
     * The color dark golden rod.
     */
    public static final Color DARK_GOLDEN_ROD = new Color(184, 134, 11);

    /**
     * The color golden rod.
     */
    public static final Color GOLDEN_ROD = new Color(218, 165, 32);

    /**
     * The color pale golden rod.
     */
    public static final Color PALE_GOLDEN_ROD = new Color(238, 232, 170);

    /**
     * The color dark khaki.
     */
    public static final Color DARK_KHAKI = new Color(189, 183, 107);

    /**
     * The color khaki.
     */
    public static final Color KHAKI = new Color(240, 230, 140);

    /**
     * The color yellow green.
     */
    public static final Color YELLOW_GREEN = new Color(154, 205, 50);

    /**
     * The color dark olive green.
     */
    public static final Color DARK_OLIVE_GREEN = new Color(85, 107, 47);

    /**
     * The color olive drab.
     */
    public static final Color OLIVE_DRAB = new Color(107, 142, 35);

    /**
     * The color lawn green.
     */
    public static final Color LAWN_GREEN = new Color(124, 252, 0);

    /**
     * The color chartreuse.
     */
    public static final Color CHARTREUSE = new Color(127, 255, 0);

    /**
     * The color green yellow.
     */
    public static final Color GREEN_YELLOW = new Color(173, 255, 47);

    /**
     * The color forest green.
     */
    public static final Color FOREST_GREEN = new Color(34, 139, 34);

    /**
     * The color lime.
     */
    public static final Color LIME = new Color(0, 255, 0);

    /**
     * The color lime green.
     */
    public static final Color LIME_GREEN = new Color(50, 205, 50);

    /**
     * The color light green.
     */
    public static final Color LIGHT_GREEN = new Color(144, 238, 144);

    /**
     * The color pale green.
     */
    public static final Color PALE_GREEN = new Color(152, 251, 152);

    /**
     * The color dark sea green.
     */
    public static final Color DARK_SEA_GREEN = new Color(143, 188, 143);

    /**
     * The color medium spring green.
     */
    public static final Color MEDIUM_SPRING_GREEN = new Color(0, 250, 154);

    /**
     * The color spring green.
     */
    public static final Color SPRING_GREEN = new Color(0, 255, 127);

    /**
     * The color sea green.
     */
    public static final Color SEA_GREEN = new Color(46, 139, 87);

    /**
     * The color medium aqua marine.
     */
    public static final Color MEDIUM_AQUA_MARINE = new Color(102, 205, 170);

    /**
     * The color medium sea green.
     */
    public static final Color MEDIUM_SEA_GREEN = new Color(60, 179, 113);

    /**
     * The color light sea green.
     */
    public static final Color LIGHT_SEA_GREEN = new Color(32, 178, 170);

    /**
     * The color dark slate gray.
     */
    public static final Color DARK_SLATE_GRAY = new Color(47, 79, 79);

    /**
     * The color dark cyan.
     */
    public static final Color DARK_CYAN = new Color(0, 139, 139);

    /**
     * The color aqua.
     */
    public static final Color AQUA = new Color(0, 255, 255);

    /**
     * The color light cyan.
     */
    public static final Color LIGHT_CYAN = new Color(224, 255, 255);

    /**
     * The color dark turquoise.
     */
    public static final Color DARK_TURQUOISE = new Color(0, 206, 209);

    /**
     * The color turquoise.
     */
    public static final Color TURQUOISE = new Color(64, 224, 208);

    /**
     * The color medium turquoise.
     */
    public static final Color MEDIUM_TURQUOISE = new Color(72, 209, 204);

    /**
     * The color pale turquoise.
     */
    public static final Color PALE_TURQUOISE = new Color(175, 238, 238);

    /**
     * The color aqua marine.
     */
    public static final Color AQUA_MARINE = new Color(127, 255, 212);

    /**
     * The color powder blue.
     */
    public static final Color POWDER_BLUE = new Color(176, 224, 230);

    /**
     * The color cadet blue.
     */
    public static final Color CADET_BLUE = new Color(95, 158, 160);

    /**
     * The color steel blue.
     */
    public static final Color STEEL_BLUE = new Color(70, 130, 180);

    /**
     * The color cornflower blue.
     */
    public static final Color CORNFLOWER_BLUE = new Color(100, 149, 237);

    /**
     * The color deep sky blue.
     */
    public static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);

    /**
     * The color dodger blue.
     */
    public static final Color DODGER_BLUE = new Color(30, 144, 255);

    /**
     * The color light blue.
     */
    public static final Color LIGHT_BLUE = new Color(173, 216, 230);

    /**
     * The color sky blue.
     */
    public static final Color SKY_BLUE = new Color(135, 206, 235);

    /**
     * The color light sky blue.
     */
    public static final Color LIGHT_SKY_BLUE = new Color(135, 206, 250);

    /**
     * The color midnight blue.
     */
    public static final Color MIDNIGHT_BLUE = new Color(25, 25, 112);

    /**
     * The color dark blue.
     */
    public static final Color DARK_BLUE = new Color(0, 0, 139);

    /**
     * The color medium blue.
     */
    public static final Color MEDIUM_BLUE = new Color(0, 0, 205);

    /**
     * The color royal blue.
     */
    public static final Color ROYAL_BLUE = new Color(65, 105, 225);

    /**
     * The color blue violet.
     */
    public static final Color BLUE_VIOLET = new Color(138, 43, 226);

    /**
     * The color indigo.
     */
    public static final Color INDIGO = new Color(75, 0, 130);

    /**
     * The color dark slate blue.
     */
    public static final Color DARK_SLATE_BLUE = new Color(72, 61, 139);

    /**
     * The color slate blue.
     */
    public static final Color SLATE_BLUE = new Color(106, 90, 205);

    /**
     * The color medium slate blue.
     */
    public static final Color MEDIUM_SLATE_BLUE = new Color(123, 104, 238);

    /**
     * The color medium purple.
     */
    public static final Color MEDIUM_PURPLE = new Color(147, 112, 219);

    /**
     * The color dark magenta.
     */
    public static final Color DARK_MAGENTA = new Color(139, 0, 139);

    /**
     * The color dark violet.
     */
    public static final Color DARK_VIOLET = new Color(148, 0, 211);

    /**
     * The color dark orchid.
     */
    public static final Color DARK_ORCHID = new Color(153, 50, 204);

    /**
     * The color medium orchid.
     */
    public static final Color MEDIUM_ORCHID = new Color(186, 85, 211);

    /**
     * The color thistle.
     */
    public static final Color THISTLE = new Color(216, 191, 216);

    /**
     * The color plum.
     */
    public static final Color PLUM = new Color(221, 160, 221);

    /**
     * The color violet.
     */
    public static final Color VIOLET = new Color(238, 130, 238);

    /**
     * The color orchid.
     */
    public static final Color ORCHID = new Color(218, 112, 214);

    /**
     * The color medium violet red.
     */
    public static final Color MEDIUM_VIOLET_RED = new Color(199, 21, 133);

    /**
     * The color pale violet red.
     */
    public static final Color PALE_VIOLET_RED = new Color(219, 112, 147);

    /**
     * The color deep pink.
     */
    public static final Color DEEP_PINK = new Color(255, 20, 147);

    /**
     * The color hot pink.
     */
    public static final Color HOT_PINK = new Color(255, 105, 180);

    /**
     * The color light pink.
     */
    public static final Color LIGHT_PINK = new Color(255, 182, 193);

    /**
     * The color pink.
     */
    public static final Color PINK = new Color(255, 192, 203);

    /**
     * The color antique white.
     */
    public static final Color ANTIQUE_WHITE = new Color(250, 235, 215);

    /**
     * The color beige.
     */
    public static final Color BEIGE = new Color(245, 245, 220);

    /**
     * The color bisque.
     */
    public static final Color BISQUE = new Color(255, 228, 196);

    /**
     * The color blanched almond.
     */
    public static final Color BLANCHED_ALMOND = new Color(255, 235, 205);

    /**
     * The color wheat.
     */
    public static final Color WHEAT = new Color(245, 222, 179);

    /**
     * The color corn silk.
     */
    public static final Color CORN_SILK = new Color(255, 248, 220);

    /**
     * The color lemon chiffon.
     */
    public static final Color LEMON_CHIFFON = new Color(255, 250, 205);

    /**
     * The color light golden rod yellow.
     */
    public static final Color LIGHT_GOLDEN_ROD_YELLOW = new Color(250, 250, 210);

    /**
     * The color light yellow.
     */
    public static final Color LIGHT_YELLOW = new Color(255, 255, 224);

    /**
     * The color saddle brown.
     */
    public static final Color SADDLE_BROWN = new Color(139, 69, 19);

    /**
     * The color sienna.
     */
    public static final Color SIENNA = new Color(160, 82, 45);

    /**
     * The color chocolate.
     */
    public static final Color CHOCOLATE = new Color(210, 105, 30);

    /**
     * The color peru.
     */
    public static final Color PERU = new Color(205, 133, 63);

    /**
     * The color sandy brown.
     */
    public static final Color SANDY_BROWN = new Color(244, 164, 96);

    /**
     * The color burly wood.
     */
    public static final Color BURLY_WOOD = new Color(222, 184, 135);

    /**
     * The color tan.
     */
    public static final Color TAN = new Color(210, 180, 140);

    /**
     * The color rosy brown.
     */
    public static final Color ROSY_BROWN = new Color(188, 143, 143);

    /**
     * The color moccasin.
     */
    public static final Color MOCCASIN = new Color(255, 228, 181);

    /**
     * The color navajo white.
     */
    public static final Color NAVAJO_WHITE = new Color(255, 222, 173);

    /**
     * The color peach puff.
     */
    public static final Color PEACH_PUFF = new Color(255, 218, 185);

    /**
     * The color misty rose.
     */
    public static final Color MISTY_ROSE = new Color(255, 228, 225);

    /**
     * The color lavender blush.
     */
    public static final Color LAVENDER_BLUSH = new Color(255, 240, 245);

    /**
     * The color linen.
     */
    public static final Color LINEN = new Color(250, 240, 230);

    /**
     * The color old lace.
     */
    public static final Color OLD_LACE = new Color(253, 245, 230);

    /**
     * The color papaya whip.
     */
    public static final Color PAPAYA_WHIP = new Color(255, 239, 213);

    /**
     * The color seashell.
     */
    public static final Color SEA_SHELL = new Color(255, 245, 238);

    /**
     * The color mint cream.
     */
    public static final Color MINT_CREAM = new Color(245, 255, 250);

    /**
     * The color slate gray.
     */
    public static final Color SLATE_GRAY = new Color(112, 128, 144);

    /**
     * The color light slate gray.
     */
    public static final Color LIGHT_SLATE_GRAY = new Color(119, 136, 153);

    /**
     * The color light steel blue.
     */
    public static final Color LIGHT_STEEL_BLUE = new Color(176, 196, 222);

    /**
     * The color lavender.
     */
    public static final Color LAVENDER = new Color(230, 230, 250);

    /**
     * The color floral white.
     */
    public static final Color FLORAL_WHITE = new Color(255, 250, 240);

    /**
     * The color alice blue.
     */
    public static final Color ALICE_BLUE = new Color(240, 248, 255);

    /**
     * The color ghost white.
     */
    public static final Color GHOST_WHITE = new Color(248, 248, 255);

    /**
     * The color honeydew.
     */
    public static final Color HONEYDEW = new Color(240, 255, 240);

    /**
     * The color ivory.
     */
    public static final Color IVORY = new Color(255, 255, 240);

    /**
     * The color azure.
     */
    public static final Color AZURE = new Color(240, 255, 255);

    /**
     * The color snow.
     */
    public static final Color SNOW = new Color(255, 250, 250);

    /**
     * The color dim gray.
     */
    public static final Color DIM_GRAY = new Color(105, 105, 105);

    /**
     * The color dark gray.
     */
    public static final Color DARK_GRAY = new Color(169, 169, 169);

    /**
     * The color light gray.
     */
    public static final Color LIGHT_GRAY = new Color(211, 211, 211);

    /**
     * The color gainsboro.
     */
    public static final Color GAINSBORO = new Color(220, 220, 220);

    /**
     * The color white smoke.
     */
    public static final Color WHITE_SMOKE = new Color(245, 245, 245);

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private Colors() {
        throw new IllegalInstanceException(this);
    }
}
