package org.tylley.bowspleef.mananger;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class SaveInventoryManager {

    private GameManager gameManager;

    public SaveInventoryManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private HashMap<UUID, Inventory> hashMap = new HashMap<>();


    public void saveInventory(Player player) {

        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                hashMap.put(player.getUniqueId(), inventory);
            }

        }

    }

    public void loadInventory(Player player) {

        Inventory inventory = hashMap.get(player.getUniqueId());

        if (inventory != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) != null) {
                    player.getInventory().setItem(i, inventory.getItem(i));
                }
            }
            player.sendMessage("Inventory Restored!");
        }
    }

    public HashMap<UUID, Inventory> getInventories() {
        return hashMap;

    }


}
