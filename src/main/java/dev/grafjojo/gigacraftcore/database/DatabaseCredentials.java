package dev.grafjojo.gigacraftcore.database;

public record DatabaseCredentials(
        String host,
        int port,
        String database,
        String username,
        String password
) {}