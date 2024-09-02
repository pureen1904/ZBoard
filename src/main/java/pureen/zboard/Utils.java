package pureen.zboard;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Utils {

    private static Logger logger = ZBoard.getPluginLogger();

    public static void log(String... strings) {
        for (String string : strings) {
            logger.info(string);
        }
    }

    public static void msgPlayer(Player player, String... strings) {
        for (String string : strings) {
            player.sendMessage(string);
        }
    }



}
