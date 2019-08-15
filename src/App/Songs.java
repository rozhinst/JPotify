package App;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Songs implements Serializable {
    private JFileChooser chooser;
    private ArrayList songs;
    private String path;


    public Songs() {
        songs = new ArrayList();
        chooser = new JFileChooser("src");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

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
            System.out.println("hjggkgkh " + chooser.getSelectedFile().getAbsolutePath());
            System.out.println("namesssssss " + id3.getDetails().get(0));
        }
        return song;
    }

    public void removeSongs(Song song) {
        songs.remove(song);
        System.out.println("song removed");
    }

    public ArrayList getSongArrays() {
        return songs;
    }




    //it can also be used for playlist
    public static void writeSongsToFile(ArrayList<Song> songs, String path) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Song song : songs)
                objectOut.writeObject(song);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   // public static void writePlaylistToFlie()
    public static void writeLibrariesToFile(ArrayList<PlayList> playLists, String path) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (PlayList playList : playLists)
                objectOut.writeObject(playList);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeAlbumsToFile(ArrayList<Albums> albums, String path) {
        System.out.println("Albumssssssssssssssssssssss");
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Albums  album: albums)
                objectOut.writeObject(album);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Albums> reafAlbumsFromFile(String path) throws IOException {
        System.out.println("reading Albumssssssssssssssssssssss");
        ArrayList<Albums> temp = new ArrayList<>();
        ObjectInputStream objectIn = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(path);
            objectIn = new ObjectInputStream(fileIn);
            while (true)
                temp.add((Albums) objectIn.readObject());

        } catch (EOFException ex) {
            objectIn.close();
            fileIn.close();
            return temp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<PlayList> reafPlayListFromFile(String path) throws IOException {
        ArrayList<PlayList> temp = new ArrayList<>();
        ObjectInputStream objectIn = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(path);
            objectIn = new ObjectInputStream(fileIn);
            while (true)
                temp.add((PlayList) objectIn.readObject());

        } catch (EOFException ex) {
            objectIn.close();
            fileIn.close();
            return temp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /////////it can also be used for reading from a playlist

    public static ArrayList<Song> reafSongsFromFile(String path) throws IOException {
        ArrayList<Song> temp = new ArrayList<>();
        ObjectInputStream objectIn = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(path);
            objectIn = new ObjectInputStream(fileIn);
            while (true)
                temp.add((Song) objectIn.readObject());

        } catch (EOFException ex) {
            objectIn.close();
            fileIn.close();
            return temp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
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

    public static void orderingSongs() throws IOException {
        ArrayList<Song> songs = Songs.reafSongsFromFile("src\\App\\songs\\song.bin");
        ArrayList<Albums> albums = new ArrayList<>();
        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = 0; j < songs.size() - i - 1; j++)
                if (songs.get(j).getTimePlayed() < songs.get(j + 1).getTimePlayed()) {
                    Song song = songs.get(j);
                    songs.set(j, songs.get(j + 1));
                    songs.set(j + 1, song);
                }
        }
        for (int i = 0; i < songs.size(); i++)
            albums.add(songs.get(i).getAlbum());
        Songs.writeSongsToFile(songs, "src\\App\\songs\\song.bin");
    }


}

class Main1 {
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
//        ArrayList<Song> songs = (ArrayList<Song>) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
//       // songs.remove(4);
//
//        Songs.writeToFile(songs,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");

    }
}
