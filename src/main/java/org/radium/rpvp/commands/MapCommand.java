package org.radium.rpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.Map;
import org.radium.rpvp.manager.MapManager;
import org.radium.rpvp.util.TextHelper;

public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;

        Player player = (Player) commandSender;

        if (!player.hasPermission("pvp.map")) return true;

        if (args.length != 2) {
            TextHelper.sendMessage(player,
                    "%prefix% &a/map create <name>\n" +
                            "%prefix% &a/map setpos1/2 <map>\n" +
                            "%prefix% &a/map delete <map>"
            );
            return true;
        }

        MapManager mapManager = Core.getInstance().getMapManager();

        if (args[0].equalsIgnoreCase("create")) {
            String name = args[1];
            if (mapManager.getMapMap().containsKey(name)){
                TextHelper.sendPrefixedMessage(player, "&cMap with that name is already created.");
                return true;
            }
            mapManager.addMap(name, player.getLocation());
            TextHelper.sendPrefixedMessage(player, "&aMap created successfully, set pos1, pos2 & save to finish setup.");
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            String name = args[1];
            if (mapManager.getMapMap().containsKey(name)){
                mapManager.removeMap(name);
                TextHelper.sendPrefixedMessage(player, "&aMap removed successfully!");
                return true;
            }
            TextHelper.sendPrefixedMessage(player, "&cMap not found!");
            return true;
        }

        String mapName = args[1];
        Map map = mapManager.getMap(mapName);

        if (map == null) {
            TextHelper.sendPrefixedMessage(player, "&cMap not found!");
            return true;
        }

        if (args[0].equalsIgnoreCase("setpos1")) {
            map.setPos1Location(player.getLocation());
            TextHelper.sendPrefixedMessage(player, "&aPos-1 set for map " + mapName);
            return true;
        }
        if (args[0].equalsIgnoreCase("setpos2")) {
            map.setPos2Location(player.getLocation());
            TextHelper.sendPrefixedMessage(player, "&aPos-2 set for map " + mapName);
            return true;
        }

        return false;
    }
}
