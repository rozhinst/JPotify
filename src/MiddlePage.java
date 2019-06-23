import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MiddlePage extends JPanel{
    private  GetID3 id3;
    private  ArrayList filePath;
    private ArrayList <JPanel> songs;
    private ArrayList <String> songPaths;
    private  Songs songsInLib;
    private JFrame songsInLibrary;
    private JFrame albumsInLibrary;
    private JFrame songinPlaylist;

    public MiddlePage() throws InvalidDataException, IOException, UnsupportedTagException {
        songinPlaylist = new JFrame();
        songsInLibrary = new JFrame();
        albumsInLibrary = new JFrame();


        songsInLibrary.setVisible(true);

       // setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        songsInLibrary.setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        songsInLib = new Songs();
        filePath = (ArrayList) songsInLib.reafFromFile("src\\songs\\song.txt");
        songPaths =  songsInLib.getSongArrays();
       songs = new ArrayList<>(songPaths.size());
        for(int i=0;i<songPaths.size();i++){

            id3 = new GetID3((String) filePath.get(i));
            Image newImage = id3.getImg().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel artWork = new JLabel();
            artWork.setIcon(new ImageIcon(newImage));


            songs.get(i).add(artWork);
          // songs.add(panel);
            songsInLibrary.add(songs.get(i));

        }



    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color2 = Color.BLACK;
        Color color1 = Color.gray;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
