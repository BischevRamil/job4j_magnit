package ru.job4j.magnit;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ramil Bischev
 * @since 2020-02-27
 * Класс работы с базой данных
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;
    private static final Random RN = new Random();
    final static Logger LOGGER = Logger.getLogger(ru.job4j.magnit.UsageLog4j.class);

    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Соединение с базой данных создание таблицы entries.
     *
     * @return true если соединение успешно.
     */
    public boolean connectToDB() {
        this.config.init();
        try {
            this.connect = DriverManager.getConnection(this.config.get("url"));
            connect.setAutoCommit(false);
            Statement st = connect.createStatement();
            st.execute("create table if not exists entries (entry integer);");
            this.clearTable("entries");
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        return this.connect != null;
    }

    /**
     * Генерируется size целочисленных значений и вносятся в БД.
     * @param size количество значений.
     */
    public void generate(int size) {
        if (connectToDB()) {
            try (PreparedStatement statement = connect.prepareStatement(SQLItems.INSERT.query)) {
                for (int i = 1; i <= size; i++) {
                    Entry entry = new Entry();
                    entry.setEntry(generateInt());
                    statement.setInt(1, entry.getEntry());
                    statement.addBatch();
                }
                statement.executeBatch();
                connect.commit();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * Выгрузка значений из БД в List.
     * @return List.
     */
    public List<Entry> load() {
        List<Entry> listResult = new ArrayList<>();
        try (PreparedStatement st = connect.prepareStatement(SQLItems.GETALL.query)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Entry entry = new Entry();
                entry.setEntry(resultSet.getInt("entry"));
                listResult.add(entry);
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return listResult;
    }

    @Override
    public void close() throws Exception {
        connect.close();
    }

     /** Удаление таблицы
      * @param tableName имя таблицы
      */
    public void clearTable(String tableName) {
        try (Statement statement = connect.createStatement()) {
            statement.execute("DELETE FROM " + tableName + ";");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    enum SQLItems {
        GETALL("SELECT * FROM entries;"),
        INSERT("INSERT INTO entries (entry) VALUES (?);");

        String query;

        SQLItems(String query) {
            this.query = query;
        }
    }

    private int generateInt() {
        return RN.nextInt(100);
    }
}
