package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private static final int PORT = 1234;
    private ServerSocket serverSocket;
    private static ArrayList<String> online = new ArrayList<>();
    public static HashMap<String, Socket> clients = new HashMap();
    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server Client Created");
        while( true ){
            Socket client = null;
            try {
                client = serverSocket.accept();
                System.out.println("A new Socket connected");
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandler.start();
                System.out.println("Here");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
