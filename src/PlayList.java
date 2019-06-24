import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlayList implements Serializable {
    private String name;
    private ArrayList<Song> playListSongs;
    //private ArrayList<Songs> song;
    public PlayList(String name){
        this.name = name;
        playListSongs = new ArrayList<>();
        //song = new ArrayList<>();
    }
    public void rename(String name){
        this.name = name;
    }
    public void addSongs(int index){
        Songs song = new Songs();
        ArrayList<Song> songs = (ArrayList<Song>) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
        playListSongs.add(songs.get(index));//iteration?
    }
    public void removeSongs(int index){
        Songs song = new Songs();
        ArrayList<Song> songs = (ArrayList<Song>) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
        playListSongs.remove(songs.get(index));//iteration????
    }
    public void reOrder(int index1,int index2){
        Collections.swap(playListSongs,index1,index2);
    }//creatplaylist tuuye action listenere add new playliste va ye array az playlist ha va save o write hamunja bashe;
    public ArrayList<Song> getPlayListSongs(){
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
