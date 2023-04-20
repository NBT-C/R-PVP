package org.radium.rpvp.util.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.radium.rpvp.Core;

import java.io.File;
import java.io.IOException;

public class SimpleConfig implements Config {
    public String path;
    private final File file;
    private YamlConfiguration cfg;

    public SimpleConfig(String name, String path) {
        this.path = path;
        (new File(path)).mkdir();
        this.file = new File(path, name);
        if (!this.file.exists()) {
            try {
                Core.getInstance().saveResource(name, true);
                this.cfg = YamlConfiguration.loadConfiguration(this.file);
            } catch (Exception var4) {
            }
        }

        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean exists() {
        return this.file.exists();
    }

    public void delete() {
        this.file.delete();
        this.cfg = null;
    }

    public YamlConfiguration getConfig() {
        if (this.cfg == null) {
            this.cfg = YamlConfiguration.loadConfiguration(this.file);
        }

        return this.cfg;
    }

    public void save() {
        try {
            this.cfg.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public File getFile() {
        return this.file;
    }
}
