package org.tylley.bowspleef.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.tylley.bowspleef.BowSpleefPlugin;
import org.tylley.bowspleef.Utils.CC;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

import java.util.HashMap;
import java.util.UUID;

public class SignInteractionListener implements Listener {

    private BowSpleefPlugin bowSpleefPlugin;
    private GameManager gameManager;
    public SignInteractionListener(GameManager gameManager, BowSpleefPlugin bowSpleefPlugin) {
        this.bowSpleefPlugin = bowSpleefPlugin;
        this.gameManager = gameManager;
    }

    @EventHandler
    public void signClick(SignChangeEvent event) {
        Block block = event.getBlock();
        BlockState blockState = block.getState();
            if (event.getLine(0).equalsIgnoreCase("[BowSpleef]")) {
                event.setLine(0, CC.s("&aBow Spleef"));
                event.setLine(1, CC.s("&aClick Me To Join!"));
            }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getClickedBlock()==null) return;

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        BlockState blockState = block.getState();
        if (blockState instanceof Sign) {
            Sign sign = (Sign) blockState;
            String line1 = sign.getLine(0);
            if (line1.equalsIgnoreCase(CC.s("&aBow Spleef"))) {

                if (gameManager.getGameState() != GameState.LOBBY) {
                    player.sendMessage("Game already in progress.");
                } else {
                    gameManager.setGameState(GameState.LOBBY);


                }
                if (gameManager.getPlayerManager().getPlayerList().contains(player.getUniqueId())) {

                    player.sendMessage("You are already in the game!");
                } else {

                    gameManager.getPlayerManager().getPlayerList().add(player.getUniqueId());
                    gameManager.getPlayerManager().waitingInLobby(player);
                    gameManager.getPlayerManager().teleportToLobby();
                    gameManager.getSaveInventoryManager().saveInventory(player);
                    gameManager.getPlayerManager().getLastLocation().put(player.getUniqueId(), player.getLocation());
                    player.sendMessage("You have joined the game!");
                }





            }
        }

    }
}
