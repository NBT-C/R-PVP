package org.radium.rpvp.model;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.radium.rpvp.base.Map;

public interface MapImpl {
    void addMap(String name, Location spawn);
    void removeMap(String name);
    Map getMap(String name);
    boolean isInSpawnArea(Entity entity);

    String getRandomMap();
}
