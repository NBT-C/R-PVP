package org.radium.rpvp.manager.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.Settings;
import org.radium.rpvp.base.user.User;

public class KitManager {
    private void selectKit(Player player, KitType kitType) {
        player.getInventory().clear();
        User user = Core.getInstance().getUserManager().getUser(player.getUniqueId());
        user.getSettings().setSelectedKit(kitType.name());
        Settings userSettings = user.getSettings();
        player.getInventory().setArmorContents(kitType.getArmour());
        for (ItemStack tool : kitType.getTools()) {
            player.getInventory().setItem(userSettings.getKitHotbar().get(tool.getType().name()), tool);
        }
    }

    public void checkKit(Player player) {
        for (KitType kitType : KitType.values()) {
            User user = Core.getInstance().getUserManager().getUser(player.getUniqueId());
            System.out.println(kitType.name());
            if (user.getSettings().getSelectedKit().equals(kitType.name())) {
                if (!player.hasPermission("rank." + kitType.name().toLowerCase())) {
                    selectKit(player, KitType.DEFAULT);
                    break;
                }
            } else {
                selectKit(player, getClosestKit(player));
                break;
            }
        }
    }

    public KitType getClosestKit(Player player) {
        for (KitType kitType : KitType.values()) {
            if (player.hasPermission("rank." + kitType.name().toLowerCase())) {
                return kitType;
            }
        }
        return KitType.DEFAULT;
    }
}
