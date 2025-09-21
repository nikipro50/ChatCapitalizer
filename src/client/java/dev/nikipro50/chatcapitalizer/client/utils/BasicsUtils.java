package dev.nikipro50.chatcapitalizer.client.utils;

import dev.nikipro50.chatcapitalizer.client.config.ConfigManager;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class BasicsUtils
{
    public static Optional<ModContainer> modContainer(String modId)
    {
        return FabricLoader.getInstance().getModContainer(modId);
    }

    @NotNull
    public static String formatMessage(@NotNull String message, List<String> players)
    {
        for (String excluded : ConfigManager.get().excludedWords)
        {
            if (message.equalsIgnoreCase(excluded))
                return message;
        }

        return switch (ConfigManager.get().caseMode)
        {
            case ALL_CAPS -> message.toUpperCase();
            case SENTENCE -> toSentenceCase(message, players);
            case TITLE -> toTitleCase(message, players);
            case NORMAL -> defaultCapitalize(message, players);
        };
    }

    @NotNull
    private static String toSentenceCase(@NotNull String message, List<String> players)
    {
        String lower = message.toLowerCase();
        return defaultCapitalize(lower, players);
    }

    @NotNull
    private static String toTitleCase(@NotNull String message, List<String> players)
    {
        String[] words = message.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            if (ConfigManager.get().excludedWords.contains(word.toLowerCase()))
            {
                result.append(word);
            } else
            {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            }
            if (i < words.length - 1) result.append(" ");
        }

        return applyAlwaysCapitalize(result.toString());
    }

    private static @NotNull String defaultCapitalize(@NotNull String message, List<String> players)
    {
        if (!ConfigManager.get().enabled) return message;

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        int i = 0;

        if (ConfigManager.get().onlyFirstWord)
        {
            if (message.isEmpty() || !Character.isLetter(message.charAt(0))) return message;

            return Character.toUpperCase(message.charAt(0)) + message.substring(1);
        }

        while (i < message.length())
        {
            char c = message.charAt(i);

            String matchedPlayer = null;
            for (String name : players)
            {
                if (message.regionMatches(i, name, 0, name.length()))
                {
                    matchedPlayer = name;
                    break;
                }
            }

            if (matchedPlayer != null)
            {
                result.append(matchedPlayer);
                i += matchedPlayer.length();
                capitalizeNext = false;
                continue;
            }

            boolean isExcluded = false;
            for (String word : ConfigManager.get().excludedWords)
            {
                if (message.regionMatches(true, i, word, 0, word.length()))
                {
                    result.append(message, i, i + word.length());
                    i += word.length();
                    capitalizeNext = false;
                    isExcluded = true;
                    break;
                }
            }
            if (isExcluded) continue;

            if (capitalizeNext && Character.isLetter(c))
            {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else result.append(c);

            for (String symbol : ConfigManager.get().capitalizeAfter)
            {
                if (message.startsWith(symbol, i))
                {
                    capitalizeNext = true;
                    break;
                }
            }

            i++;
        }

        return applyAlwaysCapitalize(result.toString());
    }

    private static String applyAlwaysCapitalize(String text)
    {
        for (String word : ConfigManager.get().alwaysCapitalize)
        {
            text = text.replaceAll("(?i)\\b" + Pattern.quote(word) + "\\b", word);
        }

        return text;
    }
}
