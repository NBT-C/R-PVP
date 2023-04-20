package org.radium.rpvp.manager.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.Configuration;
import org.radium.rpvp.Core;
import org.radium.rpvp.base.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DataManager {
    private final HikariConfig dataSourceConfig = new HikariConfig();
    private HikariDataSource dataSource;

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    public boolean connect() {
        final boolean isConnected = initDatasource();
        if (isConnected) {
            checkTable();
        }
        return isConnected;
    }
    private void checkTable() {
        String query = "CREATE TABLE IF NOT EXISTS "+ User.TABLE_NAME +"(" +
                "`UUID` varchar(255) NOT NULL," +
                "`NAME` varchar(20) NOT NULL," +
                "`KILLS` INT DEFAULT 0," +
                "`DEATHS` INT DEFAULT 0," +
                "`POINTS` INT DEFAULT 0," +
                "`ASSISTS` INT DEFAULT 0," +
                "`SETTINGS` TEXT" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.addBatch(query);
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean initDatasource() {
        Configuration config = Core.getInstance().getDatabaseData().getConfig();
        try {
            final String driver = "jdbc:mysql://";
            final String host = config.getString("Database.Host");
            final String username = config.getString("Database.Username");
            final String database = config.getString("Database.Database");
            final String databaseProperties = config.getString("Database.DatabaseProperties");
            final String password = config.getString("Database.Password");
            final int port = config.getInt("Database.Port");
            final int maximumPoolSize = config.getInt("Database.MaximumPoolSize");
            dataSourceConfig.setPoolName("Guilds");
            dataSourceConfig.setMaximumPoolSize(maximumPoolSize);
            dataSourceConfig.setIdleTimeout(0);
            dataSourceConfig.setUsername(username);
            dataSourceConfig.setPassword(password);
            dataSourceConfig.setJdbcUrl(driver + host + ":" + port + "/" + database + databaseProperties);
            dataSource = new HikariDataSource(this.dataSourceConfig);
            Core.getInstance().getLogger().log(Level.FINE, "Connected to database!");
            return true;
        } catch (final Exception exception) {
            Core.getInstance().getLogger().log(Level.INFO, "Err");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean isClosed() {
        return this.dataSource == null || !this.dataSource.isRunning() || this.dataSource.isClosed();
    }
    public void disconnect() {
        if (this.isClosed()) {
            Core.getInstance().getLogger().log(Level.SEVERE, "Database is already closed!");
        }
        this.dataSource.close();
        Core.getInstance().getLogger().log(Level.FINE, "Successfully disconnected from the database!");
    }
}
