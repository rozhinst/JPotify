import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class MP3 implements Runnable {
    private int cnt;
    private int value;
    private int totalFrame;
    private String path;
    public AdvancedPlayer player;
    Thread t;
    public MP3(int cnt,String path,int totalFrame) {
        this.cnt = cnt;
        this.path = path;
        this.totalFrame = totalFrame;
        value = 0;
    }
    public MP3(String path){
        this.path = path;
        value = 0;
    }
    public MP3(){value = 0;}

    public void playMusic(Thread t){
            this.t = t;
            t.start();
    }
    public  void pauseMusic(){
        t.suspend();
    }
    public void resume(){
        t.resume();
    }
    public void run() {
        FileInputStream FIS ;
        try {
            FIS = new FileInputStream(path);
             player = new AdvancedPlayer(FIS);
            player.play(value,totalFrame);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }
    public void playLocation(int value){
        this.value = value;
    }
    public void stop(){
        t.stop();
    }
    public MP3 creatMP3(int counter,String path,GetID3 id3){
        return new MP3(counter, (String) path, id3.getTotalFrames());
    }
    public int shuffle(int size){
        Random random = new Random();
        return random.nextInt(size);
    }
}
