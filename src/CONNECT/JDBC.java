package CONNECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String hostName = "localhost:3306";
    private static final String dbName = "covid";
    private static final String username = "root";
    private static final String password = "hieu3082001";

    private static final String connectionURL = "jdbc:mysql://"+hostName+"/"+dbName;

    public static Connection connect(){
        //Tạo đối tượng Connection
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connectionURL, username, password);
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();}

        return conn;
    }
}