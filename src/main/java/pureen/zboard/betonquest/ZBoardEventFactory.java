package pureen.zboard.betonquest;

import org.betonquest.betonquest.BetonQuest;
import org.betonquest.betonquest.Instruction;
import org.betonquest.betonquest.api.logger.BetonQuestLogger;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.api.quest.event.EventFactory;
import org.betonquest.betonquest.compatibility.fakeblock.HideGroupEvent;
import org.betonquest.betonquest.compatibility.fakeblock.ShowGroupEvent;
import org.betonquest.betonquest.exceptions.InstructionParseException;
import org.betonquest.betonquest.quest.PrimaryServerThreadData;
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent;
import org.bukkit.plugin.ServicesManager;
import org.jetbrains.annotations.Nullable;
import pureen.zboard.configs.ScoreboardConfig;
import pureen.zboard.managers.ConfigManager;
import pureen.zboard.managers.ZScoreBoardManager;

import java.util.Arrays;
import java.util.Locale;

public class ZBoardEventFactory implements EventFactory {


    private final PrimaryServerThreadData data;
    private final BetonQuestLogger log;
    private ZScoreBoardManager zScoreBoardManager;
    private ConfigManager configManager;

    public ZBoardEventFactory(final BetonQuestLogger log, ServicesManager servicesManager , PrimaryServerThreadData data, ZScoreBoardManager zScoreBoardManager, ConfigManager configManager) {
        this.data = data;
        this.log = log;
        this.zScoreBoardManager = zScoreBoardManager;
        this.configManager = configManager;
    }


    public Event parseEvent(Instruction instruction) throws InstructionParseException {
        return (Event)new PrimaryServerThreadEvent(getZBoardEvent(instruction), this.data);
    }
    // Usage zboard <ins1> <ins2> <String>
    public Event getZBoardEvent(Instruction instruction) throws InstructionParseException {
        String action = instruction.getPart(1);
        String name = instruction.getPart(2);
        String a = Arrays.toString(instruction.getRemainingParts());
        return switch (action.toLowerCase(Locale.ROOT)) {
            case "addsideq" -> new AddQEvent("sideq", name, a.trim(), zScoreBoardManager, configManager);
            case "adddailyq" -> new AddQEvent("dailyq", name, a.trim(), zScoreBoardManager, configManager);
            case "addeventq" -> new AddQEvent("eventq", name, a.trim(), zScoreBoardManager, configManager);
            default ->
                    throw new InstructionParseException("Unknown action (valid options are: showgroup, hidegroup): " + action);
        };
    }


}
