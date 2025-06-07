package org.tylley.bowspleef.mananger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.tylley.bowspleef.BowSpleefPlugin;
import org.tylley.bowspleef.tasks.GameActiveTask;
import org.tylley.bowspleef.tasks.GameStartDownTask;
import org.tylley.bowspleef.tasks.LobbyWaitTask;

import java.util.*;

public class GameManager {
    //Managers
    private SaveInventoryManager saveInventoryManager;
    private final BlockManager blockManager;
    private final PlayerManager playerManager;
    private final ArenaManager arenaManager;
    private GameStartDownTask gameStartDownTask;
    private GameActiveTask gameActiveTask;
    private final BowSpleefPlugin plugin;
    //Default
    public GameState gameState = GameState.LOBBY;

    public GameManager(BowSpleefPlugin plugin) {
        this.plugin = plugin;

        this.saveInventoryManager = new SaveInventoryManager(this);
        this.arenaManager = new ArenaManager(plugin);
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);
    }
    public void setGameState(GameState gameState) {

        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;

        this.gameState = gameState;

        switch (gameState) {
            case LOBBY:
                //Timer Tasks
                LobbyWaitTask lobbyWaitTask = new LobbyWaitTask(this);
                lobbyWaitTask.runTaskTimer(plugin, 0, 20);
                break;
            case VOTING:
                Bukkit.broadcastMessage("Waiting For Votes!");
                break;
            case STARTING:
                if (this.gameStartDownTask != null) this.gameStartDownTask.cancel();
                this.gameStartDownTask = new GameStartDownTask(this);
                this.gameStartDownTask.runTaskTimer(plugin, 0, 20);
                getPlayerManager().teleportToStart();
                break;
            case ACTIVE:
                //start down task
                if (this.gameStartDownTask != null) this.gameStartDownTask.cancel();
                this.gameActiveTask = new GameActiveTask(this);
                this.gameActiveTask.runTaskTimer(plugin, 0, 20);
                getPlayerManager().giveBows();
                Bukkit.broadcastMessage("Active!");
                break;
            case WON:
                cleanup();
                break;
        }
    }
    public void cleanup() {

        if (getPlayerManager().getPlayerList().isEmpty()) return;
        if (getPlayerManager().getLastLocation().isEmpty()) return;
        if (getSaveInventoryManager().getInventories().isEmpty()) return;

        UUID uuid = getPlayerManager().getPlayerList().get(0);

        Player player = Bukkit.getPlayer(uuid);

        player.teleport(getPlayerManager().getLastLocation().get(uuid));

        getSaveInventoryManager().loadInventory(player);
        getPlayerManager().getPlayerList().remove(uuid);
        getPlayerManager().getLastLocation().remove(uuid);

        this.gameState = GameState.RESTARTING;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public SaveInventoryManager getSaveInventoryManager() {
        return saveInventoryManager;
    }

    public BowSpleefPlugin getPlugin() {
        return plugin;
    }
}
