package org.tylley.bowspleef.mananger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.tylley.bowspleef.BowSpleefPlugin;

public class ArenaManager {


    private Location location;
    //manage arenas
    //get locations of arena, spawn, spectator, etc.
    private BowSpleefPlugin bowSpleefPlugin;

    public ArenaManager(BowSpleefPlugin plugin) {
        this.bowSpleefPlugin = plugin;

    }

    public Location getLobbyLocation() {

        String worldName = bowSpleefPlugin.getConfig().getString("Lobby.World");
        double x = bowSpleefPlugin.getConfig().getDouble("Lobby.X");
        double y = bowSpleefPlugin.getConfig().getDouble("Lobby.Y");
        double z = bowSpleefPlugin.getConfig().getDouble("Lobby.Z");

        World world = bowSpleefPlugin.getServer().getWorld(worldName);

        location = new Location(world, x, y, z);
        return location;
    }

    public void setLobbyLocation(Player player) {

        if (bowSpleefPlugin.getConfig() != null) {
            bowSpleefPlugin.getConfig().set("Lobby.Pitch", player.getLocation().getPitch());
            bowSpleefPlugin.getConfig().set("Lobby.World", player.getLocation().getWorld().getName());
            bowSpleefPlugin.getConfig().set("Lobby.X", player.getLocation().getX());
            bowSpleefPlugin.getConfig().set("Lobby.Y", player.getLocation().getY());
            bowSpleefPlugin.getConfig().set("Lobby.Z", player.getLocation().getZ());

            bowSpleefPlugin.saveConfig();

        } else {
            player.sendMessage("Configuration is null.");


        }
    }

    public Location getSpawnLocation() {

        String worldName = bowSpleefPlugin.getConfig().getString("Spawn.World");
        double x = bowSpleefPlugin.getConfig().getDouble("Spawn.X");
        double y = bowSpleefPlugin.getConfig().getDouble("Spawn.Y");
        double z = bowSpleefPlugin.getConfig().getDouble("Spawn.Z");

        World world = bowSpleefPlugin.getServer().getWorld(worldName);

        location = new Location(world, x, y, z);
        return location;
    }

    public void setSpawnLocation(Player player) {

        if (bowSpleefPlugin.getConfig() != null) {
            bowSpleefPlugin.getConfig().set("Spawn.Pitch", player.getLocation().getPitch());
            bowSpleefPlugin.getConfig().set("Spawn.X", player.getLocation().getX());
            bowSpleefPlugin.getConfig().set("Spawn.Y", player.getLocation().getY());
            bowSpleefPlugin.getConfig().set("Spawn.Z", player.getLocation().getZ());

            bowSpleefPlugin.saveConfig();

        } else {

            player.sendMessage("Configuration is null.");

        }

    }
    //Edit in config for arenas
    public double getDeathLocation(){

        return 10.0;
    }
}