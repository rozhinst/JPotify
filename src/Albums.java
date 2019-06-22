import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Albums extends Songs{
    private GetID3 id3;
    private String name;
    private ArrayList<String> albumSong;
    public Albums() throws InvalidDataException, IOException, UnsupportedTagException {
        super();
        ///yadet bashe aval song be album basheha!!!!!
        id3 = new GetID3(albumSong.get(0));

    }
    public BufferedImage showPicture(){
        return id3.getImg();
    }
    public void manageAlbum(){
        ArrayList<String> songs = (ArrayList<String>) this.reafFromFile();
///?????????????????????

    }
    public void setName(){
        name = id3.getDetails().get(1);
    }
    public String getName(){
        return name;
    }
    public void removeAlbumSongs() throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList<String> songs = (ArrayList<String>) this.reafFromFile();
        Iterator it = songs.iterator();
        String s;
        while (it.hasNext()) {
            s = (String) it.next();
            for(int i=0;i<songs.size();i++)
                if(songs.get(i).equals(s))
                    this.removeSongs(s);
        }
        this.writeToFile(this.getSongArrays());
    }
}
