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

    public TrackerSQL(Connection connection) {
        this.connection = connection;
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
        String sqlInsert = "INSERT INTO item(item_id, item_name, item_desc) VALUES (?,?,?);";
        int id = Integer.parseInt(item.getId());
        String name = item.getName();
        String desc = item.getDesc();
        try (PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert)) {
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
        String sqlInsert = "UPDATE item SET item_id = ?, item_name = ?, item_desc = ? WHERE item_id = ?;";
        int itemId = Integer.parseInt(item.getId());
        String itemName = item.getName();
        String itemDesc = item.getDesc();
        try (PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert)) {
            pstmt.setInt(1, itemId);
            pstmt.setString(2, itemName);
            pstmt.setString(3, itemDesc);
            pstmt.setInt(4, Integer.parseInt(id));
            int sqlResult = pstmt.executeUpdate();
            if (sqlResult != 0) {
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
        String sqlInsert = "DELETE FROM item WHERE item_id = ?;";
        int itemId = Integer.parseInt(id);
        try (PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert)) {
            pstmt.setInt(1, itemId);
            int sqlResult = pstmt.executeUpdate();
            if (sqlResult != 0) {
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
        String sqlInsert = "SELECT * FROM item;";
        try (Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlInsert)) {
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
        String sqlInsert = "SELECT * FROM item WHERE item_name = ?;";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert);
        ResultSet resultSet = pstmt.executeQuery(sqlInsert)) {
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
        String sqlInsert = "SELECT * FROM item WHERE item_id = ?;";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sqlInsert);
             ResultSet resultSet = pstmt.executeQuery(sqlInsert)) {
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
            String itemId = resultSet.getString("item_id");
            String itemName = resultSet.getString("item_name");
            String itemDesc = resultSet.getString("item_desc");
            result = new Item(itemId, itemName, itemDesc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
