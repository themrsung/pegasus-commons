package pegasus.text;

import java.util.EnumSet;
import java.util.Set;

public class TextFormat {
    public static final TextFormat NORMAL_ONLY = new TextFormat();
    public static final TextFormat BOLD_ONLY = new TextFormat(TextFormatAttribute.BOLD);
    public static final TextFormat ITALIC_ONLY = new TextFormat(TextFormatAttribute.ITALIC);
    public static final TextFormat STRIKE_ONLY = new TextFormat(TextFormatAttribute.STRIKE);
    public static final TextFormat UNDERLINED_ONLY = new TextFormat(TextFormatAttribute.UNDERLINED);
    public static final TextFormat QUOTED_ONLY = new TextFormat(TextFormatAttribute.QUOTED);
    public static final TextFormat CODE_ONLY = new TextFormat(TextFormatAttribute.CODE);

    public TextFormat(TextFormatAttribute... attributes) {
        this.attributes = EnumSet.of(TextFormatAttribute.NORMAL, attributes);
    }

    protected final EnumSet<TextFormatAttribute> attributes;

    public boolean hasAttribute(TextFormatAttribute attribute) {
        return attributes.contains(attribute);
    }

    public boolean hasAttributes(Set<TextFormatAttribute> attributes) {
        return this.attributes.containsAll(attributes);
    }

}
