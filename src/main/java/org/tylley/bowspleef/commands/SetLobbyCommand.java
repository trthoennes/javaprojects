package org.tylley.bowspleef.commands;

import org.bukkit.entity.Player;
import org.tylley.bowspleef.BowSpleefPlugin;
import org.tylley.bowspleef.Utils.CC;
import org.tylley.bowspleef.mananger.ArenaManager;

import java.rmi.MarshalException;

public class SetLobbyCommand extends SubCommand {

    private ArenaManager arenaManager;

    private BowSpleefPlugin plugin;

    public SetLobbyCommand(BowSpleefPlugin plugin, ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
        this.plugin = plugin;

    }

    @Override
    public String getName() {
        return "setlobby";
    }

    @Override
    public String getDescription() {
        return "Sets Lobby for the MiniGame";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/bowspleef setlobby";
    }

    @Override
    public void perform(Player player, String[] args) {


        arenaManager.setLobbyLocation(player);
        player.sendMessage(CC.s("&lConfiguration for the Lobby has been Saved!"));
    }


}
