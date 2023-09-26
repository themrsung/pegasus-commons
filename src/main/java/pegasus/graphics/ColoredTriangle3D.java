package pegasus.graphics;

import pegasus.tensor.Vector3;

import java.awt.*;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A three-dimensional colored triangle.
 * @see Triangle3D
 */
public class ColoredTriangle3D extends Triangle3D implements ColoredTriangle<Vector3> {
    public ColoredTriangle3D(Vector3 a, Vector3 b, Vector3 c, Color color) {
        super(a, b, c);
        this.color = Objects.requireNonNull(color);
    }

    public ColoredTriangle3D(ColoredTriangle3D t) {
        super(t);
        this.color = t.color;
    }

    protected Color color;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void updateColor(UnaryOperator<Color> operator) {
        color = Objects.requireNonNull(operator.apply(color));
    }

    @Override
    public void setColor(Color color) {
        this.color = Objects.requireNonNull(color);
    }
}
