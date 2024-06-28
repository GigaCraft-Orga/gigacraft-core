package dev.grafjojo.gigacraftcore.database.postgres;

import dev.grafjojo.gigacraftcore.database.Storage;

public abstract class PostgresStorage implements Storage {

    private final String table;
    private final PostgresDatabase database;

    public PostgresStorage(String table, PostgresDatabase database) {
        this.table = table;
        this.database = database;
        initialize();
    }

    public String getTable() {
        return table;
    }

    public PostgresDatabase getDatabase() {
        return database;
    }
}