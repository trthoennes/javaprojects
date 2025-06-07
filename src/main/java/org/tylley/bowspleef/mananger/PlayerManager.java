package org.tylley.bowspleef.mananger;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.tylley.bowspleef.Utils.CC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.XMLFormatter;

public class PlayerManager {

    private GameManager gameManager;

    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private HashMap<UUID, Location> lastLocation = new HashMap<>();

    ArrayList<UUID> playerList = new ArrayList<>();


    public void giveBows() {

        for (UUID uuid : playerList) {

            Player p = Bukkit.getPlayer(uuid);

            if (p.getGameMode() != GameMode.ADVENTURE) {
                p.setGameMode(GameMode.ADVENTURE);

            }

            giveBow(p);
        }
    }

    public ArrayList<UUID> getPlayerList() {
        return playerList;
    }

    public HashMap<UUID, Location> getLastLocation() {
        return lastLocation;
    }


    private void giveBow(Player player) {

        player.getInventory().clear();

        ArrayList<String> bowLore = new ArrayList<>();
        bowLore.add("Shoot the TNT below other players");
        ItemStack bow = new ItemStack(Material.BOW);

        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        bowMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowMeta.setDisplayName(CC.s("&6Bow"));
        bowMeta.setLore(bowLore);

        bow.setItemMeta(bowMeta);

        player.getInventory().addItem(bow);
        player.getInventory().setItem(8, new ItemStack(Material.ARROW));
    }

    public void teleportToLobby() {

        for (UUID uuid : playerList) {

            Player p = Bukkit.getPlayer(uuid);

            p.teleport(gameManager.getArenaManager().getLobbyLocation());
        }

    }

    public void teleportToStart() {

        if (gameManager.getPlugin().getGameMap().load()) {

            for (UUID uuid : playerList) {

                Player p = Bukkit.getPlayer(uuid);

                World world = gameManager.getPlugin().getGameMap().getWorld();

                double pitch = gameManager.getPlugin().getConfig().getDouble("Spawn.Pitch");
                double x = gameManager.getPlugin().getConfig().getDouble("Spawn.X");
                double y = gameManager.getPlugin().getConfig().getDouble("Spawn.Y");
                double z = gameManager.getPlugin().getConfig().getDouble("Spawn.Z");
                p.teleport(new Location(world, x, y, z));
            }
        }


    }

    public void waitingInLobby(Player player) {
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
    }

    public void removePlayer(Player player) {

        if (!playerList.contains(player.getUniqueId())) {

            return;
        }
        Iterator<UUID> itr = playerList.iterator();

        while (itr.hasNext()) {

            UUID next = itr.next();

            if (next.equals(player.getUniqueId())) {
                itr.remove();

                player.sendMessage("You left the game.");

                player.teleport(player.getWorld().getSpawnLocation());
                player.setGameMode(GameMode.SURVIVAL);
            }

        }
    }

    //Check if player is under the arena map
    public void underMap(Player player) {
        double deathSpot = gameManager.getArenaManager().getDeathLocation();

        if (!playerList.contains(player.getUniqueId())) return;
        if (player.getLocation().getY() < player.getLocation().getY() - deathSpot) {
            player.setHealth(0.0);
            removePlayer(player);

        }

    }

}


