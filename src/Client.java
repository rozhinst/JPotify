import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    ClientReciever reciever;
    ClientSender sender;
    String IP;
    ArrayList<String> friends;
    String name;
    Socket client;
    public Client() throws IOException {
        client = new Socket("",1234);
        friends = new ArrayList<>();
        reciever.inputStream = client.getInputStream();
        sender.outputStream = client.getOutputStream();
        ObjectInputStream in = new ObjectInputStream(reciever.inputStream);
        ObjectOutputStream out = new ObjectOutputStream(sender.outputStream);
        out.writeObject(friends);
        Thread input = new Thread(reciever);
        Thread output = new Thread(sender);
    }
}
