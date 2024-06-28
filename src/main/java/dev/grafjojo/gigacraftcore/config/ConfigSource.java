package dev.grafjojo.gigacraftcore.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigSource {

    private final File file;
    private final FileConfiguration config;

    public ConfigSource(File file, String path) {
        this.file = new File(path,file + ".yml");
        this.config = new YamlConfiguration();
    }

    public void set(String path, Object value) {
        config.set(path, value);
    }

    public Object get(String path) {
        return config.get(path);
    }

    public void comment(String path, List<String> comment) {
        config.setComments(path, comment);
    }

    public boolean contains(String path) {
        return !config.contains(path);
    }

    public void load() {
        if (!file.exists()) {

            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
