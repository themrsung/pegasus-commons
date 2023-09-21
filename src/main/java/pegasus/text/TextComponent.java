package pegasus.text;

import java.awt.*;

public interface TextComponent {
    String content();

    int fontSize();

    TextFormat format();

    Color color();
}
