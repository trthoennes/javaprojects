package org.tylley.bowspleef.commands;

import org.bukkit.entity.Player;
import org.tylley.bowspleef.mananger.GameManager;

import javax.smartcardio.CardException;
import java.util.Iterator;
import java.util.UUID;

public class LeaveCommand extends SubCommand {


    private final GameManager gameManager;

    public LeaveCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "leave bowspleef game";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/bowspleef leave";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (gameManager.getPlayerManager().getLastLocation().containsKey(player.getUniqueId())) {


            player.teleport(gameManager.getPlayerManager().getLastLocation().get(player.getUniqueId()));
            gameManager.getPlayerManager().getLastLocation().remove(player.getUniqueId());
            gameManager.getPlayerManager().removePlayer(player);
        } else {
            player.sendMessage("You're not in the game.");


        }


    }
}
