package Network;

import App.FriendsActivityGUI;
import App.FriendsActivityLogic;
import App.MainFrame;

import javax.lang.model.util.ElementScanner6;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerMessagesManager implements Runnable {
    DataInputStream readerHolder;
   MainFrame mainFrame;

    public ServerMessagesManager(DataInputStream reader,MainFrame mainframe1) {
        this.mainFrame = mainframe1;
        readerHolder = reader;
    }

    public void run() {

        while (true) {//inja be ui vasle

            try {
                String command = readerHolder.readLine();
                System.out.println(command + "   your command");
                if (command.equals("CHT")) {
                    String from = readerHolder.readLine();
                    String text = readerHolder.readLine();
                    showFriends(from,text);
                    System.out.println(text + "  "+from);
                }
                if (command.equals("FILE")) {
                    String from = readerHolder.readLine();
                    String fileName = readerHolder.readLine();
                    int fileLength = Integer.parseInt(readerHolder.readLine());

                    byte[] fileData = new byte[fileLength];
                    readerHolder.readFully(fileData);

                    // save file to "Files" Folder
                    File filesFolder = new File("src\\Files");
                    if (!filesFolder.exists()) {
                        filesFolder.mkdir();
                    }
                    File file = new File(filesFolder, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(fileData);
                    fos.flush();
                    fos.close();

                    System.out.println("New File recieved From " + from);
                }

            } catch (IOException e) {
            }


        }
    }
    private void showFriends(String from,String song){
        if(mainFrame!=null) {
            System.out.println("na dg");
            mainFrame.getFriendsActivityLogic().setRecentlyPlayed(song, from);
            System.out.println("hi");
        }
        System.out.println("injam text miad :)) ");
    }
}
