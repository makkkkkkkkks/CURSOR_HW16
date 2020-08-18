package databaseSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    public static Connection getConnection(DatabaseSelect db) throws SQLException, ClassNotFoundException {
        ResourceBundle resource = ResourceBundle.getBundle(db.toString());
        String driver = resource.getString("db.driver");
        String url = resource.getString("db.url");
        String dbName = resource.getString("db.name");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        Class.forName(driver);
        return DriverManager.getConnection(url + dbName, user, pass);
    }

}
