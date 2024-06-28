package dev.grafjojo.gigacraftcore.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class Coloration {

    private static final String GRADIENT_FORMAT = "<gradient:<color1>:<color2>><content></gradient>";
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * @deprecated Use {@link #gradient(String, TextColor...)} instead
     * @param hexColor1 the start color of the gradient
     * @param hexColor2 the end color of the gradient
     * @param content the content which should be colored
     * @return the colored content as a component
     */
    @Deprecated
    public static Component gradient(String hexColor1, String hexColor2, String content) {
        TagResolver contentResolver = Placeholder.component("content", Component.text(content));

        String format = "<gradient:" + hexColor1 + ":" + hexColor2 + "><content></gradient>";

        return miniMessage.deserialize(format, contentResolver);
    }

    /**
     * This method creates a gradient from the given colors
     * @param content the content which should be colored
     * @param textColors the colors of the gradient, at least two colors are required
     * @return the colored content as a component
     */
    public static Component gradient(String content, TextColor... textColors) {
        if (textColors.length < 2) {
            throw new IllegalArgumentException("At least two colors are required for a gradient");
        }
        TagResolver contentResolver = Placeholder.component("content", Component.text(content));
        StringBuilder formatBuilder = new StringBuilder("<gradient:");
        for (TextColor colors : textColors) {
            formatBuilder.append(colors).append(":");
        }
        formatBuilder.deleteCharAt(formatBuilder.length() - 1);
        formatBuilder.append("><content></gradient>");
        String format = formatBuilder.toString();
        return miniMessage.deserialize(format, contentResolver);
    }

    /**
     * @param format Custom format for the gradient @see {@link #GRADIENT_FORMAT}
     * @param content the content which should be colored
     * @return the colored content as a component
     */
    public static Component gradient(String format, Component content) {
        return miniMessage.deserialize(format, Placeholder.component("content", content));
    }

    public static Component translate(String content) {
        return miniMessage.deserialize(content);
    }
}