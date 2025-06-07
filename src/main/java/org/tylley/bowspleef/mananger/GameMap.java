package org.tylley.bowspleef.mananger;

import org.bukkit.World;

public interface GameMap {

    boolean load();
    void unload();

    boolean restoreFromSource();

    boolean isLoaded();

    World getWorld();

}
