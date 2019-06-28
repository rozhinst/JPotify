import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlayList extends JButton implements Serializable {
    private String name;
    private ArrayList<Song> playListSongs;
    private String filePath;

    //private ArrayList<Songs> song;
    public PlayList(String name) {
        this.name = name;
        playListSongs = new ArrayList<>();
        //song = new ArrayList<>();
    }

    public void rename(String name) {
        if((!this.name.equals("Favorite")) && (!this.name.equals("Shared")))
        this.name = name;
    }

    public void addSongs(int index) throws IOException {
        Songs song = new Songs();
        ArrayList<Song> songs = (ArrayList<Song>) song.reafSongsFromFile("src\\songs\\song.bin");
        playListSongs.add(songs.get(index));//iteration?

    }


    public void removeSongs(int index) throws IOException {
        ArrayList<Song> songs = (ArrayList<Song>) Songs.reafSongsFromFile("src\\songs\\song.bin");
        playListSongs.remove(songs.get(index));//iteration????
    }

    public void reOrder(int index1, int index2) {
        Collections.swap(playListSongs, index1, index2);
    }//creatplaylist tuuye action listenere add new playliste va ye array az playlist ha va save o write hamunja bashe;

    public ArrayList<Song> getPlayListSongs() {
        return playListSongs;
    }

    public String getName() {
        return name;
    }
}
class Main2 {
    public static void main(String[] args) throws IOException {
        PlayList playList = new PlayList("rozhin");
        playList.addSongs(0);
        playList.addSongs(2);
        System.out.println("songs added");
        for (int i = 0; i < playList.getPlayListSongs().size(); i++) {
            System.out.println(playList.getName() + "    " + playList.getPlayListSongs().get(i).getName());
        }
        //playList.removeSongs(0);
       // playList.getPlayListSongs().remove(0);///tabe remove moshkel dare chon read va write nemishe
       // playList.reOrder(0,1);

        System.out.println("song remove");
        for (int i = 0; i < playList.getPlayListSongs().size(); i++) {
            System.out.println(playList.getName() + "    " + playList.getPlayListSongs().get(i).getName());
        }

    }

}
