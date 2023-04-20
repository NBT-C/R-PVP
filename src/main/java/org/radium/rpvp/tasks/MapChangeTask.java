package org.radium.rpvp.tasks;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.Map;
import org.radium.rpvp.manager.MapManager;

import java.util.HashMap;

public class MapChangeTask extends BukkitRunnable {
    private final MapManager mapManager;
    private int remainingTime = 20;

    public MapChangeTask(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void start(){
        HashMap<String, Map> mapMap = mapManager.getMapMap();
        if (mapMap.isEmpty()) {
            return;
        }
        Bukkit.broadcastMessage("Vote for the next map:");
        for (Map map : mapMap.values()) {
            TextComponent voteLink = new TextComponent("# " + map.getName() + " [Click to vote]");
            voteLink.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vote " + map.getName()));
            voteLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to vote for " + map.getName()).create()));
            Bukkit.spigot().broadcast(voteLink);
        }
        Bukkit.broadcastMessage("Vote ends in 20 seconds.");
        new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.broadcastMessage("Vote ended!");
            }
        }.runTaskLater(Core.getInstance(), 200L);
    }

    @Override
    public void run() {
        if (remainingTime == 0) {
            remainingTime = 20;
            start();
        }
        remainingTime--;
    }
}

