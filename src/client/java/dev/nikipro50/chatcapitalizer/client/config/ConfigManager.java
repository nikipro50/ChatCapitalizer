package dev.nikipro50.chatcapitalizer.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.nikipro50.chatcapitalizer.client.enums.CaseMode;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigManager
{
    private static final File CONFIG_FILE = new File(
            FabricLoader.getInstance().getConfigDir().toFile(),
            "chatcapitalizer.json"
    );

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static ConfigManager INSTANCE;

    public static ConfigManager get()
    {
        if (INSTANCE == null) load();
        return INSTANCE;
    }

    public static void load()
    {
        if (CONFIG_FILE.exists())
        {
            try (FileReader reader = new FileReader(CONFIG_FILE))
            {
                INSTANCE = GSON.fromJson(reader, ConfigManager.class);
            } catch (IOException e)
            {
                e.printStackTrace();
                INSTANCE = new ConfigManager();
            }
        } else
        {
            INSTANCE = new ConfigManager();
            save();
        }
    }

    public static void save()
    {
        try (FileWriter writer = new FileWriter(CONFIG_FILE))
        {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean enabled = true;
    public boolean onlyFirstWord = false;
    public List<String> capitalizeAfter = Arrays.asList(".", "!", "?");
    public List<String> excludedWords = Arrays.asList("gg", "idk");
    public CaseMode caseMode = CaseMode.NORMAL;
    public List<String> alwaysCapitalize = Arrays.asList("Minecraft");
    public String previewMessage = "hello world! gg minecraft lol";
}
