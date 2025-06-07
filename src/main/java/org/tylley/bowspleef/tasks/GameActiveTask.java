package org.tylley.bowspleef.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.tylley.bowspleef.Utils.CC;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

import java.util.UUID;

public class GameActiveTask extends BukkitRunnable {


    private GameManager gameManager;

    public GameActiveTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    int timeLeft = 60;

    //60 seconds until game over until someone died

    @Override
    public void run() {

        if (gameManager.getGameState() != GameState.ACTIVE) return;

        timeLeft--;

        for (UUID uuid : gameManager.getPlayerManager().getPlayerList()){
            Player player = Bukkit.getPlayer(uuid);
            gameManager.getPlayerManager().underMap(player);
        }


        if (gameManager.getPlayerManager().getPlayerList().size() == 1) {

            UUID uuid = gameManager.getPlayerManager().getPlayerList().get(0);

            Player player = Bukkit.getPlayer(uuid);

            Bukkit.broadcastMessage(CC.s("&a" + player.getDisplayName() + " &2Has Won!"));

            player.getInventory().clear();


            gameManager.setGameState(GameState.WON);
            player.teleport(gameManager.getPlayerManager().getLastLocation().get(uuid));
            gameManager.getPlugin().getRollBackManager().rollBackWorld();
            cancel();

        } else if (gameManager.getPlayerManager().getPlayerList().size() > 1) {
            if (timeLeft >= 0) {
                Bukkit.broadcastMessage(timeLeft + " Remaining!");
            } else {
                cancel();

            }
        }


    }

}

