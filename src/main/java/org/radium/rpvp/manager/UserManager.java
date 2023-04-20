package org.radium.rpvp.manager;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.User;
import org.radium.rpvp.model.UserImpl;

import java.util.HashMap;
import java.util.UUID;

public class UserManager implements UserImpl {
    private @Getter final HashMap<UUID, User> userMap = new HashMap<>();
    @Override
    public User addUser(UUID uuid, String playerName) {
        User user = new User(uuid);
        user.setName(playerName);
        userMap.put(uuid, user);
        return user;
    }

    @Override
    public void removeUser(UUID uuid) {
        if (!userMap.containsKey(uuid)) return;
        userMap.remove(uuid);
    }

    @Override
    public User getUser(UUID uuid) {
        return userMap.get(uuid);
    }

    @Override
    public User getUser(String name) {
        Player player = Core.getInstance().getServer().getPlayer(name);
        if (player == null) return null;
        return getUser(player.getUniqueId());
    }
}
