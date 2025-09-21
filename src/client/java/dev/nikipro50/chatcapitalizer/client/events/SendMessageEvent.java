package dev.nikipro50.chatcapitalizer.client.events;

import dev.nikipro50.chatcapitalizer.client.server.ServerGetter;
import dev.nikipro50.chatcapitalizer.client.utils.BasicsUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;

public class SendMessageEvent
{
    public static void register()
    {
        ClientSendMessageEvents.MODIFY_CHAT.register(message ->
        {
            if (message.isEmpty()) return message;
            if (!Character.isLetter(message.charAt(0))) return message;

            MinecraftClient client = MinecraftClient.getInstance();
            ServerGetter server = new ServerGetter(client);

            return BasicsUtils.formatMessage(message, server.players());
        });
    }
}
