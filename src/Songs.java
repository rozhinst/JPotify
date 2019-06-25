import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Songs  implements Serializable {
    //private static final String filepath = "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt";
    private JFileChooser chooser;
    private ArrayList songs;
    private String path;
    //private ArrayList<String> songNames;
    public Songs() {
        songs = new ArrayList();
        chooser = new JFileChooser("src");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //songNames = new ArrayList<>();
    }
    public Song addSong(ArrayList array) throws InvalidDataException, IOException, UnsupportedTagException {
        int r = chooser.showSaveDialog(null);
        Song song = new Song();
        if (r == JFileChooser.APPROVE_OPTION) {
            song.setPath(chooser.getSelectedFile().getAbsolutePath());
            GetID3 id3 = new GetID3(chooser.getSelectedFile().getAbsolutePath());
            String[] s = id3.getDetails().get(0).split(":");
            song.setName(s[1]);
            array.add(song);
            songs = array;
            System.out.println("hjggkgkh "+chooser.getSelectedFile().getAbsolutePath());
            System.out.println("namesssssss "+ id3.getDetails().get(0));
        }
        return song;
    }
    public void removeSongs(Song song){
        songs.remove(song);
        System.out.println("song removed");
    }
    public ArrayList getSongArrays(){
        return songs;
    }
//    //public ArrayList getSongname(){
//        return songNames;
//    }
   // public void setPath()
    public static void writeToFile(Object object,String path){
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object reafFromFile(String path) {
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
    public static void orderingSongs(ArrayList<Song> songs){
        ArrayList<Albums> albums = new ArrayList<>();
        for(int i=0;i<songs.size()-1;i++){
            for(int j=0;j<songs.size()-i-1;j++)
                if(songs.get(j).getTimePlayed() < songs.get(j+1).getTimePlayed()){
                    Song song = songs.get(j);
                    songs.set(j,songs.get(j+1));
                    songs.set(j+1,song);
                }
        }
        for(int i =0;i<songs.size();i++)
            albums.add(songs.get(i).getAlbum());
        Songs.writeToFile(songs,"src\\songs\\song.txt");
        Songs.writeToFile(albums,"src\\songs\\Albums.txt");
    }



}
class Main1{
   public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
//        ArrayList<Song> songs = (ArrayList<Song>) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
//       // songs.remove(4);
//
//        Songs.writeToFile(songs,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");

}
}
