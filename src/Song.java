import java.time.LocalTime;

public class Song {
    private String name;
    private String path;
    private LocalTime timePlayed;
    public void setName(String s){
        name = s;
    }
    public void setPath(String s){
        path = s;
    }
    public void setTimePlayed(){
        timePlayed = LocalTime.now();
    }

    public LocalTime getTimePlayed() {
        return timePlayed;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
