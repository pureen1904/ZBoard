package pureen.zboard.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import pureen.zboard.ZBoard;
import pureen.zboard.managers.ZScoreBoardManager;


public abstract class Command implements CommandExecutor {
    protected ZBoard zboard;
    protected String name;


    public Command(ZBoard zboard, String name) {
        this.zboard = zboard;
        PluginCommand pluginCommand = zboard.getCommand(name);
        pluginCommand.setExecutor(this);
    }

    protected abstract void execute(Player player, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            execute((Player) sender, args);
        }

        return true;
    }





}
