import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MP3 {
//    FileInputStream fis;
//
//    {
//        try {
//            fis = new FileInputStream("C:\\Users\\LENOVO\\Desktop\\JPotify\\src\\05. Ludovico Einaudi - Monday.mp3");
//            Player player = new Player(fis);
//            player.play();
//            int c =0;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (JavaLayerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        MP3 player1 = new MP3();
//    }
//    MP3 player = new MP3();
FileInputStream FIS;
    BufferedInputStream BIS;

    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;

    public void Stop(){
        if(player != null){
            player.close();

            pauseLocation = 0;
            songTotalLength = 0;

           // MP3PlayerGUI.Display.setText("");
        }
    }

    public void Pause() throws IOException {
        if(player != null){
            try{
                pauseLocation =  FIS.available();
                player.close();

            }
            catch(IOException ex){

            }

        }
    }

    public void Play(String path){

        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";

        } catch (FileNotFoundException  | JavaLayerException ex ) {

        } catch (IOException ex) {
           ex.printStackTrace();
        }

        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();

                    if(player.isComplete() //&& MP3PlayerGUI.count == 1
                    ){
                        Play(fileLocation);
                    }
                    if(player.isComplete()){
                       // MP3PlayerGUI.Display.setText("");
                    }
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }

    public void Resume(){

        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);

            player = new Player(BIS);
            FIS.skip(songTotalLength - pauseLocation);

        } catch (FileNotFoundException  | JavaLayerException ex ) {

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException ex) {

                }
            }
        }.start();

    }
}
