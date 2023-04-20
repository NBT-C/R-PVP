package org.radium.rpvp.base;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.radium.rpvp.Core;

import java.util.logging.Level;

@Data
public class Map {
    private final String name;
    private Location spawn;
    private Location pos1Location;
    private Location pos2Location;

    public Map(String name, Location spawn) {
        this.name = name;
        this.spawn = spawn;
        Core.getInstance().getMapData().getConfig().set("Maps." + name + ".Spawn", spawn);
        Core.getInstance().getMapData().save();
    }

    public void setPos1Location(Location pos1Location) {
        this.pos1Location = pos1Location;
        Core.getInstance().getMapData().getConfig().set("Maps." + name + ".Pos1", pos1Location);
        Core.getInstance().getMapData().save();
    }

    public void setPos2Location(Location pos2Location) {
        this.pos2Location = pos2Location;
        Core.getInstance().getMapData().getConfig().set("Maps." + name + ".Pos2", pos2Location);
        Core.getInstance().getMapData().save();
    }

    public static void loadFromConfiguration() {
        Configuration config = Core.getInstance().getMapData().getConfig();
        ConfigurationSection configurationSection = config.getConfigurationSection("Maps");
        if (configurationSection == null) {
            Core.getInstance().getLogger().log(Level.WARNING, "PLEASE CREATE MAPS, COULD NOT SET A DEFAULT MAP!");
            return;
        }
        for (String mapName : configurationSection.getKeys(false)) {
            ConfigurationSection mapSection = config.getConfigurationSection("Maps." + mapName);
            if (mapSection == null)
                continue;
            Map map = new Map(mapName, (Location) mapSection.get("Spawn"));
            map.setPos1Location((Location) mapSection.get("Pos1"));
            map.setPos2Location((Location) mapSection.get("Pos2"));

            Core.getInstance().getMapManager().getMapMap().put(mapName, map);

            Core.getInstance().getLogger().log(Level.INFO, "MAP LOADED - " + map.getName() + " (!)");
        }
        if (Core.getInstance().getMapManager().getRandomMap() != null) {
            Core.getInstance().getLogger().log(Level.INFO, "Selected as a start map: " + Core.getInstance().getMapManager().getRandomMap());
            return;
        }
        Core.getInstance().getLogger().log(Level.WARNING, "PLEASE CREATE MAPS, COULD NOT SET A DEFAULT MAP!");
    }

    public boolean contains(Location location) {
        if (!location.getWorld().equals(pos1Location.getWorld())) {
            return false;
        }

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        double x1 = pos1Location.getX();
        double y1 = pos1Location.getY();
        double z1 = pos1Location.getZ();

        double x2 = pos2Location.getX();
        double y2 = pos2Location.getY();
        double z2 = pos2Location.getZ();

        double maxX = Math.max(x1, x2);
        double maxY = Math.max(y1, y2);
        double maxZ = Math.max(z1, z2);

        double minX = Math.min(x1, x2);
        double minY = Math.min(y1, y2);
        double minZ = Math.min(z1, z2);

        return (x >= minX && x <= maxX) &&
                (y >= minY && y <= maxY) &&
                (z >= minZ && z <= maxZ);
    }
}
