package org.radium.rpvp.manager;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.Map;
import org.radium.rpvp.model.MapImpl;

import java.util.HashMap;
import java.util.Random;

public class MapManager implements MapImpl {
    private @Getter final HashMap<String, Map> mapMap = new HashMap<>();
    private String currentMap = null;

    @Override
    public void addMap(String name, Location spawn) {
        if (mapMap.containsKey(name)) return;
        Map map = new Map(name, spawn);
        mapMap.put(name, map);
        if (currentMap == null) {
            currentMap = name;
        }
    }

    @Override
    public void removeMap(String name) {
        if (!mapMap.containsKey(name)) return;
        Core.getInstance().getMapData().getConfig().set("Maps."+name, null);
        Core.getInstance().getMapData().save();
        mapMap.remove(name);
        if (name.equals(currentMap)) {
            currentMap = getRandomMap();
        }
    }

    @Override
    public Map getMap(String name) {
        return mapMap.get(name);
    }

    @Override
    public boolean isInSpawnArea(Entity entity) {
        for (Map map : mapMap.values()){
            if (map.contains(entity.getLocation())){
                return true;
            }
        }
        return false;
    }
    @Override
    public String getRandomMap() {
        if (mapMap.isEmpty()) {
            return null;
        }
        String[] mapNames = mapMap.keySet().toArray(new String[0]);
        String nextMap = mapNames[new Random().nextInt(mapNames.length)];
        while (nextMap.equals(currentMap)) {
            nextMap = mapNames[new Random().nextInt(mapNames.length)];
        }
        return nextMap;
    }
}
