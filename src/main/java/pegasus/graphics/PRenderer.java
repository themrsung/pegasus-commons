package pegasus.graphics;

import pegasus.exception.IllegalInstanceException;
import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;

import java.awt.*;

/**
 * Contains graphics rendering utilities.
 */
public final class PRenderer {
    //
    // Triangles
    //

    /**
     * Inverts the Y coordinates of the provided triangle {@code t}.
     *
     * @param t The triangle of which to invert the Y coordinates of
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public static void invertY(Triangle2D t) {
        t.a = Tensors.invertY(t.a);
        t.b = Tensors.invertY(t.b);
        t.c = Tensors.invertY(t.c);
    }

    /**
     * Inverts the Y coordinates of the provided triangle {@code t}.
     *
     * @param t The triangle of which to invert the Y coordinates of
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public static void invertY(Triangle3D t) {
        t.a = Tensors.invertY(t.a);
        t.b = Tensors.invertY(t.b);
        t.c = Tensors.invertY(t.c);
    }

    /**
     * Draws the provided triangle {@code t} to the provided graphics object {@code g}.
     *
     * @param g The graphics object of which to render to
     * @param t The triangle of which to render
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static void drawTriangle(Graphics g, Triangle2D t) {
        g.drawPolygon(asPolygon(t));
    }

    /**
     * Fills the provided triangle {@code t} to the provided graphics object {@code g}.
     *
     * @param g The graphics object of which to render to
     * @param t The triangle of which to render
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static void fillTriangle(Graphics g, Triangle2D t) {
        g.fillPolygon(asPolygon(t));
    }

    /**
     * Draws the provided triangle {@code t} to the provided graphics object {@code g}.
     *
     * @param g           The graphics object of which to render to
     * @param t           The triangle of which to render
     * @param focalLength The focal length of which to use
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static void drawTriangle(Graphics g, Triangle3D t, double focalLength) {
        g.drawPolygon(asPolygon(t, focalLength));
    }

    /**
     * Fills the provided triangle {@code t} to the provided graphics object {@code g}.
     *
     * @param g           The graphics object of which to render to
     * @param t           The triangle of which to render
     * @param focalLength The focal length of which to use
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public static void fillTriangle(Graphics g, Triangle3D t, double focalLength) {
        g.fillPolygon(asPolygon(t, focalLength));
    }

    /**
     * Returns the provided triangle {@code t} as a {@link Polygon polygon}.
     *
     * @param t The triangle of which to convert into a polygon
     * @return The converted polygon
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public static Polygon asPolygon(Triangle2D t) {
        Polygon polygon = new Polygon();
        t.forEachVertex(v -> polygon.addPoint((int) v.x(), (int) v.y()));
        return polygon;
    }

    /**
     * Returns the provided triangle {@code t} as a {@link Polygon polygon}.
     *
     * @param t           The triangle of which to convert into a polygon
     * @param focalLength The focal length of which to use
     * @return The converted polygon
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    public static Polygon asPolygon(Triangle3D t, double focalLength) {
        Polygon polygon = new Polygon();
        t.forEachVertex(v -> {
            Vector2 v2 = Tensors.to2D(v, focalLength);
            polygon.addPoint((int) v2.x(), (int) v2.y());
        });
        return polygon;
    }

    //
    // Miscellaneous
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private PRenderer() {
        throw new IllegalInstanceException(this);
    }
}
