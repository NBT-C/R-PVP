package org.radium.rpvp.base.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.radium.rpvp.Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@Data
public class User {
    public static final String TABLE_NAME = "pvp_users";
    private final UUID uuid;
    private Settings settings;
    private String name;
    private int kills;
    private int deaths;
    private int points;
    private int streak;
    private int assists;
    public User(UUID uuid){
        this.uuid = uuid;
        this.settings = new Settings();
        loadName();
    }
    private void loadName(){ //That will help when the player's name have been changed
        Player player = Core.getInstance().getServer().getPlayer(uuid);
        if (player == null) {
            this.name = "NONE";
            return;
        }
        this.name = player.getName();
    }
    public void save() {
        try (Connection connection = Core.getInstance().getDataManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE uuid=?")) {
            preparedStatement.setString(1, uuid + "");
            try (ResultSet resultSet = preparedStatement.executeQuery();
                 PreparedStatement ps = connection.prepareStatement(resultSet.next() ? "" +
                         handleReplace("UPDATE " + TABLE_NAME + " SET NAME='{name}', KILLS={kills}, POINTS={points}, ASSISTS={assists}, DEATHS={deaths}, SETTINGS='{settings}' WHERE UUID='{uuid}'") :
                         handleReplace("INSERT INTO " + TABLE_NAME + " (UUID, NAME, KILLS, POINTS, ASSISTS, DEATHS, SETTINGS) VALUES ('{uuid}', '{name}', {kills}, {points}, {assists}, {deaths}, '{settings}')"))) {
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String handleReplace(String s) {
        return s
                .replace("{uuid}", uuid + "")
                .replace("{name}", name)
                .replace("{kills}", kills+"")
                .replace("{points}", points+"")
                .replace("{assists}", assists+"")
                .replace("{deaths}", deaths + "")
                .replace("{settings}", convertToJson(settings) == null ? convertToJson(new Settings()) : convertToJson(settings));
    }
    private String convertToJson(Object object) {
        try {
            return Core.getInstance().getObjectMapper().writeValueAsString(object);
        }catch (JsonProcessingException e){e.printStackTrace();}
        return "{}";
    }
}
