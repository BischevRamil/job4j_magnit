package ru.job4j.magnit;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ramil Bischev
 * @since 2020-02-27
 * Класс загрузки данных БД в конфигурацию
 */
public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("appSQLite.properties")) {
            values.load(in);
            Class.forName(values.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
