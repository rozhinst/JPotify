import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetID3 {
    //private byte[] aByte;
    private String path;
    private String title;
    private String albume;
    private String artist;
    private String year;
    private String genre;
    private String tag;
    RandomAccessFile rand;
    private Mp3File mp3File;
    private ArrayList<String> details;
    private BufferedImage img;
    public GetID3(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        this.path = path;
        details = new ArrayList<>();
        mp3File = new Mp3File(path);
        try {
//            rand = new RandomAccessFile(path,"r");
//            aByte = new byte[(int) rand.length()];
//            rand.read(aByte, (int) (rand.length() -), 128);
            File song = new File(path);
            FileInputStream file = new FileInputStream(song);
            int size = (int)song.length();
            file.skip(size - 128);
            byte[] last128 = new byte[128];
            file.read(last128);
            String id3 = new String(last128);
            String tag = id3.substring(0, 3);
            if (tag.equals("TAG")) {
               // if(mp3File.hasId3v1Tag()) {
                //System.out.println(new String(aByte, 3, 30));
                details.add(title = "Title: " + id3.substring(3, 32)+ "\n"); //new String(aByte, 3, 30));
                details.add(albume = "Album: " + id3.substring(63, 91)+ "\n");//new String(aByte, 30, 63));
                details.add(artist = "Artist: " + id3.substring(33, 62)+ "\n");//new String(aByte, 33, 30));
                details.add(year = "Year: " + id3.substring(93, 97)+ "\n" );//new String(aByte, 93, 4));
                details.add(genre = "Genre: " + id3.substring(127, 128)+ "\n"); //new String(aByte, 127, 1));
                String tmp = "Duration "+mp3File.getLengthInSeconds()/60 +":";
                if(mp3File.getLengthInSeconds()%60<10) tmp+="0"+mp3File.getLengthInSeconds()%60;
                else tmp+=mp3File.getLengthInSeconds()%60;
                details.add(tmp);
            }
            file.close();

            if (mp3File.hasId3v2Tag()){
                System.out.println("okkk");
                ID3v2 id3v2tag = mp3File.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                img = ImageIO.read(new ByteArrayInputStream(imageData));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getDetails() {
        return details;
    }

    public BufferedImage getImg() {

        return img;
    }
    public Mp3File getMp3File(){
        return mp3File;
    }
    public int getTotalFrames(){
        return mp3File.getFrameCount();
    }
}
