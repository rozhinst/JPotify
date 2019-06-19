import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javax.sound.sampled.FloatControl;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.player.JavaSoundAudioDevice;
import java.io.IOException;
public class MP3 implements Runnable {
    private int cnt;
    private String path;
    Thread t;
    BufferedInputStream BIS;
    public MP3(int cnt,String path) {
        this.cnt = cnt;
        this.path = path;
    }

//
//    public Player player;
//    public long pauseLocation;
//    public long songTotalLength;
//    public String fileLocation;
//
//    public void Stop(){
//        if(player != null){
//            player.close();
//
//            pauseLocation = 0;
//            songTotalLength = 0;
//
//           // MP3PlayerGUI.Display.setText("");
//        }
//    }
//
//    public void Pause() throws IOException {
//        if(player != null){
//            try{
//                pauseLocation =  FIS.available();
//                player.close();
//
//            }
//            catch(IOException ex){
//
//            }
//
//        }
//    }
//
//    public void Play(String path){
//
//        try {
//            FIS = new FileInputStream(path);
//            BIS = new BufferedInputStream(FIS);
//
//            player = new Player(BIS);
//            songTotalLength = FIS.available();
//            fileLocation = path + "";
//
//        } catch (FileNotFoundException  | JavaLayerException ex ) {
//
//        } catch (IOException ex) {
//           ex.printStackTrace();
//        }
//
//        new Thread(){
//            @Override
//            public void run(){
//                try {
//                    player.play();
//
//                    if(player.isComplete() //&& MP3PlayerGUI.count == 1
//                    ){
//                        Play(fileLocation);
//                    }
//                    if(player.isComplete()){
//                       // MP3PlayerGUI.Display.setText("");
//                    }
//                } catch (JavaLayerException ex) {
//
//                }
//            }
//        }.start();
//
//    }
//
//    public void Resume(){
//
//        try {
//            FIS = new FileInputStream(fileLocation);
//            BIS = new BufferedInputStream(FIS);
//
//            player = new Player(BIS);
//            FIS.skip(songTotalLength - pauseLocation);
//
//        } catch (FileNotFoundException  | JavaLayerException ex ) {
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        new Thread(){
//            @Override
//            public void run(){
//                try {
//                    player.play();
//                } catch (JavaLayerException ex) {
//
//                }
//            }
//        }.start();
//
//    }
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
            AdvancedPlayer player = new AdvancedPlayer(FIS);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }
//    @Override
//    public void stateChanged(ChangeEvent e) {
//        // jmf keeps throwing exceptions despite the fact that volumnLevel is always in [0, 1.0]
//        // so instead of dividing sliderValue by 100.0 (so that each slider tick/value corresponds with the appropriate float value (1 = .01)..)
//        // I'm dividing it by 150 (can be whatever number as long as the resulting volumnLevel is not 1.0 or near it)..
//        (player.getGainControl()).setLevel((float)GUI.getVolumeSlider().getValue() / 150.0f);
//    }

}
