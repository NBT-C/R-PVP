package org.radium.rpvp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.radium.rpvp.base.Map;
import org.radium.rpvp.commands.MapCommand;
import org.radium.rpvp.commands.SaveCommand;
import org.radium.rpvp.events.MapRegionListener;
import org.radium.rpvp.events.PlayerJoinListener;
import org.radium.rpvp.manager.MapManager;
import org.radium.rpvp.manager.UserManager;
import org.radium.rpvp.manager.kits.KitManager;
import org.radium.rpvp.manager.loader.UserLoader;
import org.radium.rpvp.manager.storage.DataManager;
import org.radium.rpvp.tasks.MapChangeTask;
import org.radium.rpvp.util.config.SimpleConfig;

public final class Core extends JavaPlugin {
    private static @Getter Core instance;
    private @Getter ObjectMapper objectMapper;
    private @Getter SimpleConfig databaseData;
    private @Getter SimpleConfig mapData;
    private @Getter DataManager dataManager;
    private @Getter UserManager userManager;
    private @Getter MapManager mapManager;
    private @Getter KitManager kitManager;

    @Override
    public void onEnable() {
        init();
        loadConfiguration();
        loadListeners();
        loadCommands();
        if (!getDataManager().connect()){
            getServer().getPluginManager().disablePlugin(this);
            throw new RuntimeException("Could not connect to database.");
        }
        UserLoader.loadFromDatabase();
        Map.loadFromConfiguration();
        new MapChangeTask(mapManager).runTaskTimer(this, 20L, 20L);
    }
    @Override
    public void onDisable() {
        UserLoader.save();
        getDataManager().disconnect();
    }
    private void init(){
        instance = this;
        this.dataManager = new DataManager();
        this.userManager = new UserManager();
        this.mapManager = new MapManager();
        this.objectMapper = new ObjectMapper();
        this.kitManager = new KitManager();
    }
    private void loadConfiguration(){
        this.databaseData = new SimpleConfig("database.yml", getDataFolder().getPath());
        this.mapData = new SimpleConfig("maps.yml", getDataFolder().getPath());
    }
    private void loadListeners(){
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new MapRegionListener(), this);
    }
    public void loadCommands(){
        getCommand("map").setExecutor(new MapCommand());
        getCommand("save").setExecutor(new SaveCommand());
    }
}
