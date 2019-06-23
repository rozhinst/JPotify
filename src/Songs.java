import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Songs implements Serializable {
    private static final String filepath = "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt";
    private JFileChooser chooser;
    private ArrayList songs;
    private String path;
    private ArrayList<String> songNames;
    public Songs() {
        songs = new ArrayList();
        chooser = new JFileChooser("src");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        songNames = new ArrayList<>();
    }
    public void addSong(ArrayList array,ArrayList name) throws InvalidDataException, IOException, UnsupportedTagException {
        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            array.add(chooser.getSelectedFile().getAbsolutePath());
            songs = array;
            System.out.println("hjggkgkh "+chooser.getSelectedFile().getAbsolutePath());
            GetID3 id3 = new GetID3(chooser.getSelectedFile().getAbsolutePath());
            System.out.println("namesssssss "+ id3.getDetails().get(0));
            String [] s =  id3.getDetails().get(0).split(":");
            name.add(s[1]);
            songNames = name;

        }
    }
    public void removeSongs(String s) throws InvalidDataException, IOException, UnsupportedTagException {
        songs.remove(s);
        GetID3 id3 = new GetID3(s);
        songNames.remove(id3.getDetails().get(0));
    }
    public ArrayList getSongArrays(){
        return songs;
    }
    public ArrayList getSongname(){
        return songNames;
    }
   // public void setPath()
    public void writeToFile(ArrayList arrayList,String path){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(arrayList);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object reafFromFile(String path) {
        FileInputStream fileIn;
        try {

            fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
class Main1{
   public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        Songs playList = new Songs();
       //playList.writeToFile();
       ArrayList a = (ArrayList) playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
       ArrayList name = (ArrayList) playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
       if(a==null)  a = new ArrayList();
       if(name == null) name = new ArrayList();
       playList.addSong(a,name);
       for (int i=0;i<a.size();i++) {
           System.out.println(a.get(i) + "hellloooo");
           System.out.println(name.get(i)+" namee");
       }
       playList.writeToFile(a,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
       playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
    }
}
