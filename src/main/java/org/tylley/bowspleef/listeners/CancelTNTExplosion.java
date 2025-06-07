package org.tylley.bowspleef.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

public class CancelTNTExplosion implements Listener {

    private GameManager gameManager;

    public CancelTNTExplosion(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTNTPRimed(EntityExplodeEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE) return;
        if (event.getEntityType() == EntityType.PRIMED_TNT) {

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void dontExplode(EntityExplodeEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE) return;
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
        }
    }
}
