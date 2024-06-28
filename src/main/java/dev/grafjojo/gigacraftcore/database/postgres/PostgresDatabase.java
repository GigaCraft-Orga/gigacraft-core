package dev.grafjojo.gigacraftcore.database.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.grafjojo.gigacraftcore.database.Database;
import dev.grafjojo.gigacraftcore.database.DatabaseCredentials;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDatabase implements Database {

    private final DatabaseCredentials credentials;
    private HikariDataSource dataSource;

    public PostgresDatabase(DatabaseCredentials credentials) {
        this.credentials = credentials;
        connect();
    }

    @Override
    public void connect() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", credentials.host(), credentials.port(), credentials.database()));
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername(credentials.username());
        config.setPassword(credentials.password());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    @Override
    public void disconnect() {
        if (dataSource != null) dataSource.close();
    }

    public DatabaseCredentials getCredentials() {
        return credentials;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
