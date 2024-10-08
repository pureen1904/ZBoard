package pureen.zboard.betonquest;

import org.betonquest.betonquest.api.profiles.Profile;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.exceptions.QuestRuntimeException;
import org.bukkit.OfflinePlayer;
import pureen.zboard.managers.ConfigManager;
import pureen.zboard.managers.ZScoreBoardManager;

public class AddQEvent implements Event {

    private final ZScoreBoardManager zScoreBoardManager;
    private final ConfigManager configManager;
    String type;
    String name;
    String a;


    public AddQEvent(String type, String name, String a, ZScoreBoardManager zScoreBoardManager, ConfigManager configManager) {
        this.type = type;
        this.name = name;
        this.a = a;
        this.configManager = configManager;
        this.zScoreBoardManager = zScoreBoardManager;
    }


    public void execute(Profile profile) throws QuestRuntimeException {
        OfflinePlayer player = profile.getPlayer();
        switch (type) {
            case "sideq" -> zScoreBoardManager.reSetLineData(player.getUniqueId(), "sidequests", name, a);
            case "dailyq" -> zScoreBoardManager.reSetLineData(player.getUniqueId(), "dailyquests", name, a);
            case "eventq" -> zScoreBoardManager.reSetLineData(player.getUniqueId(), "eventquests", name, a);
        }
        configManager.saveConfigs();
    }

}
