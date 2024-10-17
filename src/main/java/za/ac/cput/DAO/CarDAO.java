package za.ac.cput.DAO;

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

    private Connection con;
    private PreparedStatement pstmt;

    public CarDAO() {

        try {
            this.con = DBConnect.derbyConnection();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex);
        }
   
    }
    public CarVote vote(CarVote votes){
         
        int num;
        String insertsql = "INSERT INTO CarVote VALUES (?,?)";
        try {
            pstmt = this.con.prepareStatement(insertsql);
            pstmt.setString(2, votes.getCarName());
            pstmt.setInt(3, votes.getVote());
            num = pstmt.executeUpdate();

            if (num > 0) {
                return votes;
            } else {
                return null;
            }
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "ERROR1 " + sqlEx);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException sqlEx) {
                JOptionPane.showMessageDialog(null, "ERROR2 " + sqlEx);
            }
        }
        return votes;
    }
    
}
