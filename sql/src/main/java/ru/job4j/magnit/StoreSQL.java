package ru.job4j.magnit;

import java.io.File;
import java.security.KeyStore;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;

    /**
     * Constructor.
     * @param config config via properties.
     */
    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Creates new DB.
     */
    public void createNewDB() {
        try {
            Class.forName(config.get("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.get("url"),
                    config.get("username"),
                    config.get("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * Creates new table "entry" with field "field".
     * @return true if successfully created
     */
    private boolean createTable() {
        boolean result = false;
        String sql = "CREATE TABLE IF NOT EXISTS entry (field INTEGER NOT NULL);";
        try (PreparedStatement create = this.connection.prepareStatement(sql)) {
            result = create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Selects all rows from table "entry". Needed to check if table is exists.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */
    private int selectAll() {
        int result = 0;
        String select = "SELECT field FROM entry;";
        try (PreparedStatement seletall = this.connection.prepareStatement(select)) {
            result = seletall.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes table "entry".
     */
    private void dropTable() {
        String drop = "DROP TABLE entry";
        try (PreparedStatement droptable = this.connection.prepareStatement(drop)) {
            droptable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method populates table "entry" with values (1, 2, 3, ... n).
     * @param size numbers of rows.
     */
    public void generate(int size) {
        String insert = "INSERT INTO entry (field) VALUES (?);";
        if (!createTable()) {
            if (selectAll() != 1) {
                dropTable();
                createTable();
            }
        }
        try (PreparedStatement populate = this.connection.prepareStatement(insert)) {
            for (int i = 1; i < size + 1; i++) {
                populate.setInt(1, i);
                populate.addBatch();
            }
            populate.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all data form table "entry" and saves it to list.
     * @return list
     */
    public List<Entry> load() {
        List<Entry> result = new LinkedList<>();
        String sql = "SELECT * FROM entry;";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Entry value = new Entry(rs.getInt("field"));
                result.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Config config = new Config();
        StoreXML storeXML = new StoreXML(new File("C:\\sqlite\\db\\magnit.xml"));
        StoreSQL store = new StoreSQL(config);
        store.createNewDB();
        store.generate(5);
        Entries entries = new Entries(store.load());
        storeXML.save(entries);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert();
        SAXParserXML saxParserXML = new SAXParserXML();
        saxParserXML.parser();
    }
}
