import java.io.Serializable;
import java.time.LocalTime;

public class Song implements Serializable {
    private String name;
    private String path;
    private long timePlayed=0;
    public void setName(String s){
        name = s;
    }
    public void setPath(String s){
        path = s;
    }
    public void setTimePlayed(){
        timePlayed = System.currentTimeMillis();
    }

    public long getTimePlayed() {
        return timePlayed;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
