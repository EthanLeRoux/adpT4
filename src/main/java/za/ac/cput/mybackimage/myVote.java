
package za.ac.cput.mybackimage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import za.ac.cput.DAO.CarDAO;
import za.ac.cput.Domain.CarVote;
/**
 *
 * @author Esihle Mlinjana (222441712)
 */
public class myVote extends JFrame{
    
    private JPanel pnlFront = new JPanel() {
        private Image backImage = new ImageIcon("FM.png").getImage();
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JPanel pnlTable = new JPanel();
    private JLabel lblCar = new JLabel("CAR OF THE YEAR");
    private JLabel lblCars = new JLabel("Luxury Cars");
    private JLabel lblAdd = new JLabel("Add A Car");
    private JTextField txtAdd = new JTextField();
    private String strCars []={"Select:","Rolls Royce Ghost", "BMW i7 xDrive60", "Mercedis AMG G63S", "Bentley Continental GT", "Audi Q8"}; 
    private JComboBox cbCars = new JComboBox(strCars);
    private DefaultTableModel tblModel = new DefaultTableModel();
    private JTable tblCar = new JTable(tblModel);
    private JButton btnAdd = new JButton("Add");
    private JButton btnVote = new JButton("Vote");
    private JButton btnView = new JButton("View");
    private JButton btnExit = new JButton("Exit");
    
    CarDAO dao = new CarDAO();
    CarVote car;
    
    public myVote(){
        super("Luxury Vote");
        
        lblCar.setBounds(400,50,300,200);
        lblCar.setFont(new Font("Serif", Font.BOLD, 30));
        lblCar.setForeground(Color.WHITE);
        
        lblAdd.setBounds(600,100,300,200);
        lblAdd.setFont(new Font("Serif",Font.BOLD, 20));
        lblAdd.setForeground(Color.WHITE);  
        
        lblCars.setBounds(380,100,300,200);
        lblCars.setFont(new Font("Serif",Font.BOLD, 20));
        lblCars.setForeground(Color.WHITE);
        
        txtAdd.setBackground(Color.WHITE);
        txtAdd.setBounds(580,230,150,30);
        //txtAdd.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        cbCars.setBackground(Color.WHITE);
        cbCars.setBounds(360,230,150,35);
        
        pnlTable.setBounds(300, 300, 500, 250);
        tblModel.addColumn("ID");   
        tblModel.addColumn("Car Name");  
        tblModel.addColumn("Vote");  
        
        tblCar.setBounds(300, 300, 500, 250);
        tblCar.setForeground(Color.BLACK);
        tblCar.setBackground(Color.WHITE);
        
        
        btnAdd.setBounds(320, 600, 100, 30);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Serif",Font.BOLD,25));
        
        btnVote.setBounds(440, 600, 100, 30);
        btnVote.setBackground(Color.BLACK);
        btnVote.setForeground(Color.WHITE);
        btnVote.setFont(new Font("Serif",Font.BOLD,25));
        
        btnView.setBounds(560, 600, 100, 30);
        btnView.setBackground(Color.BLACK);
        btnView.setForeground(Color.WHITE);
        btnView.setFont(new Font("Serif",Font.BOLD,25));
        
        btnExit.setBounds(680, 600, 100, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Serif",Font.BOLD,25));
        
        add(btnAdd);
        add(btnVote);
        add(btnView);
        add(btnExit);
        add(cbCars);
        add(txtAdd);
        add(lblCars);
        add(lblAdd);
        add(lblCar);
        add(pnlTable);
        pnlTable.add(new JScrollPane(tblCar));
        add(pnlFront);
        
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    
                }
            
            
        });
    }
    
}   

