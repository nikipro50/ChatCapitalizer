package dev.nikipro50.chatcapitalizer;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chatcapitalizer implements ModInitializer
{
    private static Logger logger;

    public static Logger logger()
    {
        return logger;
    }

    @Override
    public void onInitialize()
    {
        logger = LogManager.getLogger("ChatCapitalizer");
        logger.log(Level.INFO, "Logger loaded!");
    }
}
