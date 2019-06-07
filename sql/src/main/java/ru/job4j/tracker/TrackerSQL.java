package ru.job4j.tracker;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        String sql_insert = "INSERT INTO item(item_id, item_name, item_desc) VALUES (?,?,?);";
        int id = Integer.parseInt(item.getId());
        String name = item.getName();
        String desc = item.getDesc();
        init();
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql_insert)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, desc);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        return result;
    }

    @Override
    public Item findById(String id) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {
        TrackerSQL trackerSQL = new TrackerSQL();
        trackerSQL.add(new Item("12","testName2", "testDesc2"));
    }
}
