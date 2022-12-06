import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
public class MyServer {

    private static  ServerSocket ServerSocket;
    private static ServerSocket LeaderSocket ;
    private static Socket clientSocket = null;
    private static Socket LeaderCnxSocket = null;
    private static BufferedReader stdin;
    private static String fileName;
    private static PrintStream os;

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket = new ServerSocket(28444);
            LeaderSocket = new ServerSocket(12345);
            System.out.println("Heyy!! Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }
   
 
        while (true) {
            try {
            	
                LeaderCnxSocket = LeaderSocket.accept();
              
               System.out.println("Accepted connection : " + LeaderCnxSocket);


               clientSocket = ServerSocket.accept();
               System.out.println("Accepted connection : " + clientSocket);
               System.out.println("Client search file TD.pdf!");
                Thread t = new Thread(new ServiceClient(clientSocket,LeaderCnxSocket));

                t.start();
                
                
            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
            
            
        }
    }
  
}