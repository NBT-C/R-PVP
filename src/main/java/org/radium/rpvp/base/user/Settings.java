package org.radium.rpvp.base.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class Settings {
    private String selectedKit;
    private List<Integer> unlockedPerks;
    private List<Integer> unlockedTrails;
    private Map<String, Integer> kitHotbar;

    public Settings() {
        this.selectedKit = "DEFAULT";
        this.unlockedPerks = new ArrayList<>();

        this.unlockedTrails = new ArrayList<>();

        this.kitHotbar = new HashMap<>();
        this.kitHotbar.put("IRON_SWORD", 0);
        this.kitHotbar.put("FISHING_ROD", 1);
        this.kitHotbar.put("BOW", 2);
        this.kitHotbar.put("ARROW", 7);
        this.kitHotbar.put("FLINT_AND_STEEL", 8);
    }
}

