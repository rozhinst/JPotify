package App;

import Network.Client;
import Network.Server;

public class DownloadMusic {
   synchronized public void getSong(String path,String name){
        Client client = Server.clients.get(name);
        client.sendFile(MainFrame.name,path);
    }
    synchronized public void sendSharedPlayList(String name){
        Client client = Server.clients.get(name);
        client.sendFile(MainFrame.name,"src\\App\\playlists\\Shared.bin");
    }
}


