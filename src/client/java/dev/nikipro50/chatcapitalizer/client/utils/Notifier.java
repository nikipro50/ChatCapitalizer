package dev.nikipro50.chatcapitalizer.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Notifier
{
    private final MinecraftClient client;

    public Notifier(MinecraftClient client)
    {
        this.client = client;
    }

    public void updateConfigToast(Text message)
    {
        if (this.client == null) return;

        this.client.getToastManager().add(
                SystemToast.create(
                        this.client,
                        SystemToast.Type.PERIODIC_NOTIFICATION,
                        Text.literal("ChatCapitalizer - Config Update").setStyle(Style.EMPTY.withColor(Formatting.GOLD)),
                        message
                )
        );
    }

    public void updateConfigMessage(Text message)
    {
        if (this.client == null || this.client.player == null) return;

        this.client.player.sendMessage(Text.literal("[ChatCapitalizer] ").setStyle(Style.EMPTY.withColor(Formatting.GOLD)).append(message));
    }

    public void notifyUpdate()
    {
        if (this.client == null) return;

        this.client.getToastManager().add(
                SystemToast.create(
                        this.client,
                        SystemToast.Type.PERIODIC_NOTIFICATION,
                        Text.literal("ChatCapitalizer - Updater").setStyle(Style.EMPTY.withColor(Formatting.RED)),
                        Text.literal("Update this mod for new functions!").setStyle(Style.EMPTY.withColor(Formatting.WHITE))
                )
        );
    }

}
