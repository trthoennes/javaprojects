package org.tylley.bowspleef.mananger;

import org.bukkit.Bukkit;

public class RollBackManager {


    private GameManager gameManager;
    private LocalGameMap gameMap;
    public RollBackManager(GameManager gameManager, LocalGameMap gameMap) {
        this.gameMap = gameMap;
        this.gameManager = gameManager;
    }


    public void rollBackWorld() {

        gameMap.restoreFromSource();
        Bukkit.broadcastMessage("Resetting!");
        if (gameManager.getGameState() != GameState.ACTIVE) {
            gameManager.setGameState(GameState.RESTARTING);

        }


    }



}
