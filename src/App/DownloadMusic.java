package App;

import Network.Client;
import Network.Server;

public class DownloadMusic {
    public void getSong(String path,String name){
        Client client = Server.clients.get(name);
        client.sendFile(MainFrame.name,path);
    }
}


