package Network;

import App.MainFrame;
import App.Song;
import App.SongbarGUI;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable{
    Socket mSocket;
    int port = 2220;
    String serverAddress = "127.0.0.1";

    InputStream fromServerStream;
    OutputStream toServerStream;

    DataInputStream reader;
    PrintWriter writer;
    ArrayList<String> paths;
    MainFrame mainFrame = null;
    String name;
    private static volatile int commandNumber = 0;
    private static volatile boolean flag = false;
    private static final Object lock = new Object();

    public Client(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        paths = new ArrayList<>();
        this.name = name;
    }
        public void start(){
        try {

            mSocket = new Socket(serverAddress, port);


            System.out.println("connect to server ....");
            // input stream (stream from server)
            fromServerStream = mSocket.getInputStream();

            // output stream (stream to server)
            toServerStream = mSocket.getOutputStream();

            reader = new DataInputStream(fromServerStream);
            writer = new PrintWriter(toServerStream, true);

            // first : read server message
            String msg = reader.readLine();
            System.out.println("Server :" + msg);

            // Manage other server message by a Thread
            Thread t = new Thread(new ServerMessagesManager(reader,mainFrame));
            System.out.println("umad to start");
            t.start();
            sendOrder();

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        }
//    @Override
//    public void run() {

            //Scanner sc = new Scanner(System.in);
        public void sendOrder(){
            //name = "mn";
            sendName(name);
            System.out.println(commandNumber);
            if(mainFrame != null) return;
            new Thread(() -> {
                while (commandNumber!=0) {
                    // System.out.println(commandNumber + " "+ flag);
//                    try {
//                        synchronized (lock) {
//                            lock.wait();
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //while (flag == true) {
                        // commandNumber = Integer.parseInt(reader.readLine());
                        //System.out.println(commandNumber+"eh");
                        switch (commandNumber) {
                            case 1:
                                System.out.println("Enter receiver name");
                                //String to = reader.readLine();
                                String to = "rozhin";
//                    System.out.println("Enter your message");
//                    String text = sc.nextLine();
                                Song song = SongbarGUI.getCurrentSong();
                                String text = song.getName();
                                System.out.println(text);
                                sendSingleCht(to, text);
                                System.out.println("done");
                               // flag = false;
                                break;
                            case 2:
                                //System.out.println("Enter your message");
                                System.out.println("umad");
                                //text = reader.readLine();
                                song = SongbarGUI.getCurrentSong();
                                text = song.getName();
                                System.out.println(text);
                                sendGroupCht(text);
                                System.out.println("command 2 done");
                                //flag = false;
                                break;
                            case 3:
                                System.out.println("Enter receiver name");
                                try {
                                    to = reader.readLine();
                                    for (int i = 0; i < paths.size(); i++) {
                                        String fileName = paths.get(i);
                                        sendFile(to, fileName);
                                    }
                                   // flag = false;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

//                    System.out.println("Enter file name(Full Path)");
//                    String fileName = sc.nextLine();
                                break;
                            case 4:

                                bye();
                               // flag = false;
                                return;

                        }
                   // }
                }
            }).start();
        }



     public void sendName(String name) {
        writer.println(name);
    }

     public void sendSingleCht(String to, String text) {
        writer.println("SCHT");
        writer.println(to);
        writer.println(text);
    }

     public void sendGroupCht(String text) {
        System.out.println("hamiddddddddddd");

        writer.println("GCHT");
        writer.println(text);
    }

     public void sendFile(String to, String fileName) {
        File file = new File(fileName);
        long fileLength = file.length();

        writer.println("SFILE");
        writer.println(file.getName());
        writer.println(to);
        writer.println("" + fileLength);

        try {

            // convert file to byte array
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            byte[] fileData = new byte[(int) fileLength];
            dis.readFully(fileData);

            // send byte array to server
            toServerStream.write(fileData);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     public void bye() {
        writer.println("BYE");
    }

     public static void setCommandNumber(int cmd) {
        setFlag(true);
        commandNumber = cmd;
        System.out.println("kar mikonam "+commandNumber+" "+flag);

//        synchronized (lock){
//            lock.notifyAll();
//        }

        //System.exit(0);

    }

    @Override
    public void run() {
        start();
    }


     public static int getCommandNumber() {
        return commandNumber;
    }
     public static boolean getFlag(){return flag;}
     public  static void setFlag(boolean bool){flag = bool;}
     public void setName(String name){this.name = name;}
     public String getName() {return name;}

//    public static void main(String[] args) {
////        try {
////
////            System.out.println("1");
////           // MainFrame mainFrame = new MainFrame();
////            //new Thread(mainFrame).start();
////            MainFrame main = new MainFrame();
////            new Thread(main).start();
////            System.out.println("2");
////            //new Thread(new Client(mainFrame,"rozhin")).start();
////            new Client(main);
////            System.out.println("3");
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (InvalidDataException e) {
////            e.printStackTrace();
////        } catch (UnsupportedTagException e) {
////            e.printStackTrace();
////        }
////
////
////    }
//
//
//    }
}
