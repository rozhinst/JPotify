package Server;


import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
public class ClientHandler extends Thread {
    private Socket socket;

    // private DataOutputStream dataOutputStream;
    // private DataInputStream dataInputStream;
    public ClientHandler(Socket client) throws Exception {
        super();
        if (client == null)
            throw new Exception("client can't be null");
        this.socket = client;
        // dataOutputStream = new DataOutputStream(client.getOutputStream());
        // dataInputStream = new DataInputStream(client.getInputStream());
    }

    public void run() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            JSONObject json = (JSONObject) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}