import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlayList implements Serializable {
    private String name;
    private ArrayList<String> playListSongs;
    private ArrayList<Songs> song;
    public PlayList(String name){
        this.name = name;
        playListSongs = new ArrayList<>();
        song = new ArrayList<>();
    }
    public void rename(String name){
        this.name = name;
    }
    public void addSongs(int index){
        Songs song = new Songs();
        ArrayList<String> songs = (ArrayList<String>) song.reafFromFile();
        playListSongs.add(songs.get(index));
    }
    public void removeSongs(int index){
        Songs song = new Songs();
        ArrayList<String> songs = (ArrayList<String>) song.reafFromFile();
        playListSongs.remove(songs.get(index));
    }
    public void reOrder(int index1,int index2){
        Collections.swap(playListSongs,index1,index2);
    }//creatplaylist tuuye action listenere add new playliste va ye array az playlist ha va save o write hamunja bashe;
    public ArrayList<String> getPlayListSongs(){
        return playListSongs;
    }
    public String getName(){
        return name;
    }
}
class Main2{
    public static void main(String[] args) {
        PlayList playList = new PlayList("rozhin");
        playList.addSongs(0);
        playList.addSongs(1);
        System.out.println("songs added");
        for (int i =0;i<playList.getPlayListSongs().size();i++){
            System.out.println(playList.getName()+"    "+playList.getPlayListSongs().get(i));
        }
        playList.removeSongs(0);
        System.out.println("song remove");
        for (int i =0;i<playList.getPlayListSongs().size();i++){
            System.out.println(playList.getName()+"    "+playList.getPlayListSongs().get(i));
        }

}

}
