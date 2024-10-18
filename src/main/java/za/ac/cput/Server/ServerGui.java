/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ethan
 */
public class ServerGui extends JFrame implements ActionListener{
    private JButton exitBtn;
    private JTextArea clientTxtArea;
    private String response;
    private JPanel topPanel;
    private JPanel centerPanel;
    
    public ServerGui(){
     exitBtn = new JButton("EXIT");
     clientTxtArea = new JTextArea(10,30);
     response = "";
     topPanel = new JPanel();
     centerPanel = new JPanel();
     
     setGui();
    }
    
    public void setGui(){
        clientTxtArea.setEditable(false);
        centerPanel.add(clientTxtArea);
        topPanel.add(exitBtn);
        this.add(topPanel);
        this.add(centerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("EXIT")){
            
        }
    }
    
}

