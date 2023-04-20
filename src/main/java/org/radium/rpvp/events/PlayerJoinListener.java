package org.radium.rpvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.User;
import org.radium.rpvp.manager.UserManager;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        UserManager userManager = Core.getInstance().getUserManager();
        Player player = event.getPlayer();

        if(!userManager.getUserMap().containsKey(player.getUniqueId())){
            User user = userManager.addUser(player.getUniqueId(), player.getName());
            user.getSettings().setSelectedKit(Core.getInstance().getKitManager().getClosestKit(player).name());
        }

        Core.getInstance().getKitManager().checkKit(player);

    }
}
