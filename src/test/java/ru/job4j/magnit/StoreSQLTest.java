package ru.job4j.magnit;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    private Config config = new Config();
    private StoreSQL storeSQL = new StoreSQL(config);

    @Test
    public void testConnection() {
        assertThat(this.storeSQL.connectToDB(), is(true));
    }

    @Test
    public void whenReadRecordsFromTableThenHaveList() {
        this.storeSQL.generate(100);
        List<Entry> list = this.storeSQL.load();
        assertThat(list.size(), is(100));
    }
}
