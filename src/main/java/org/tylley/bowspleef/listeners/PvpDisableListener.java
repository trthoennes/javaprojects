package org.tylley.bowspleef.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;
import org.tylley.bowspleef.mananger.PlayerManager;

public class PvpDisableListener implements Listener {

    private GameManager gameManager;

    public PvpDisableListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHit(EntityDamageByEntityEvent event) {


        Entity attacker = event.getDamager();
        Entity damaged = event.getEntity();

        if (gameManager.gameState == GameState.ACTIVE || gameManager.gameState == GameState.LOBBY || gameManager.gameState == GameState.STARTING) {
            if (attacker instanceof Player && damaged instanceof Player) {

                event.setCancelled(true);
            } else if (event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
                event.setCancelled(true);

            }

        }
    }


}
