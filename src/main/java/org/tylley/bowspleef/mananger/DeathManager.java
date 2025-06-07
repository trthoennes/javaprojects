package org.tylley.bowspleef.mananger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeathManager {

    //Manages Death of Player

    private GameManager gameManager;
    public DeathManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public List<UUID> spectators = new ArrayList<>();

    //Once there are spectators where can they go?
    // Compass (Teleport to players still alive)
    // Feather (Leave being spectator)
    // Barriers to not leave stadium


}
