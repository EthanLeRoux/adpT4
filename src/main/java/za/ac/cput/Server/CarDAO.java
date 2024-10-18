package za.ac.cput.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import za.ac.cput.Conn.DBConnect;
import za.ac.cput.Domain.CarVote;

/**
 *
 * @author Esihle
 */
public class CarDAO {
    private DBConnect dbcon;
    private Connection con;
    private PreparedStatement pstmt;

    public CarDAO() {

        try {
            dbcon = new DBConnect();
            this.con = dbcon.derbyConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
   
    }
    
    void voteForExistingCar(CarVote cv) throws SQLException{
       String command = "UPDATE carvotes SET votes = votes+1 WHERE carname = ?";
       dbcon = new DBConnect();
       con = dbcon.derbyConnection();
       pstmt = this.con.prepareStatement(command);
       pstmt.setString(1, cv.getCarName());
       pstmt.executeUpdate();
    }
    
    void addCar(CarVote cv) throws SQLException{
       String command = "INSERT INTO carvotes (carname, votes) VALUES (?,?)";
       dbcon = new DBConnect();
       con = dbcon.derbyConnection();
       pstmt = this.con.prepareStatement(command);
       pstmt.setString(1, cv.getCarName());
       pstmt.setInt(2, 0);
       pstmt.executeUpdate();
    }
    
}
