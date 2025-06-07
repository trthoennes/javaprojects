package org.tylley.bowspleef.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.tylley.bowspleef.Utils.CC;
import org.tylley.bowspleef.mananger.GameManager;

import java.util.UUID;


public class onDeathListener implements Listener {

    private GameManager gameManager;

    public onDeathListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity() != null) {

            Player p = e.getEntity();

            UUID uuid = p.getUniqueId();

            if (!gameManager.getPlayerManager().getPlayerList().isEmpty()) {
                if (gameManager.getPlayerManager().getPlayerList().contains(uuid)) {
                    gameManager.getPlayerManager().getPlayerList().remove(uuid);

                    e.getDrops().clear();
                    p.getInventory().clear();

                    p.setHealth(20);
                    gameManager.getSaveInventoryManager().loadInventory(p);
                    p.teleport(gameManager.getPlayerManager().getLastLocation().get(uuid));
                    gameManager.getPlayerManager().getLastLocation().remove(uuid);
                   e.setDeathMessage(CC.s("&a" + p.getDisplayName() + " &4Did not Deserve Death! "));

                }

            }


        }

    }



}
