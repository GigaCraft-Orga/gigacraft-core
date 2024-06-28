package dev.grafjojo.gigacraftcore.database;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRegistry {

    private static final List<Storage> STORAGES = new ArrayList<>();

    public static void registerStorage(Storage storage) {
        STORAGES.add(storage);
    }

    public static void unregisterStorage(Storage storage) {
        STORAGES.remove(storage);
    }

    public static <T extends Storage> T getStorage(Class<T> clazz) {
        return STORAGES.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }

    public static List<Storage> getStorages() {
        return STORAGES;
    }
}