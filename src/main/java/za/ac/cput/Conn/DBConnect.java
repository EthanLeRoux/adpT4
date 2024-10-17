
package za.ac.cput.Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Esihle
 */
public class DBConnect {
    
     public static Connection derbyConnection()throws SQLException{
        String db_URL = "jdbc:derby://localhost:1527/CarVote";
        String Username = "administrator";
        String Password = "admin";
        return DriverManager.getConnection(db_URL,Username,Password);
    }
}
