package io.github._4drian3d.velocityhexlogger;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecrell.terminalconsole.TerminalConsoleAppender;

import java.util.function.Function;
import java.util.regex.Pattern;

final class LoggerProvider {
    private static final Pattern RGB_PATTERN = Pattern.compile(
            LegacyComponentSerializer.SECTION_CHAR + "#([0-9a-fA-F]){6}");
    private static final String RGB_ANSI = "\u001B[38;2;%d;%d;%dm";
    private static final String RESET = LegacyComponentSerializer.SECTION_CHAR + "r";
    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .character(LegacyComponentSerializer.SECTION_CHAR)
            .hexCharacter('#')
            .build();
    private static final Function<Component, String> converter = TerminalConsoleAppender.isAnsiSupported()
            ? LoggerProvider::convertHexColors
            : LoggerProvider::stripHexColors;

    static String convert(final Component component) {
        return converter.apply(component);
    }

    private static String convertHexColors(final Component input) {
        return RGB_PATTERN.matcher(SERIALIZER.serialize(input)).replaceAll(result -> {
            final int hex = Integer.decode(result.group().substring(1));
            final int red = hex >> 16 & 0xFF;
            final int green = hex >> 8 & 0xFF;
            final int blue = hex & 0xFF;
            return String.format(RGB_ANSI, red, green, blue);
        }) + RESET;
    }

    private static String stripHexColors(final Component input) {
        return RGB_PATTERN.matcher(SERIALIZER.serialize(input)).replaceAll("");
    }
}
