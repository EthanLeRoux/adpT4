package za.ac.cput.mybackimage;


/**
 *
 * @author henzley
 */
import java.io.IOException;
import java.net.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class ClientSide {

    private Socket clientSocket;
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;

    public ClientSide() {
        //establish connection to server
        try {
            clientSocket = new Socket("localhost", 6666);
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }

    public void getStreams() {
        ObjectInputStream in = null;
        try {
            //construct stream objects for data transfer
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }

    public void communicate() {
        
    }
}
