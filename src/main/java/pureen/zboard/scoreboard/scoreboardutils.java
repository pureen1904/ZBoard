package pureen.zboard.scoreboard;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class scoreboardutils {

    public static void setBoard(Player player, Scoreboard scoreboard) {
       player.setScoreboard(scoreboard);
    }

    public static Scoreboard createTemplateScoreboard() {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Component textComponent = Component.text("&aZangosMMO");
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("zboard", Criteria.DUMMY, textComponent);
        Score s1 = objective.getScore("");
        Score s2 = objective.getScore("&a&lObjektifler:");

        return scoreboard;
    }


}
