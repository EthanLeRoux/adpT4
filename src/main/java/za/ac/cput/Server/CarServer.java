/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import za.ac.cput.Domain.CarVote;

/**
 *
 * @author ethan
 */

public class CarServer  extends JFrame {
    private ServerSocket listener;
    private String msg = "";
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String response = "";
    private Socket client;
    
    private JButton exitBtn;
    private JTextArea clientTxtArea;
    private JPanel topPanel;
    private JPanel centerPanel;
    
    public CarServer() throws IOException{
        listener = new ServerSocket(6666,10);
        
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
    
    private void listenForClients() throws IOException
    {
        clientTxtArea.append("Listening for Clients...");
        System.out.println("Listening for Clients...");
        
        client = listener.accept();
        
        clientTxtArea.append("\n"+client.getInetAddress() + " is connected.");
        System.out.println(client.getInetAddress() + " is connected.");
    }
    
    private void getStreams() throws IOException
    {   
        out = new ObjectOutputStream(client.getOutputStream());
        out.flush();
        in = new ObjectInputStream(client.getInputStream());
        
    }
    
    private void sendData(String myMsg) throws IOException
    {
        out.writeObject(myMsg);
        out.flush();
    }
    
    public void closeStuff() throws IOException{
        in.close();
        out.close();
        this.dispose();
    }
    
    public void processClient() throws IOException, ClassNotFoundException, SQLException
    {
        clientTxtArea.append("\n"+"Processing the Client...");
        System.out.println("Processing the Client...");
        while(true){
            msg = (String)in.readObject();
            
            clientTxtArea.append("\n"+"From CLIENT>> " + msg);    
            System.out.println("From CLIENT>> " + msg);
                
                if(msg.equalsIgnoreCase("exit")){
                    clientTxtArea.append("\n"+"Client has terminated the connection.");
                    System.out.println("Client has terminated the connection.");
                    break;
                }
                
                createNewCar(msg);
                //voteForCar(msg)
        }
        closeStuff();
    }

    void voteForCar(String name) throws SQLException{
        int vote = 0;
        CarVote cv = new CarVote(name,vote);
        CarDAO dao = new CarDAO();
        dao.voteForExistingCar(cv);
        
        clientTxtArea.append("Voted for" + name + " succesfully");
        System.out.println("Voted for" + name + " succesfully");
    }
    
    void createNewCar(String name) throws SQLException{
        CarVote cv = new CarVote(name, 0);
        CarDAO dao = new CarDAO();
        dao.addCar(cv);
        
        clientTxtArea.append("\n"+ "Added new car" + name + " sucvcsfully");
        System.out.println("Added new car" + name + " sucvcsfully");
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        CarServer cs = new CarServer();
        cs.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cs.setSize(500, 500);
        cs.setVisible(true);
        
        cs.listenForClients();
        cs.getStreams();
        cs.sendData("Its the client Yo");
//        cs.voteForCar("Audi Q8");
        cs.processClient();
    }
}
