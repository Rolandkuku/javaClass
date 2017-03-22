
import java.sql.*;
import java.util.ArrayList;

public class UserManager {

    private Connection conn = null;
    private Statement stmt = null;

    public  UserManager ()
            throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        this.conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection("jdbc:mysql://192.168.10.10/javatest?" + "user=javaTest&password=secret");
            this.createTableIfNotExist();
        }  catch (ClassNotFoundException e) {
            System.err.println("Driver non chargé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL !");
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void createTableIfNotExist () throws SQLException {
        this.createStatement();
        String strQuery = "SHOW TABLES;";
        ResultSet rsTables = this.stmt.executeQuery(strQuery);
        Boolean tableExist = false;
        try {
            while(rsTables.next()) {
                if (rsTables.getString(1).compareTo("tp3_users") == 0) {
                    tableExist = true;
                }
            }
            if (!tableExist) {
                String createTableQuery = "CREATE TABLE tp3_users (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "pwd VARCHAR(255)" +
                        ");";
                this.stmt.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeStatement();
    }

    public void createUser(String name, String pwd) throws SQLException {
        this.createStatement();
        String createUserQuery = "INSERT INTO tp3_users" +
                "(name, pwd)" +
                " VALUES ('" + name + "', '" + pwd + "');";
        try {
            this.stmt.executeUpdate(createUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeStatement();
    }

    public ArrayList getUserList() throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        try {
            this.createStatement();
            String listUserQuery = "SELECT * FROM tp3_users;";
            ResultSet userList = this.stmt.executeQuery(listUserQuery);
            while(userList.next()) {
                users.add(
                        "id: " + userList.getString("id")
                                + " name: " + userList.getString("name")
                                + " pwd: " + userList.getString("pwd")
                                + "\n"
                );
            }
            this.closeStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String name) throws SQLException {
        this.createStatement();
        String userQuery = String.format("SELECT * from tp3_users WHERE name = '%s'", name);
        ResultSet sqlUser = this.stmt.executeQuery(userQuery);
        User user = new User();
        if (sqlUser.next()) {
            user.setName(sqlUser.getString("name"));
            user.setPwd(sqlUser.getString("pwd"));
        }
        return user;
    }

    private void closeStatement() {
        try {
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createStatement() {
        try {
            this.stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
