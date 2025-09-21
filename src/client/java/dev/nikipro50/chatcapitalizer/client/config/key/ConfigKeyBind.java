package dev.nikipro50.chatcapitalizer.client.config.key;

import dev.nikipro50.chatcapitalizer.client.config.screen.ConfigScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;

public class ConfigKeyBind
{
    public static void register(KeyBinding openConfigKey)
    {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            while (openConfigKey.wasPressed())
            {
                if (client.currentScreen == null)
                {
                    Screen screen = ConfigScreen.create(client.currentScreen);
                    client.setScreen(screen);
                }
            }
        });
    }
}
