package pegasus.graphics;

import pegasus.tensor.Vector;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * A triangle with a mutable color component.
 * @param <V> The vector of which used to represent this triangle's vertices
 * @see Triangle
 */
public interface ColoredTriangle<V extends Vector<V>> extends Triangle<V> {
    /**
     * Returns the color of this triangle.
     * @return The color of this triangle
     */
    Color getColor();

    /**
     * Applies the provided update function to the color of this triangle, then sets the
     * color of this triangle to the return value of the provided update function.
     * @param operator The update function of which to apply to this triangle's color
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateColor(UnaryOperator<Color> operator);

    /**
     * Sets the color of this triangle.
     * @param color The color of this triangle
     */
    void setColor(Color color);
}
