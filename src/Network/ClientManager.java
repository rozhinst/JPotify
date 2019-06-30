package Network;

import Network.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class ClientManager implements Runnable {
    Socket clientHolder;
    Server serverHolder;
    InputStream fromClientStream;
    OutputStream toClientStream;

    DataInputStream reader;
    PrintWriter writer;
    String name;
    HashMap<ClientManager,PrintWriter> writers = new HashMap<>();
    HashMap<ClientManager,DataInputStream> readers = new HashMap<>();

    public ClientManager(Server server, Socket client) {
        serverHolder = server;
        clientHolder = client;

    }

    public void run() {
        try {
            // input stream (stream from client)
            fromClientStream = clientHolder.getInputStream();

            // output stream (stream to client)
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);
            writers.put(this,writer);
            readers.put(this,reader);
            // send message to client
            writer.println("What is your name?");
           System.out.println("Server :What is your name?");

            // Receive client response (javab=name:D)
            name = reader.readLine();
            System.out.println(name + ":connected to server! ");

            // add "this" to Server "clientsMap" HashMap
            serverHolder.addClientManager(name, this);
//            Thread t = new Thread();



                    while (true) {

                            // read command from client
                             //command = null;
                            String command = reader.readLine();


                        // now decide by command ;-)
                        if (command.equals("BYE")) {

                            System.out.println("Good Bye " + name);
                            break;
                        } else if (command.equals("SCHT")) {
                            String to = null;

                            to = reader.readLine();

                            String text = null;
                            text = reader.readLine();


                            sendTextToAnotherClient(to, text);
                        } else if (command.equals("GCHT")) {
                            System.out.println("inja ");
                            String text = null;

                            text = reader.readLine();

                            sendTextToAllClients(text);
                        } else if (command.equals("SFILE")) {
                            String fileName = null;
                            try {
                                fileName = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String to = null;
                            try {
                                to = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            int fileLength = 0;
                            try {
                                fileLength = Integer.parseInt(reader.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            byte[] fileData = new byte[fileLength];

                            try {
                                reader.readFully(fileData);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            sendFileToAnotherClient(fileName, to, fileData);
                        } else if (command.equals("SOBJ")) {
                            String to = null;

                            to = reader.readLine();

                            int dataSize = 0;
                            try {
                                dataSize = Integer.parseInt(reader.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            byte[] fileData = new byte[dataSize];
                            try {
                                reader.readFully(fileData);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            // sendObjectToAnotherClient(to, fileData);

                        }
                        }
                    } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



     private void sendTextToAnotherClient(String to, String text) {
        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendText(name, text);
    }

     private void sendText(String from, String text) {
        writer.println("CHT");
        writer.println(from);
        writer.println(text);
    }

     private void sendTextToAllClients(String text) {
        // find all client managers
        // and for each of them use sendText method to send message
        System.out.println("inja send to all mishe");
         HashMap<String,ClientManager> s = serverHolder.getClientMap();
        for (String cmName : s.keySet() ) {
                if(!cmName.equals(name))
                s.get(cmName).sendText(name, text);
        }
    }

     private void sendFileToAnotherClient(String fileName, String to, byte[] fileData) {

        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendFile(name, fileName, fileData);

    }

     private void sendObjectToAnotherClient(String to, byte[] fileData) {
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendObject(name, fileData);

    }

     private void sendFile(String from, String fileName, byte[] fileData) {
        try {
            writer.println("FILE");
            writer.println(from);
            writer.println(fileName);
            writer.println("" + fileData.length);

            toClientStream.write(fileData, 0, fileData.length);
            toClientStream.flush();// force to send data

        } catch (IOException e) {
        }
    }

     private void sendObject(String from, byte[] fileData) {
        try {
            writer.println("OBJECT");
            writer.println(from);
            writer.println("" + fileData.length);

            toClientStream.write(fileData, 0, fileData.length);
            toClientStream.flush();// force to send data

        } catch (IOException e) {
        }
    }

}

