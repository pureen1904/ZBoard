package pureen.zboard;


import org.betonquest.betonquest.BetonQuest;
import org.betonquest.betonquest.api.logger.BetonQuestLogger;
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory;
import org.betonquest.betonquest.quest.PrimaryServerThreadData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pureen.zboard.betonquest.ZBoardEventFactory;
import pureen.zboard.commands.ZBoardCommand;
import pureen.zboard.commands.testCommand;
import pureen.zboard.framework.ZScoreBoard;
import pureen.zboard.listeners.PlayerJoinListener;
import pureen.zboard.managers.ConfigManager;
import pureen.zboard.managers.ZScoreBoardManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static pureen.zboard.Utils.log;

public final class ZBoard extends JavaPlugin implements Listener {

    private static Logger logger;
    private ConfigManager configManager;
    private ZScoreBoardManager zScoreBoardManager;
    private BetonQuestLoggerFactory loggerFactory;

    @Override
    public void onEnable() {
        logger = getLogger();

        configManager = new ConfigManager(this);
        zScoreBoardManager = new ZScoreBoardManager(this);

        configManager.loadConfigs();
        zScoreBoardManager.loadZScoreBoard();

        new testCommand(this);
        new ZBoardCommand(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(this), this);



        if (Bukkit.getPluginManager().getPlugin("BetonQuest") != null)
            try {
                loggerFactory = Bukkit.getServicesManager().load(BetonQuestLoggerFactory.class);
                new ZBoardEventFactory(loggerFactory.create(ZBoardEventFactory.class), Bukkit.getServicesManager(), new PrimaryServerThreadData(this.getServer(), Bukkit.getScheduler(), this), zScoreBoardManager, configManager);
                BetonQuest.getInstance().getQuestRegistries().getEventTypes().register("zboard", new ZBoardEventFactory(Bukkit.getServicesManager().load(BetonQuestLoggerFactory.class).create(BetonQuestLogger.class),this.getServer()
                        .getServicesManager(), new PrimaryServerThreadData(this.getServer(), Bukkit.getScheduler(), this), zScoreBoardManager, configManager));
                log("Plugin hooked: BetonQuest");
            } catch (Exception e) {
                log("Unable to hook into BetonQuest! Disabling features");
                e.printStackTrace();
            }


        log("ZBoard is active!");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                zScoreBoardManager.getZScoreBoard(player.getUniqueId()).createBoard(player);
            }
        }, 0, 20);

    }

    @Override
    public void onDisable() {
        zScoreBoardManager.saveZScoreBoard();
        configManager.saveConfigs();

        log("ZBoard is disabled!");
    }


    public static Logger getPluginLogger() {
        return logger;
    }


    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ZScoreBoardManager getzScoreBoardManager() {
        return zScoreBoardManager;
    }
}
