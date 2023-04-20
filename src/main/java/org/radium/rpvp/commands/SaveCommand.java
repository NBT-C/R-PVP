package org.radium.rpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.Settings;
import org.radium.rpvp.base.user.User;
import org.radium.rpvp.util.TextHelper;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.Map;

public class SaveCommand implements CommandExecutor {

    private final Material[] REQUIRED_ITEMS = {
            Material.FISHING_ROD,
            Material.IRON_SWORD,
            Material.BOW,
            Material.ARROW,
            Material.FLINT_AND_STEEL
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        ItemStack[] inventoryContents = player.getInventory().getContents();
        boolean hasAllItems = true;
        Map<String, Integer> itemSlots = new HashMap<>();

        for (Material requiredItem : REQUIRED_ITEMS) {
            boolean foundItem = false;
            for (int j = 0; j < 9; j++) {
                ItemStack item = inventoryContents[j];
                if (item != null && item.getType() == requiredItem) {
                    itemSlots.put(requiredItem.name(), j);
                    foundItem = true;
                    break;
                }
            }
            if (!foundItem) {
                hasAllItems = false;
                break;
            }
        }

        if (!hasAllItems) {
            TextHelper.sendPrefixedMessage(player, "&cThere's an item not found or not in slot 0-8.");
            return true;
        }


        User user = Core.getInstance().getUserManager().getUser(player.getUniqueId());
        user.getSettings().getKitHotbar().putAll(itemSlots);

        TextHelper.sendPrefixedMessage(player, "&aLoadout saved successfully!");
        player.playSound(player.getLocation(), Sound.ANVIL_USE, 2L, 2L);
        return true;
    }
}
