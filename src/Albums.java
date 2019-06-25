import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Albums extends Songs{
    private static GetID3 id3;
    private static String name;
    private static ArrayList<Song> albumSong;
    public Albums() {
        super();
        albumSong = new ArrayList<>();
        ///yadet bashe aval song be album basheha!!!!
    }
    public BufferedImage showPicture() throws InvalidDataException, IOException, UnsupportedTagException {
        id3 = new GetID3(albumSong.get(0).getPath());
        return id3.getImg();
    }
    public static Albums manageAlbum(Song song) throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList<Albums> albums = (ArrayList<Albums>) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
        if(albums == null) albums =new ArrayList<>();
        Albums albums1 = new Albums();
        GetID3 id3 = new GetID3(song.getPath());
        if(albums.size() == 0){
            albums1 = new Albums();
            albums1.setAlbumSong(song);
            albums1.setName();
            albums.add(albums1);
        }
        else {
            for (int i = 0; i < albums.size(); i++) {
                if (id3.getDetails().get(1).equals(albums.get(i).getName()))
                    albums.get(i).setAlbumSong(song);
                else {

                    song.setAlbum(albums1);
                    albums1.setAlbumSong(song);
                    albums1.setName();
                    albums.add(albums1);
                }
            }
        }
        System.out.println(albums.size()+"helooooooooooooooooo");
        Songs.writeToFile(albums,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
        return  albums1;
    }
    public static void setName() throws InvalidDataException, IOException, UnsupportedTagException {
        id3 = new GetID3(albumSong.get(0).getPath());
        name = id3.getDetails().get(1);
    }
    public  String getName(){
        return name;
    }
    public static ArrayList getAlbumSongs(){return albumSong;}
    public static void setAlbumSong(Song song){
        albumSong.add(song);
    }

    public void removeAlbumSongs(){
        ArrayList<Song> songs = (ArrayList<Song>) this.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
        ArrayList albums = (ArrayList) Songs.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
        Iterator it = songs.iterator();
        Song s;
        while (it.hasNext()) {
            s = (Song) it.next();
            for(int i=0;i<songs.size();i++)
                if(songs.get(i).equals(s))
                    this.removeSongs(s);
        }
        albums.remove(this);
        this.writeToFile(this.getSongArrays(),"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
        //this.writeToFile(this.getSongname(),"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
        this.writeToFile(albums,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
    }
}
