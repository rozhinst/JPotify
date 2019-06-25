import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DisplaySongs extends JFrame {
//    private final String WINDOWS_TITLE = "SONG";
//    private final int WIDTH = 1000;
//    private final int HEIGHT = 650;
//    private final int X = 100,Y = 100;
//    private JPanel song;
//    private Songs songs;
//    private JTextArea area;
//    public DisplaySongs() throws IOException, InvalidDataException, UnsupportedTagException {
//        super();
//        this.setTitle(WINDOWS_TITLE);
//        this.setLayout(new BorderLayout());
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(WIDTH, HEIGHT);
//        this.setLocation(X, Y);
//        song = new JPanel();
//        //album = new Albums();
//        add(song);
//        songs = new Songs();
//        area = new JTextArea();
//        song.add(area);
//        setVisible(true);
//    }
//    public void showSongs(JTextArea area){//parameter area
//        ArrayList a = (ArrayList) songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
//        for(int i=0;i<a.size();i++) {
//            area.append((String) a.get(i));
//       }
//
//    }
    public void showAlbumSongs(Albums album){//parameter area
        ArrayList songs = album.getAlbumSongs();


    }
    public void addAlbum() throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList albums = (ArrayList) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
        Albums album = new Albums();
        if (albums == null) albums = new ArrayList();
        JFileChooser chooser = new JFileChooser("src");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File[] filesInDirectory = chooser.getCurrentDirectory().listFiles();
        for (int i = 0; i < filesInDirectory.length; i++) {
            Song song = new Song();
            GetID3 id3 = new GetID3(filesInDirectory[i].getAbsolutePath());
            song.setName(id3.getDetails().get(0));
            song.setPath(filesInDirectory[i].getAbsolutePath());
            album.setAlbumSong(song);
        }
        album.setName();
        albums.add(album);
        Songs.writeToFile(albums, "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");

    }
    public void creatPlayList(String name){
        ArrayList playLists = (ArrayList) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\PlayLists.txt");
        PlayList playList = new PlayList(name);
        if(playLists == null) playLists = new ArrayList();
        playLists.add(playList);
        Songs.writeToFile(playLists,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\PlayLists.txt");


    }
    public void removePlaylist(PlayList playList ){
        ArrayList playLists = (ArrayList) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\PlayLists.txt");
        Iterator it = playLists.iterator();
        while(it.hasNext()){
            PlayList play = (PlayList) it.next();
            if(play.equals(playList)){
                playLists.remove(play);
            }
        }
        Songs.writeToFile(playLists, "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\PlayLists.txt");
    }


    }

//class Main3{
//    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
//        DisplaySongs songs = new DisplaySongs();
//        //songs.showSongs();
//
//    }
//}