import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiddlePage extends JPanel{
    private ArrayList <JPanel> songs;
    private ArrayList <String> songPaths;
    private  Songs songsInLib;
    private JFrame songsInLibrary;
    private JFrame albumsInLibrary;
    private JFrame songinPlaylist;

    public MiddlePage(){
        songinPlaylist = new JFrame();
        songsInLibrary = new JFrame();
        albumsInLibrary = new JFrame();




       // setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        songsInLibrary.setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        songsInLib = new Songs();
        songPaths =  songsInLib.getSongArrays();
        for(int i=0;i<songPaths.size();i++){
           JPanel panel = new JPanel();
           panel.add( new ImageIcon(new ImageIcon(.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
)
           songs.add(panel);

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
