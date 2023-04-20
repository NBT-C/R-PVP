package org.radium.rpvp.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class TextHelper {
    public static String format(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static void sendMessage(Player player, String text){
        player.sendMessage(format(text.replace("%prefix%", "&8┃ &c&lPvP &8&l➟")));
    }
    public static void sendPrefixedMessage(Player player, String text){
        player.sendMessage(format("&8┃ &c&lPvP &8&l➟ " + text));
    }
}
