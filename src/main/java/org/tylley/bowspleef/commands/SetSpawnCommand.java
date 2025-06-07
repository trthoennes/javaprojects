package org.tylley.bowspleef.commands;

import org.bukkit.entity.Player;
import org.tylley.bowspleef.BowSpleefPlugin;
import org.tylley.bowspleef.Utils.CC;
import org.tylley.bowspleef.mananger.ArenaManager;

public class SetSpawnCommand extends SubCommand {

    private ArenaManager arenaManager;

    private BowSpleefPlugin bowSpleefPlugin;

    public SetSpawnCommand(BowSpleefPlugin bowSpleefPlugin, ArenaManager arenaManager) {

        this.arenaManager = arenaManager;
        this.bowSpleefPlugin = bowSpleefPlugin;
    }

    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "Sets Spawn of the Arena";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/bowspleef setspawn";
    }

    @Override
    public void perform(Player player, String[] args) {

        arenaManager.setSpawnLocation(player);

        player.sendMessage(CC.s("&lConfiguration for the Spawn point has been Saved!"));


    }
}
