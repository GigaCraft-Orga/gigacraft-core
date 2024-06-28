package dev.grafjojo.gigacraftcore.config;

import dev.grafjojo.gigacraftcore.config.annotations.ConfigValue;
import dev.grafjojo.gigacraftcore.config.annotations.YamlConfig;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public class ConfigFactory {

    private final String path;

    public ConfigFactory(String path) {
        this.path = path;
    }

    public void loadConfig(Class<? extends GigaConfig> clazz) {
        ConfigSource source = new ConfigSource(new File(clazz.getDeclaredAnnotation(YamlConfig.class).name()), path);
        source.load();

        getConfigFields(clazz).forEach(field -> {
            ConfigValue value = field.getDeclaredAnnotation(ConfigValue.class);
            field.setAccessible(true);

            if (source.contains(value.key())) {
                try {
                    source.set(value.key(), field.get(null));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        source.save();
        loadData(clazz, source);
    }

    public void update(Class<? extends GigaConfig> clazz) {
        ConfigSource source = new ConfigSource(new File(clazz.getDeclaredAnnotation(YamlConfig.class).name()), path);

        getConfigFields(clazz).forEach(field -> {
            ConfigValue value = field.getDeclaredAnnotation(ConfigValue.class);
            field.setAccessible(true);

            try {
                source.set(value.key(), field.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        source.save();
    }

    public void reloadConfig(Class<? extends GigaConfig> clazz) {
        ConfigSource source = new ConfigSource(new File(clazz.getDeclaredAnnotation(YamlConfig.class).name()), path);
        source.load();
        loadData(clazz, source);
    }

    public Stream<Field> getConfigFields(Class<? extends GigaConfig> clazz) {
       return Arrays.stream(clazz.getDeclaredFields())
               .filter(field -> field.isAnnotationPresent(ConfigValue.class));
    }

    private void loadData(Class<? extends GigaConfig> clazz, ConfigSource source) {
        getConfigFields(clazz).forEach(field -> {
            ConfigValue value = field.getDeclaredAnnotation(ConfigValue.class);
            field.setAccessible(true);

            if (source.get(value.key()) != null) {
                try {
                    field.set(null, source.get(value.key()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
