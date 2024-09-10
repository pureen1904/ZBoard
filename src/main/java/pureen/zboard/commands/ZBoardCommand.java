package pureen.zboard.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pureen.zboard.ZBoard;
import pureen.zboard.configs.ScoreboardConfig;
import pureen.zboard.framework.ZScoreBoard;
import pureen.zboard.managers.ConfigManager;
import pureen.zboard.managers.ZScoreBoardManager;

import java.util.ArrayList;

import static pureen.zboard.Utils.msgPlayer;

public class ZBoardCommand extends Command {


    private ZScoreBoardManager zScoreBoardManager;
    private ConfigManager configManager;

    public ZBoardCommand(ZBoard zBoard) {
        super(zBoard,"zboard");
        this.zScoreBoardManager = zBoard.getzScoreBoardManager();
        this.configManager = zBoard.getConfigManager();
    }

    @Override
    protected void execute(Player player, String[] args) {
        switch (args[0]) {
            case "reload":
                if (!(args.length == 1)) break;
                configManager.saveConfigs();
                zScoreBoardManager.clearArrays();
                configManager.loadConfigs();
                zScoreBoardManager.loadZScoreBoard();
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    ZScoreBoard zScoreBoard = zScoreBoardManager.getZScoreBoard(player1.getUniqueId());
                    zScoreBoard.createBoard(player1);
                }
                msgPlayer(player, "&a[ZBoard] &3Succesfully reloaded.");
                break;
            case "add":
                //usage /zboard add %player% <type> <Name> "String"
                if (args.length < 5) break;
                Player player1 = (Player) Bukkit.getOfflinePlayer(args[1]);
                ZScoreBoard zScoreBoard = zScoreBoardManager.getZScoreBoard(player1.getUniqueId());
                String a = "";
                int i = 4;
                do {
                    a = a + args[i++] + " ";
                } while (i < args.length);
                switch (args[2]) {
                    case "sideq":
                        zScoreBoardManager.reSetLineData(player1.getUniqueId(), "sidequests", args[3], a.trim());
                        break;
                    case "dailyq":
                        zScoreBoardManager.reSetLineData(player1.getUniqueId(), "dailyquests", args[3], a.trim());
                        break;
                    case "eventq":
                        zScoreBoardManager.reSetLineData(player1.getUniqueId(), "eventquests", args[3], a.trim());
                        break;
                }
                zScoreBoard.createBoard(player1);
                configManager.saveConfigs();
                break;
            case "del":
                //usage /zboard del sideq %player% <name>
                if (!(args.length == 4)) break;
                Player player2 = (Player) Bukkit.getOfflinePlayer(args[2]);
                switch (args[1]) {
                    case "sideq":
                        zScoreBoardManager.DelLineData(player2.getUniqueId(), "sidequests", args[3]);
                        break;
                    case "dailyq":
                        zScoreBoardManager.DelLineData(player2.getUniqueId(), "dailyquests", args[3]);
                        break;
                    case "eventq":
                        zScoreBoardManager.DelLineData(player2.getUniqueId(), "eventquests", args[3]);
                        break;
                }
                configManager.saveConfigs();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }

    }
}
