package dev.nikipro50.chatcapitalizer.client.server;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;

import java.util.List;
import java.util.stream.Collectors;

public class ServerGetter
{
    private final MinecraftClient client;

    public ServerGetter(MinecraftClient client)
    {
        this.client = client;
    }

    public List<String> players()
    {
        if(this.client.player == null || this.client.player.networkHandler == null) return List.of();

        return this.client.getNetworkHandler().getPlayerList().stream()
                .map(PlayerListEntry::getProfile)
                .map(profile -> profile.getName())
                .collect(Collectors.toList());
    }
}
