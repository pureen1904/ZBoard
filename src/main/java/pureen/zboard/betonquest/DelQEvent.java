package pureen.zboard.betonquest;

import org.betonquest.betonquest.api.profiles.Profile;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.exceptions.QuestRuntimeException;
import org.bukkit.OfflinePlayer;
import pureen.zboard.managers.ConfigManager;
import pureen.zboard.managers.ZScoreBoardManager;

public class DelQEvent implements Event {

    private final ZScoreBoardManager zScoreBoardManager;
    private final ConfigManager configManager;
    String type;
    String name;


    public DelQEvent(String type, String name, ZScoreBoardManager zScoreBoardManager, ConfigManager configManager) {
        this.type = type;
        this.name = name;
        this.configManager = configManager;
        this.zScoreBoardManager = zScoreBoardManager;
    }


    public void execute(Profile profile) throws QuestRuntimeException {
        OfflinePlayer player = profile.getPlayer();
        switch (type) {
            case "sideq" -> zScoreBoardManager.DelLineData(player.getUniqueId(), "sidequests", name);
            case "dailyq" -> zScoreBoardManager.DelLineData(player.getUniqueId(), "dailyquests", name);
            case "eventq" -> zScoreBoardManager.DelLineData(player.getUniqueId(), "eventquests", name);
        }
        configManager.saveConfigs();
    }

}
