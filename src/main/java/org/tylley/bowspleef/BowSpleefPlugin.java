package org.tylley.bowspleef;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.tylley.bowspleef.commands.BowSpleefBaseCommand;
import org.tylley.bowspleef.listeners.*;
import org.tylley.bowspleef.mananger.*;

import java.io.File;

public class BowSpleefPlugin extends JavaPlugin {


    private RollBackManager rollBackManager;
    private LocalGameMap gameMap;
    private ArenaManager arenaManager;
    private GameManager gameManager;

    private BukkitTask task;
    private BukkitTask task2;

    @Override
    public void onEnable() {

        super.onEnable();

        getDataFolder().mkdirs();

        File gameMapsFolder = new File(getDataFolder(), "gameMaps");
        if (!gameMapsFolder.exists()) {
            gameMapsFolder.mkdirs();
        }

        gameMap = new LocalGameMap(gameMapsFolder, "Elcadre", true);

        this.rollBackManager = new RollBackManager(gameManager, gameMap);
        this.arenaManager = new ArenaManager(this);
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new onDeathListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PvpDisableListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new SignInteractionListener(gameManager, this), this);
        getServer().getPluginManager().registerEvents(new CancelTNTExplosion(gameManager), this);
        getCommand("bowspleef").setExecutor(new BowSpleefBaseCommand(arenaManager, gameManager, this));

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

        super.onDisable();

        gameManager.cleanup();

        if (task != null) {
            task.cancel();

        getGameMap().unload();

        }
    }

    public LocalGameMap getGameMap() {
        return gameMap;
    }

    public RollBackManager getRollBackManager() {
        return rollBackManager;
    }
}

