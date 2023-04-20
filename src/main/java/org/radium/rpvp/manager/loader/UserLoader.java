package org.radium.rpvp.manager.loader;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.Settings;
import org.radium.rpvp.base.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class UserLoader {
    public static void loadFromDatabase() {
        try (Connection connection = Core.getInstance().getDataManager().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + User.TABLE_NAME);
             ResultSet resultSet = statement.executeQuery()) {

            long startTime = System.currentTimeMillis();

            while (resultSet.next()) {
                String uuid = resultSet.getString("UUID");
                String name = resultSet.getString("NAME");
                int kills = resultSet.getInt("KILLS");
                int deaths = resultSet.getInt("DEATHS");
                int points = resultSet.getInt("POINTS");
                int assists = resultSet.getInt("ASSISTS");
                String settings = resultSet.getString("SETTINGS");
                User user = Core.getInstance().getUserManager().addUser(UUID.fromString(uuid), name);
                user.setKills(kills);
                user.setDeaths(deaths);
                user.setPoints(points);
                user.setAssists(assists);
                user.setSettings(convertToObject(settings, Settings.class));
            }

            long timeTaken = System.currentTimeMillis() - startTime;
            int loadedPlayersSize = Core.getInstance().getUserManager().getUserMap().size();
            Core.getInstance().getLogger().log(Level.INFO, "Loaded " + loadedPlayersSize + " users in " + timeTaken + "ms");

        } catch (SQLException e) {
            throw new RuntimeException("Error while loading user ", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save() {
        long startTime = System.currentTimeMillis();
        int saveAmount = 0;
        for (User user : Core.getInstance().getUserManager().getUserMap().values()) {
            saveAmount++;
            user.save();
        }
        long timeTaken = System.currentTimeMillis() - startTime;
        Core.getInstance().getLogger().log(Level.INFO, "Saved " + saveAmount + " users in " + timeTaken + "ms");
    }
    private static <T> T convertToObject(String jsonString, Class<T> valueType) throws JsonProcessingException {
        return Core.getInstance().getObjectMapper().readValue(jsonString, valueType);
    }
}

