package dev.nikipro50.chatcapitalizer.client;

import dev.nikipro50.chatcapitalizer.Chatcapitalizer;
import dev.nikipro50.chatcapitalizer.client.config.key.ConfigKeyBind;
import dev.nikipro50.chatcapitalizer.client.events.ClientJoinServerEvent;
import dev.nikipro50.chatcapitalizer.client.events.SendMessageEvent;
import dev.nikipro50.chatcapitalizer.client.request.UpdateChecker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;

public class ChatcapitalizerClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        Chatcapitalizer.logger().log(Level.INFO, "Client - Initializing...");

        KeyBinding openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open Config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "ChatCapitalizer"
        ));

        if (!UpdateChecker.isLatestVersion())
            Chatcapitalizer.logger().log(Level.WARN, "Update the mod for all function updated! You are in " + UpdateChecker.getCurrentVersion() + " latest is " + UpdateChecker.getLatestTag() + "!");

        ConfigKeyBind.register(openConfigKey);
        SendMessageEvent.register();
        ClientJoinServerEvent.register();

        Chatcapitalizer.logger().log(Level.INFO, "Client - Initialized.");
    }
}
