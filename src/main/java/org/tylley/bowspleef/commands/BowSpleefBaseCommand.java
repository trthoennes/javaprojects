package org.tylley.bowspleef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.tylley.bowspleef.BowSpleefPlugin;
import org.tylley.bowspleef.mananger.ArenaManager;
import org.tylley.bowspleef.mananger.GameManager;

import java.util.ArrayList;

public class BowSpleefBaseCommand implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>();

    private ArenaManager arenaManager;

    private GameManager gameManager;

    private BowSpleefPlugin bowSpleefPlugin;

    public BowSpleefBaseCommand(ArenaManager arenaManager, GameManager gameManager, BowSpleefPlugin bowSpleefPlugin) {


        subCommands.add(new LeaveCommand(gameManager));
        subCommands.add(new SetLobbyCommand(bowSpleefPlugin, arenaManager));
        subCommands.add(new StartCommand(gameManager));
        subCommands.add(new SetSpawnCommand(bowSpleefPlugin, arenaManager));

        this.gameManager = gameManager;
        this.bowSpleefPlugin = bowSpleefPlugin;
        this.arenaManager = arenaManager;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (strings.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (strings[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        getSubCommands().get(i).perform(player, strings);
                    }
                }
            } else {
               for (int i = 0; i < getSubCommands().size(); i++) {
                   player.sendMessage(getSubCommands().get(i).getSyntax() + " - " + getSubCommands().get(i).getDescription());
               }
            }

        }
        return false;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
