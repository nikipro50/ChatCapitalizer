package dev.nikipro50.chatcapitalizer.client.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dev.nikipro50.chatcapitalizer.client.utils.BasicsUtils;
import net.fabricmc.loader.api.ModContainer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;

public class UpdateChecker
{
    private static final String REPO = "nikipo50/ChatCapitalizer";
    private static final String API_URL = "https://api.github.com/repos/" + REPO + "/tags";

    public static String getLatestTag()
    {
        try
        {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            JsonArray array = JsonParser.parseReader(new InputStreamReader(connection.getInputStream())).getAsJsonArray();

            if (!array.isEmpty())
            {
                JsonElement latest = array.get(0);
                return latest.getAsJsonObject().get("name").getAsString().replace("v", "");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrentVersion()
    {
        Optional<ModContainer> modContainer = BasicsUtils.modContainer("chatcapitalizer");
        if (modContainer.isEmpty()) return "Uknown";

        return modContainer.get().getMetadata().getVersion().getFriendlyString();
    }

    public static boolean isLatestVersion()
    {
        String latest = getLatestTag();
        if (latest == null) return true;

        return latest.equals(getCurrentVersion());
    }
}
