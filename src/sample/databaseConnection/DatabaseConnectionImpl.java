package sample.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*

This class are made for connection to Database

*/
public class DatabaseConnectionImpl implements DatabaseConnection {

    @Override
    public Connection createConnection()  {
        Connection connection = null;

        String url = "jdbc:mysql://127.0.0.1:3306/coursework?serverTimezone=UTC";
        String username = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection createStaticConnection(){
        DatabaseConnectionImpl databaseConnection = new DatabaseConnectionImpl();
        return databaseConnection.createConnection();
    }

}
