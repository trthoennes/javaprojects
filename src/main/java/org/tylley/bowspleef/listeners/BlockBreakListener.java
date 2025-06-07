package org.tylley.bowspleef.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

public class BlockBreakListener implements Listener {

    private GameManager gameManager;
    public BlockBreakListener(GameManager gameManager) {

        this.gameManager = gameManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!gameManager.getBlockManager().canBreak(event.getBlock()) && gameManager.gameState == GameState.ACTIVE) {

            event.setCancelled(true);
        }
    }
}
