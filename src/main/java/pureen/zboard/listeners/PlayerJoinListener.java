package pureen.zboard.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import pureen.zboard.ZBoard;
import pureen.zboard.configs.ScoreboardConfig;
import pureen.zboard.framework.ZScoreBoard;
import pureen.zboard.managers.ZScoreBoardManager;

import static pureen.zboard.scoreboard.scoreboardutils.createTemplateScoreboard;
import static pureen.zboard.scoreboard.scoreboardutils.setBoard;

public class PlayerJoinListener implements Listener {

    ZBoard zBoard;
    private ZScoreBoardManager zScoreBoardManager;
    private ScoreboardConfig scoreboardConfig;
    public PlayerJoinListener(ZBoard zBoard) {
        this.zBoard = zBoard;
        zScoreBoardManager = zBoard.getzScoreBoardManager();
        scoreboardConfig = zBoard.getConfigManager().getScoreboardConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ZScoreBoard zScoreBoard = zScoreBoardManager.getZScoreBoard(player.getUniqueId());
        if(zScoreBoard == null) {
            ZScoreBoard zScoreBoard2 = zScoreBoardManager.createZScoreBoard(player);
            scoreboardConfig.setDefaultData(player.getUniqueId());
        } else {
            zScoreBoard.createBoard(player);
        }

    }


}
