package org.tylley.bowspleef.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

public class StartCommand extends SubCommand {

    private GameManager gameManager;

    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Starts a bowspleef game inside an arena";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/bowspleef start";
    }

    @Override
    public void perform(Player player, String[] args) {

      gameManager.setGameState(GameState.STARTING);

    }



}
