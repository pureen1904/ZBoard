package pureen.zboard.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pureen.zboard.ZBoard;
import pureen.zboard.configs.ScoreboardConfig;
import pureen.zboard.framework.ZScoreBoard;

import java.util.*;


public class ZScoreBoardManager {

    private ZBoard zBoard;
    private Map<UUID, ZScoreBoard> arrays = new HashMap<>();
    private ScoreboardConfig scoreboardConfig;
    private static final String EMPTY_STRING = " ";

    public ZScoreBoardManager(ZBoard zBoard) {
        this.zBoard = zBoard;
        scoreboardConfig = zBoard.getConfigManager().getScoreboardConfig();
    }


    public void loadZScoreBoard() {
        for (String key1 : scoreboardConfig.getSection("")) {
            UUID id = UUID.fromString(key1);
            arrays.put(id, new ZScoreBoard(scoreboardConfig.getHashFromConfig(Bukkit.getOfflinePlayer(id), "sidequests"),
                    scoreboardConfig.getHashFromConfig(Bukkit.getOfflinePlayer(id), "dailyquests"),
                    scoreboardConfig.getHashFromConfig(Bukkit.getOfflinePlayer(id), "eventquests")));
        }
    }

    public void saveZScoreBoard() {
        for (UUID uuid : arrays.keySet()) {
            ZScoreBoard zScoreBoard = arrays.get(uuid);
            scoreboardConfig.setAllDatas(uuid, zScoreBoard.getSideQ(), zScoreBoard.getDailyQ(), zScoreBoard.getEventQ());
        }
    }

    public ZScoreBoard createZScoreBoard(Player player) {
        ZScoreBoard zScoreBoard = new ZScoreBoard(new HashMap<>(), new HashMap<>(), new HashMap<>());
        arrays.put(player.getUniqueId(), zScoreBoard);
        return zScoreBoard;
    }


    public void reSetLineData(UUID id, String type, String name, String string) {
        scoreboardConfig.set(id + ".scoreboardvalues." + type + "." + name, string);
        switch (type) {
            case "sidequests":
                arrays.get(id).getSideQ().put(name, string);
                break;
            case "dailyquests":
                arrays.get(id).getDailyQ().put(name, string);
                break;
            case "eventquests":
                arrays.get(id).getEventQ().put(name, string);
                break;
        }
    }

    public void DelLineData(UUID id, String type, String name) {
        scoreboardConfig.set(id + ".scoreboardvalues." + type + "." + name, "");
        switch (type) {
            case "sidequests":
                arrays.get(id).getSideQ().remove(name);
                break;
            case "dailyquests":
                arrays.get(id).getDailyQ().remove(name);
                break;
            case "eventquests":
                arrays.get(id).getEventQ().remove(name);
                break;
        }
    }


    public ZScoreBoard getZScoreBoard(UUID uuid) {
        return arrays.get(uuid);
    }

    public void clearArrays() {
        arrays.clear();
    }

    public Map<UUID, ZScoreBoard> getArrays() {
        return arrays;
    }
}
