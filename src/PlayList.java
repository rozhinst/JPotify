import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlayList implements Serializable {
    private Songs songs;
    private String name;
    private ArrayList<Songs> songList;
    private ArrayList<PlayList> playLists;
    private ArrayList<Songs> playListSongs;
    private static final String filepath = "JPotifyy\\playlists\\playlists.txt";

    public PlayList(){
        songs = new Songs();
        playListSongs = new ArrayList<>();
        this.songList = new ArrayList<>();
        this.playLists = new ArrayList<>();
    }
    public void creatPlayList(String name,ArrayList arrayList){
        PlayList playList = new PlayList();
        playList.name = name;
        arrayList.add(playList);
    }
    public void addSongs(int n){
        songList = (ArrayList) songs.reafFromFile();
        while(n>-1){////????????? check kon
            playListSongs.add(songList.get(n));
            n--;
        }
    }
    public void removeSongs(int n){
        songList = (ArrayList) songs.reafFromFile();
        while(n>-1){////????????? check kon
            playListSongs.remove(songList.get(n));
        }
    }
    public void rename(String name){
        this.name = name;
    }
    public void removePlaylist(PlayList playList){
        playLists.remove(playList);
    }
    public void changeSongOrder(int index1,int index2){
        Collections.swap(playListSongs,index1,index2);
    }
    public ArrayList getPlayListSongs(){
        return playListSongs;
    }
    public ArrayList getPlaylists(){return playLists;}
    public Object readPlaylists(){
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
    public void savePlaylist(ArrayList arrayList){
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
}
class Main2{
    public static void main(String[] args) {
        PlayList playList = new PlayList();
        ArrayList<PlayList> a =(ArrayList<PlayList>) playList.readPlaylists();
        if(a==null) a = new ArrayList<>();
        playList.creatPlayList("rozhinst",a);
        playList.savePlaylist(playList.getPlaylists());
        a.get(0).addSongs(0);
        ArrayList<Songs> songs =(ArrayList<Songs>) a.get(0).getPlayListSongs();

        for (int i =0;i<songs.size();i++){
            ArrayList<String> paths =  songs.get(i).getSongArrays();
            for(int j=0;j<paths.size();j++) System.out.println(paths.get(j));
        }
        playList.changeSongOrder(0,1);
        for (int i =0;i<songs.size();i++){
            ArrayList<String> paths =  songs.get(i).getSongArrays();
            for(int j=0;j<paths.size();j++) System.out.println(paths.get(j));
        }
        playList.savePlaylist(a);




    }
}
