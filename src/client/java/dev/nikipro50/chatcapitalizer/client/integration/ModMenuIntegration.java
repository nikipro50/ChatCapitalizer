package dev.nikipro50.chatcapitalizer.client.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.nikipro50.chatcapitalizer.client.config.screen.ConfigScreen;

public class ModMenuIntegration implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return ConfigScreen::create;
    }
}
