package xyz.oskarnilsson;

import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class DBConnect {
    String dbURL = "jdbc:mysql://localhost:3306/vanstopdb";
    String username = "root";
    String password = "r1F7$M4aU3&i";

    public void DBConnectNewStop(String newName, String newLat, String newLong, String newNation) { //Adds a stop
        int newNationID = DBConnectNationID(newNation);
        newNation = newNation.substring(0, 1).toUpperCase() + newNation.substring(1).toLowerCase();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            PreparedStatement statement = conn.prepareStatement("call vanstopdb.new_stop(?, ?, ?, ?)");
            statement.setString(1, newName);
            statement.setString(2, newLat);
            statement.setString(3, newLong);
            statement.setInt(4, newNationID);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New stop added");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public DefaultTableModel DBConnectAllview() { //fetches data from the allView and passes it on as a model
        DefaultTableModel model = new DefaultTableModel(new String[]{"name", "lat", "long", "nation", "eu", "overnight friendly", "tags"}, 0);

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            String sql = "SELECT * FROM allview";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while (result.next()) {
                String name = result.getString(1);
                String lat = result.getString(2);
                String longi = result.getString(3);
                String nation = result.getString(4);
                Boolean eu = result.getBoolean(5);
                Boolean overnight = result.getBoolean(6);
                String tags = result.getString(7);

                model.addRow(new Object[]{name, lat, longi, nation, eu, overnight, tags});

                //Console test
                //String output = "Stop: %s - %s - %s - %s - %s - %s - %s";
                //System.out.println(String.format(output, name, lat, longi, nation, eu, overnight, tags));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

    public int DBConnectNationID(String nationName) { //Gets nation ID from a name, might be redundant with a propper stored procedure
        int nationID = 0;
        nationName = nationName.substring(0, 1).toUpperCase() + nationName.substring(1).toLowerCase();
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            PreparedStatement statement = conn.prepareStatement("SELECT nations_id FROM vanstopdb.nations WHERE name = ?");
            statement.setString(1, nationName);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                nationID = result.getInt(1);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nationID;
    }

    public void DBConnectUpdate(String newName, String newLat, String newLong, String newNation, String oldName) { //Updates an existing stop.
        int newNationID = DBConnectNationID(newNation);
        newNation = newNation.substring(0, 1).toUpperCase() + newNation.substring(1).toLowerCase();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "call vanstopdb.update_stop(?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, newLat);
            statement.setString(3, newLong);
            statement.setInt(4, newNationID);
            statement.setString(5, oldName);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void DBConnectDelete(String stopToDelete) { //Removes a stop


        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "DELETE FROM stops WHERE name=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, stopToDelete);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Stop removed");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public DefaultTableModel DBConnectAllviewNameSearch(String nameSearch) {  //Fetches from the allview view with a name search string
        DefaultTableModel model = new DefaultTableModel(new String[]{"name", "lat", "long", "nation", "eu", "overnight friendly", "tags"}, 0);

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM allview WHERE name REGEXP ?");
            statement.setString(1, nameSearch);
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                String name = result.getString(1);
                String lat = result.getString(2);
                String longi = result.getString(3);
                String nation = result.getString(4);
                Boolean eu = result.getBoolean(5);
                Boolean overnight = result.getBoolean(6);
                String tags = result.getString(7);

                model.addRow(new Object[]{name, lat, longi, nation, eu, overnight, tags});


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

    public DefaultTableModel DBConnectAllviewNationSearch(String nationSearch) { //Fetches from the allview view with a nations search string
        DefaultTableModel model = new DefaultTableModel(new String[]{"name", "lat", "long", "nation", "eu", "overnight friendly", "tags"}, 0);

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM allview WHERE nation REGEXP ?");
            statement.setString(1, nationSearch);
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                String name = result.getString(1);
                String lat = result.getString(2);
                String longi = result.getString(3);
                String nation = result.getString(4);
                Boolean eu = result.getBoolean(5);
                Boolean overnight = result.getBoolean(6);
                String tags = result.getString(7);

                model.addRow(new Object[]{name, lat, longi, nation, eu, overnight, tags});


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

    public DefaultTableModel DBConnectTagTable() { //Fetches the Tag names to be used in the addtag window
        DefaultTableModel model = new DefaultTableModel(new String[]{"tag"}, 0);

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {


            String sql = "SELECT name FROM tags";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while (result.next()) {
                String tag = result.getString(1);


                model.addRow(new Object[]{tag});

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }

    public void DBConnectAddTag(String stop, String tag) { //Adds the selected tag
        try (

                Connection conn = DriverManager.getConnection(dbURL, username, password);

                CallableStatement statement = conn.prepareCall("{call add_tag(?, ?)}");
        ) {

            statement.setString(1, stop);
            statement.setString(2, tag);

            statement.execute();
            statement.close();

            System.out.println("Added tag");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void DBConnectRemoveTag(String stop, String tag) { //Removes the selected tag
        try (

                Connection conn = DriverManager.getConnection(dbURL, username, password);

                CallableStatement statement = conn.prepareCall("{call remove_tag(?, ?)}");
        ) {

            statement.setString(1, stop);
            statement.setString(2, tag);

            statement.execute();
            statement.close();

            System.out.println("Removed tag");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
