package org.tylley.bowspleef.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.tylley.bowspleef.mananger.GameManager;
import org.tylley.bowspleef.mananger.GameState;

import java.util.Iterator;
import java.util.UUID;

public class LobbyWaitTask extends BukkitRunnable {

    private GameManager gameManager;

    public LobbyWaitTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    private int lobbyWait = 10;

    @Override
    public void run() {

        lobbyWait--;

        if (lobbyWait < 0 || gameManager.getPlayerManager().getPlayerList().isEmpty()) {
            cancel();
        }
        if (gameManager.getPlayerManager().getPlayerList().size() > 1) {
            Bukkit.broadcastMessage("Starting!");
            cancel();
            gameManager.setGameState(GameState.STARTING);
        } else {

            Bukkit.broadcastMessage("Time Remaining until Game Cancels: " + lobbyWait);

            if (lobbyWait == 0) {


                //remove all players

                Iterator<UUID> itr = gameManager.getPlayerManager().getPlayerList().iterator();


                for (Player p : Bukkit.getOnlinePlayers()) {

                    while (itr.hasNext()) {

                        UUID next = itr.next();

                        if (next.equals(p.getUniqueId())) {
                            itr.remove();

                            gameManager.getPlayerManager().removePlayer(p);

                            Bukkit.broadcastMessage("Player " + p.getDisplayName() + " removed!");

                           p.teleport(gameManager.getPlayerManager().getLastLocation().get(p.getUniqueId()));

                        }

                    }
                }
                cancel();
            }

        }

    }

}
