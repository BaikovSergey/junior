package ru.job4j.tracker;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Constructor.
     */
    public TrackerSQL() {
        init();
    }

    /**
     * Method establishes connection to DB.
     * @return true/false
     */
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

    /**
     * Method add new item to DB.
     * @param item new Item object.
     * @return new added item.
     */
    @Override
    public Item add(Item item) {
        String sql_insert = "INSERT INTO item(item_id, item_name, item_desc) VALUES (?,?,?);";
        int id = Integer.parseInt(item.getId());
        String name = item.getName();
        String desc = item.getDesc();
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

    /**
     * Method replaces pointed item with new item.
     * @param id id of the item to be replaced.
     * @param item new item.
     * @return true/false.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        String sql_insert = "UPDATE item SET item_id = ?, item_name = ?, item_desc = ? WHERE item_id = ?;";
        int item_id = Integer.parseInt(item.getId());
        String item_name = item.getName();
        String item_desc = item.getDesc();
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql_insert)) {
            pstmt.setInt(1, item_id);
            pstmt.setString(2, item_name);
            pstmt.setString(3, item_desc);
            pstmt.setInt(4, Integer.parseInt(id));
            int sql_result = pstmt.executeUpdate();
            if (sql_result != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method delete pointed item
     * @param id id of the item to be removed
     * @return true/false.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        String sql_insert = "DELETE FROM item WHERE item_id = ?;";
        int item_id = Integer.parseInt(id);
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql_insert)) {
            pstmt.setInt(1, item_id);
            int sql_result = pstmt.executeUpdate();
            if (sql_result != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method finds all items.
     * @return List of items.
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        String sql_insert = "SELECT * FROM item;";
        try (Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql_insert)) {
            while (resultSet.next()) {
                result.add(createNewItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method finds all items which names matches key.
     * @param key key.
     * @return List of items.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        String sql_insert = "SELECT * FROM item WHERE item_name = ?;";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql_insert);
        ResultSet resultSet = pstmt.executeQuery(sql_insert)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
            while (resultSet.next()) {
                result.add(createNewItem(resultSet));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method finds item which  matches the id.
     * @param id id.
     * @return item.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        String sql_insert = "SELECT * FROM item WHERE item_id = ?;";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql_insert);
             ResultSet resultSet = pstmt.executeQuery(sql_insert)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            result = createNewItem(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Method creates new Item object from resultSet.
     * @param resultSet resultSet.
     * @return Item object.
     */
    private Item createNewItem(ResultSet resultSet) {
        Item result = null;
        try {
            String item_id = resultSet.getString("item_id");
            String item_name = resultSet.getString("item_name");
            String item_desc = resultSet.getString("item_desc");
            result = new Item(item_id, item_name, item_desc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
