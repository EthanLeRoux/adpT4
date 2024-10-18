package za.ac.cput.mybackimage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.table.*;
import za.ac.cput.Server.CarDAO;
import za.ac.cput.Domain.CarVote;

/**
 *
 * @author Esihle Mlinjana (222441712)
 */
public class VoteScreen extends JFrame {
    ClientSide cs;
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
    private String strCars[] = {"Select:", "Rolls Royce Ghost", "BMW i7 xDrive60", "Mercedis AMG G63S", "Bentley Continental GT", "Audi Q8"};
    private JComboBox cbCars = new JComboBox(strCars);
    private DefaultTableModel tblModel = new DefaultTableModel();
    private JTable tblCar = new JTable(tblModel);
    private JButton btnAdd = new JButton("Add");
    private JButton btnVote = new JButton("Vote");
    private JButton btnView = new JButton("View");
    private JButton btnExit = new JButton("Exit");

    CarDAO dao = new CarDAO();
    CarVote car;

    public VoteScreen() {
        super("Luxury Vote");
        cs = new ClientSide();
        cs.getStreams();
        
        lblCar.setBounds(400, 50, 300, 200);
        lblCar.setFont(new Font("Serif", Font.BOLD, 30));
        lblCar.setForeground(Color.WHITE);

        lblAdd.setBounds(600, 100, 300, 200);
        lblAdd.setFont(new Font("Serif", Font.BOLD, 20));
        lblAdd.setForeground(Color.WHITE);

        lblCars.setBounds(380, 100, 300, 200);
        lblCars.setFont(new Font("Serif", Font.BOLD, 20));
        lblCars.setForeground(Color.WHITE);

        txtAdd.setBackground(Color.WHITE);
        txtAdd.setBounds(580, 230, 150, 30);
        //txtAdd.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        cbCars.setBackground(Color.WHITE);
        cbCars.setBounds(360, 230, 150, 35);

        pnlTable.setBounds(300, 300, 500, 250);
        tblModel.addColumn("ID");
        tblModel.addColumn("Car Name");
        tblModel.addColumn("Vote");

        tblCar.setBounds(300, 300, 500, 250);
        tblCar.setForeground(Color.BLACK);
        tblCar.setBackground(Color.WHITE);

        btnAdd.setBounds(320, 600, 100, 30);
        btnAdd.setBackground(Color.BLACK);
//        btnAdd.setOpaque(true);
//        btnAdd.setBorderPainted(false);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Serif", Font.BOLD, 25));

        btnVote.setBounds(440, 600, 100, 30);
        btnVote.setBackground(Color.BLACK);
//        btnVote.setOpaque(true);
//        btnVote.setBorderPainted(false);
        btnVote.setForeground(Color.WHITE);
        btnVote.setFont(new Font("Serif", Font.BOLD, 25));

        btnView.setBounds(560, 600, 100, 30);
        btnView.setBackground(Color.BLACK);
//        btnView.setOpaque(true);
//        btnView.setBorderPainted(false);
        btnView.setForeground(Color.WHITE);
        btnView.setFont(new Font("Serif", Font.BOLD, 25));

        btnExit.setBounds(680, 600, 100, 30);
        btnExit.setBackground(Color.BLACK);
//        btnExit.setOpaque(true);
//        btnExit.setBorderPainted(false);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Serif", Font.BOLD, 25));

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

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCarName = txtAdd.getText();
                if (!newCarName.isEmpty()) {
                    try {
                        // 1. Update the strCars array
                        String[] newStrCars = new String[strCars.length + 1];
                        System.arraycopy(strCars, 0, newStrCars, 0, strCars.length);
                        newStrCars[newStrCars.length - 1] = newCarName;
                        strCars = newStrCars;

                        // 2. Refresh the JComboBox
                        cbCars.setModel(new DefaultComboBoxModel<>(strCars));

                        // Clear the text field
                        txtAdd.setText("");

                        //Send to the server
                        ClientSide.out.writeObject(newCarName);
                        ClientSide.out.flush();
                    } catch (IOException ex) {
                        System.out.println("Error sending vote to server: " + ex.getMessage());
                    }
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnVote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCar = (String) cbCars.getSelectedItem();
                if (!selectedCar.equals("Select:")) {
                    // 2. Send vote to server
                    try {
                        CarVote vote = new CarVote(selectedCar, 1); // Sending 1 vote
                        ClientSide.out.writeObject(vote);
                        ClientSide.out.flush();
                    } catch (IOException ex) {
                        System.out.println("Error sending vote to server: " + ex.getMessage());
                    }
                }
            }
        });

    }
}
