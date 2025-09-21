package dev.nikipro50.chatcapitalizer.client.events;

import dev.nikipro50.chatcapitalizer.client.request.UpdateChecker;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class ClientJoinServerEvent
{
    public static void register()
    {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
        {
            if (UpdateChecker.isLatestVersion()) return;

        });
    }
}
