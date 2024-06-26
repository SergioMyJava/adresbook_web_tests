package manager;

import model.GroupData;
import model.UserData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbsHelper extends HelperBase {
    //Connection conn;

    public JdbsHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public List<GroupData> getGroupeList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
            var result = statement.executeQuery(
                    "SELECT group_id,group_name,group_header,group_footer FROM group_list"))
            {
           while (result.next()) {
               groups.add(new GroupData()
                       .withId(result.getString("group_id"))
                       .withName(result.getString("group_name"))
                       .withHeader(result.getString("group_header"))
                       .withFooter(result.getString("group_footer")));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<UserData> getUserList() {
        var users = new ArrayList<UserData>();
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();) {
            var result = statement.executeQuery(
                    "SELECT id,firstname,lastname FROM addressbook");
            while (result.next()) {
                users.add(new UserData()
                        .withId(result.getString("id"))
                        .withFirstnameLastname(result.getString("firstname"),result.getString("lastname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
        {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private Connection getConnection() throws SQLException {
//        return conn = DriverManager.getConnection(
//                "jdbs:mysql://http://localhost/addressbook","root","");
//    }

    public int getIdByNameGroup(String groupName){
        int group_id=0;
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM group_list where group_name='"+groupName+"'")
        )

        {
            if (result.next()) {
                group_id = result.getInt("group_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group_id;
    }
    public int getIdByNameUser(String userName){
        int user_id=0;
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM addressbook where firstname='"+userName+"'")
        )

        {
            if (result.next()) {
                user_id = result.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user_id;
    }

    public boolean checkUserInGroupe(int user_id, int group_id){
        //int group_id=0;
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT *\n" +
                             "FROM address_in_groups\n" +
                             "WHERE id='" + user_id + "'" + " AND group_id='" + group_id + "'")
        )

        {
            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

}
