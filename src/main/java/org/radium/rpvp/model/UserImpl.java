package org.radium.rpvp.model;

import org.radium.rpvp.base.user.User;

import java.util.UUID;

public interface UserImpl {
    User addUser(UUID uuid, String playerName);
    void removeUser(UUID uuid);
    User getUser(UUID uuid);
    User getUser(String name);
}
