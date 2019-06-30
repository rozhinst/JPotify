package App;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Albums extends Songs implements Serializable {
    private String name;
    private ArrayList<Song> albumSong;
    public Albums() {
        super();
        albumSong = new ArrayList<>();
       // albumSong
        ///yadet bashe aval song be album basheha!!!!
    }
//    public static Albums manageAlbum(Song song) throws InvalidDataException, IOException, UnsupportedTagException {
//
//       // return  albums1;
//    }
    public void setId3(GetID3 id3){
        String[] s = id3.getDetails().get(1).split(":");
        name = s[1] ;
    }
    public String getName(){
        return name;
    }
    public ArrayList getAlbumSongs(){return albumSong;}
    public void setAlbumSong(ArrayList<Song> song){
        albumSong = song;
    }

    public void removeAlbumSongs() throws IOException {
        ArrayList<Song> songs =  this.reafSongsFromFile("src\\App\\songs\\song.bin");
        ArrayList<Albums> albums = Songs.reafAlbumsFromFile("src\\App\\songs\\Albums.bin");
        Iterator it = songs.iterator();
        Song s;
        while (it.hasNext()) {
            s = (Song) it.next();
            for(int i=0;i<songs.size();i++)
                if(songs.get(i).equals(s))
                    this.removeSongs(s);
        }
        albums.remove(this);
        this.writeSongsToFile(this.getSongArrays(),"src\\App\\songs\\song.bin");
        //this.writeToFile(this.getSongname(),"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
        this.writeAlbumsToFile(albums,"src\\App\\songs\\Albums.bin");
    }
}
