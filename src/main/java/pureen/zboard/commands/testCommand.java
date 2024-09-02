package pureen.zboard.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pureen.zboard.ZBoard;
import pureen.zboard.configs.ScoreboardConfig;
import pureen.zboard.managers.ZScoreBoardManager;

import java.util.ArrayList;

import static pureen.zboard.Utils.msgPlayer;

public class testCommand extends Command {

    private ScoreboardConfig scoreboardConfig;
    private ZScoreBoardManager zScoreBoardManager;

    public testCommand(ZBoard zBoard) {
        super(zBoard, "checkboarddata");
        this.scoreboardConfig = zboard.getConfigManager().getScoreboardConfig();
        this.zScoreBoardManager = zBoard.getzScoreBoardManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        if (!(args.length == 1)) return;
        Player player1 = Bukkit.getPlayer(args[0]);
        //msgPlayer(player1, String.valueOf(scoreboardConfig.getArrayFromLine(player1)));
        //msgPlayer(player1, String.valueOf(zScoreBoardManager.getArrays().get(player1.getUniqueId()).getSbArrays()));
    }
}
