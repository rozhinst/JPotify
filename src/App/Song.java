package App;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;

public class Song implements Serializable {
    private String name;
    private String path;
    private long timePlayed=0;
    private Albums album;
    public Song(){
        album = new Albums();
    }
    public void setName(String s){
        name = s;
    }
    public void setPath(String s){
        path = s;
    }
    public void setTimePlayed(){
        timePlayed = System.currentTimeMillis();
    }
    public void setAlbum(Albums album1){album = album1; }
    public long getTimePlayed() {
        return timePlayed;
    }
    public Albums getAlbum(){return album;}

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}

