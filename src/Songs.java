import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Songs implements Serializable {
    private static final String filepath = "C:\\Users\\hp\\JPotify\\src\\songs\\song.txt";
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
    public void addSong(ArrayList array) throws InvalidDataException, IOException, UnsupportedTagException {
        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            array.add(chooser.getSelectedFile().getAbsolutePath());
            songs = array;
            System.out.println("hjggkgkh "+chooser.getSelectedFile().getAbsolutePath());
            GetID3 id3 = new GetID3(chooser.getSelectedFile().getAbsolutePath());
            songNames.add(id3.getDetails().get(0));
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
   // public void setPath()
    public void writeToFile(ArrayList arrayList){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(filepath);
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
    public Object reafFromFile() {
        FileInputStream fileIn;
        try {

            fileIn = new FileInputStream(filepath);
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
       ArrayList a = (ArrayList) playList.reafFromFile();
       if(a==null)  a = new ArrayList();
       playList.addSong(a);
       for (int i=0;i<a.size();i++)
           System.out.println(a.get(i)+"hellloooo");
       playList.writeToFile(a);


    }
}
