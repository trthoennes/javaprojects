package org.tylley.bowspleef.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;
import org.tylley.bowspleef.mananger.PlayerManager;

import java.util.UUID;

public class GameStartDownTask extends BukkitRunnable {

    private final GameManager gameManager;

    public GameStartDownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timeLeft = 15;

    @Override
    public void run() {

        timeLeft--;

            if (timeLeft <= 0) {
                cancel();
                gameManager.setGameState(GameState.ACTIVE);
                return;
            } else {

                Bukkit.broadcastMessage(timeLeft + " until game starts!");
            }

    }
}
