package pureen.zboard.framework;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ZScoreBoard {

    private Map<String, String> SideQ;
    private Map<String, String> DailyQ;
    private Map<String, String> EventQ;
    private static final String EMPTY_STRING = " ";

    public ZScoreBoard(Map<String, String> sideQ,Map<String, String> dailyQ,Map<String, String> eventQ) {
        SideQ = new HashMap<>();
        assert sideQ != null;
        SideQ = sideQ;
        DailyQ = new HashMap<>();
        assert dailyQ != null;
        DailyQ = dailyQ;
        EventQ = new HashMap<>();
        assert eventQ != null;
        EventQ = eventQ;
    }



    public void createBoard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("zboard", Criteria.DUMMY, Component.text("§aZangosMMO"));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        if (!(getSideQ().isEmpty())) {
            Score s1 = objective.getScore("§5Yan Görevler:");
            s1.setScore(6);
            for (String a : getSideQ().keySet()) {
                Score s2 = objective.getScore("  §6" + PlaceholderAPI.setPlaceholders(player, getSideQ().get(a)));
                s2.setScore(5);
            }
        }
        if (!(getDailyQ().isEmpty())) {
            Score s1 = objective.getScore("§5Günlük Görevler:");
            s1.setScore(4);
            for (String a : getDailyQ().keySet()) {
                Score s2 = objective.getScore("  §6" + PlaceholderAPI.setPlaceholders(player, getDailyQ().get(a)));
                s2.setScore(3);
            }
        }
        if (!(getEventQ().isEmpty())) {
            Score s1 = objective.getScore("§5Etkinlik Görevleri:");
            s1.setScore(2);
            for (String a : getEventQ().keySet()) {
                Score s2 = objective.getScore("  §6" + PlaceholderAPI.setPlaceholders(player, getEventQ().get(a)));
                s2.setScore(1);
            }
        }
        player.setScoreboard(scoreboard);

    }

    public Map<String, String> getSideQ() {
        return SideQ;
    }

    public Map<String, String> getDailyQ() {
        return DailyQ;
    }

    public Map<String, String> getEventQ() {
        return EventQ;
    }
}
