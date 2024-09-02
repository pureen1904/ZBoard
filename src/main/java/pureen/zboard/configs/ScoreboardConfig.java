package pureen.zboard.configs;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;
import pureen.zboard.ZBoard;
import pureen.zboard.managers.ZScoreBoardManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardConfig extends Config {

    private ZScoreBoardManager zScoreBoardManager;

    public ScoreboardConfig(ZBoard zBoard) {
        super(zBoard, "playerdata.yml");
        this.zScoreBoardManager = zBoard.getzScoreBoardManager();
    }


    public ArrayList<String> getArrayFromLine(OfflinePlayer player) {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 1;
        String a = "";
        while (!(a == null)) {
            a = getString(player.getUniqueId() + ".scoreboardvalues.line-" + i++);
            arrayList.add(a);
        }
        arrayList.remove(arrayList.size() - 1);
        return arrayList;
    }


    public HashMap<String, String> getHashFromConfig(OfflinePlayer player, String string) {
        HashMap<String, String> map = new HashMap<>();
        for (String a : getSection(player.getUniqueId() + ".scoreboardvalues." + string + ".")) {
            map.put(a, getString(player.getUniqueId() + ".scoreboardvalues." + string + "." + a));
        }
        return map;
    }


    public void setAllDatas(UUID id, Map<String, String> sideq, Map<String, String> dailyq, Map<String, String> eventq) {
        for (String a : sideq.keySet()) {
            set(id.toString() + ".scoreboardvalues.sidequests." + a, sideq.get(a));
        }
        for (String a : dailyq.keySet()) {
            set(id.toString() + ".scoreboardvalues.dailyquests." + a, dailyq.get(a));
        }
        for (String a : eventq.keySet()) {
            set(id.toString() + ".scoreboardvalues.eventquests." + a, eventq.get(a));
        }
    }


    public void setDefaultData(UUID id) {
        set(id.toString() + ".scoreboardvalues.sidequests", null);
        set(id + ".scoreboardvalues.dailyquests", null);
        set(id + ".scoreboardvalues.eventquests", null);
    }



}
