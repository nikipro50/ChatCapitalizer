package dev.nikipro50.chatcapitalizer.client.config.screen;

import dev.nikipro50.chatcapitalizer.client.config.ConfigManager;
import dev.nikipro50.chatcapitalizer.client.enums.CaseMode;
import dev.nikipro50.chatcapitalizer.client.utils.Notifier;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ConfigScreen
{
    public static Screen create(Screen parent)
    {
        MinecraftClient client = MinecraftClient.getInstance();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Chat Capitalizer - Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder
                .startBooleanToggle(Text.of("Enabled"), ConfigManager.get().enabled)
                .setDefaultValue(true)
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().enabled = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    if (value)
                    {
                        notifier.updateConfigToast(
                                Text.literal("You have ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                        .append(Text.literal("enabled").setStyle(
                                                Style.EMPTY.withColor(Formatting.GREEN).withFormatting(Formatting.UNDERLINE))
                                        )
                                        .append(Text.literal(" the mod!").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                        )
                        );

                        notifier.updateConfigMessage(Text.literal("You have ").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY)
                                        )
                                        .append(Text.literal("enabled").setStyle(
                                                Style.EMPTY.withColor(Formatting.GREEN).withFormatting(Formatting.UNDERLINE)
                                        ))
                                        .append(Text.literal(" the mod!").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                        )
                        );

                        return;
                    }

                    notifier.updateConfigToast(
                            Text.literal("You have ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("disabled").setStyle(
                                            Style.EMPTY.withColor(Formatting.RED).withFormatting(Formatting.UNDERLINE))
                                    )
                                    .append(Text.literal(" the mod.").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(Text.literal("You have ").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY)
                                    )
                                    .append(Text.literal("disabled").setStyle(
                                            Style.EMPTY.withColor(Formatting.RED).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" the mod.").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startBooleanToggle(Text.of("Capitalize Only First Word"), ConfigManager.get().onlyFirstWord)
                .setDefaultValue(false)
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().onlyFirstWord = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    if (value)
                    {
                        notifier.updateConfigToast(
                                Text.literal("You have ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                        .append(Text.literal("enabled").setStyle(
                                                Style.EMPTY.withColor(Formatting.GREEN).withFormatting(Formatting.UNDERLINE))
                                        )
                                        .append(Text.literal(" only first word capitalization!").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                        )
                        );

                        notifier.updateConfigMessage(Text.literal("You have ").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY)
                                        )
                                        .append(Text.literal("enabled").setStyle(
                                                Style.EMPTY.withColor(Formatting.GREEN).withFormatting(Formatting.UNDERLINE)
                                        ))
                                        .append(Text.literal(" only first word capitalization!").setStyle(
                                                Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                        )
                        );
                        return;
                    }

                    notifier.updateConfigToast(
                            Text.literal("You have ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("disabled").setStyle(
                                            Style.EMPTY.withColor(Formatting.RED).withFormatting(Formatting.UNDERLINE))
                                    )
                                    .append(Text.literal(" only first word capitalization!").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(Text.literal("You have ").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY)
                                    )
                                    .append(Text.literal("disabled").setStyle(
                                            Style.EMPTY.withColor(Formatting.RED).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" only first word capitalization!").setStyle(
                                            Style.EMPTY.withFormatting(Formatting.RESET).withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startStrList(Text.of("Capitalize After Symbol"), ConfigManager.get().capitalizeAfter)
                .setDefaultValue(List.of(".", "!", "?"))
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().capitalizeAfter = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    notifier.updateConfigToast(
                            Text.literal("Updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Capitalize After Symbol").setStyle(
                                            Style.EMPTY.withColor(Formatting.AQUA).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(
                            Text.literal("You have updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Capitalize After Symbol").setStyle(
                                            Style.EMPTY.withColor(Formatting.AQUA).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startEnumSelector(Text.of("Case Mode"), CaseMode.class, ConfigManager.get().caseMode)
                .setDefaultValue(CaseMode.NORMAL)
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().caseMode = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    notifier.updateConfigToast(
                            Text.literal("Updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Case Mode").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal("!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(
                            Text.literal("You have updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Case Mode").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal("!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startStrList(Text.of("Always Capitalize"), ConfigManager.get().alwaysCapitalize)
                .setDefaultValue(List.of("Minecraft"))
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().alwaysCapitalize = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    notifier.updateConfigToast(
                            Text.literal("Updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Always Capitalize Words").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(
                            Text.literal("You have updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Always Capitalize Words").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startStrList(Text.of("Excluded Words"), ConfigManager.get().excludedWords)
                .setDefaultValue(List.of("gg", "idk"))
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().excludedWords = value;
                    ConfigManager.save();

                    Notifier notifier = new Notifier(client);
                    notifier.updateConfigToast(
                            Text.literal("Updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Excluded Words").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );

                    notifier.updateConfigMessage(
                            Text.literal("You have updated ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                                    .append(Text.literal("Excluded Words").setStyle(
                                            Style.EMPTY.withColor(Formatting.YELLOW).withFormatting(Formatting.UNDERLINE)
                                    ))
                                    .append(Text.literal(" list!").setStyle(
                                            Style.EMPTY.withColor(Formatting.GRAY))
                                    )
                    );
                })
                .build()
        );

        general.addEntry(entryBuilder
                .startStrField(Text.of("Preview Message"), ConfigManager.get().previewMessage)
                .setDefaultValue("hello world! gg minecraft lol")
                .setSaveConsumer(value ->
                {
                    ConfigManager.get().previewMessage = value;
                    ConfigManager.save();
                })
                .setTooltip(Text.of("Type a message to preview capitalization with current settings!"))
                .build()
        );


        return builder.build();
    }
}
