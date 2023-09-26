package pegasus;

import pegasus.container.DoubleContainer;
import pegasus.graphics.Viewport;
import pegasus.pointer.DoublePointer;
import pegasus.scheduler.AtomicScheduler;
import pegasus.scheduler.Scheduler;
import pegasus.tensor.Tensors;
import pegasus.tensor.Vector2;

import javax.swing.*;

public class CommonsTesting {
    static final JFrame frame;
    static final JPanel panel;
    static final Viewport viewport;

    static {
        frame = new JFrame();
        panel = new JPanel();
        viewport = new Viewport();

        frame.add(panel);
        panel.add(viewport);

        frame.setSize(1920, 1080);
    }

    static Scheduler scheduler = new AtomicScheduler();

    public static void main(String[] args) {
        scheduler.registerRepeatingTask((t, i) -> frame.repaint(), 100);



        frame.setVisible(true);
        scheduler.start();
    }
}
