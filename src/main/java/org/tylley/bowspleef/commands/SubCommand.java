package org.tylley.bowspleef.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getUsage();

    public abstract String getSyntax();

    public abstract void perform(Player player, String[] args);
}
