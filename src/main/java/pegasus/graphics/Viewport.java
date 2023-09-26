package pegasus.graphics;

import pegasus.tensor.Quaternion;
import pegasus.tensor.Vector3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Viewport extends JComponent {
    public Viewport() {
    }

    public Vector3 viewportLocation;
    public Quaternion viewportRotation;
    public double focalLength = 500;
    public Color backgroundColor = Colors.LIGHT_BLUE;
    public List<ColoredTriangle3D> triangles = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (ColoredTriangle3D triangle : List.copyOf(triangles)) {
            g.setColor(triangle.color);
            PRenderer.fillTriangle(g, triangle, focalLength);
        }
    }
}
