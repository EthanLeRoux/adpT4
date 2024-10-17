package za.ac.cput.mybackimage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Esihle
 */
public class guiImage extends JFrame {

    private JPanel pnlImage = new JPanel() {
        private Image backImage = new ImageIcon("RRE.jpg").getImage();

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JPanel pnlCenter = new JPanel();
    private JLabel lblImage = new JLabel();
    private JLabel lblSlogan = new JLabel("Got everything set? Ready to vote for top luxury Car?");
    private JButton btnGo = new JButton("LETS GO");

    public guiImage() {
        super("Luxury Vote");
    
        lblImage.setIcon(new ImageIcon("Legends.png"));
        lblSlogan.setBounds(200, 200, 1000, 500);
        lblSlogan.setForeground(Color.WHITE);
        lblSlogan.setFont(new Font("Serif", Font.BOLD, 30));

        btnGo.setBounds(450, 500, 150, 60);
        btnGo.setBackground(Color.black);
        btnGo.setForeground(Color.white);
        btnGo.setFont(new Font("Serif", Font.BOLD, 20));

        pnlImage.add(lblImage);
        add(btnGo);
        add(lblSlogan);
        add(pnlImage);

        btnGo.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnGo) {
                myVote vote = new myVote();
                vote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vote.setSize(1040, 800);
                vote.setVisible(true);
                dispose();
            }
        });

    }
}
