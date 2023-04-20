package org.radium.rpvp.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.radium.rpvp.Core;
import org.radium.rpvp.manager.MapManager;

public class MapRegionListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!Core.getInstance().getMapManager().isInSpawnArea(entity)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        MapManager mapManager = Core.getInstance().getMapManager();
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if (mapManager.isInSpawnArea(entity) || mapManager.isInSpawnArea(damager)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission("pvp.break")) return;
        event.setCancelled(true);
    }
}
