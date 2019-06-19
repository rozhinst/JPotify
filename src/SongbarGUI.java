import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class SongbarGUI extends JPanel {
    private JButton prev;
    private JButton pause;
    private JButton next;
    private JButton shuffle;
    private JPanel playButtons;
    private JPanel songbar;
    private JProgressBar bar;
    private  JPanel barPanel;
    private JSlider volume;
    private JLabel artwork;
    private ImageIcon artworkImage;
    public SongbarGUI() {


        setLayout(new BorderLayout());


        setPreferredSize(new Dimension(20,100));
        playButtons = new JPanel();
        songbar = new JPanel();
        playButtons.setBackground(new Color(66,28,82));

        artwork = new JLabel("artwork");
        artwork.setPreferredSize(new Dimension(100,100));
        artworkImage = new ImageIcon("C:\\Users\\hp\\JPotify\\src\\images1.jpg");
        artwork.setIcon(artworkImage);

        playButtons.setLayout( new FlowLayout(FlowLayout.CENTER,10,3));

        songbar.setLayout(new BorderLayout());
        add(songbar, CENTER);

        add(artwork,BorderLayout.WEST);

        playButtons.setBorder(new EmptyBorder(10,10,10,10));

        prev = new JButton("previous");
        next = new JButton( "next");
        pause = new JButton("pause/play");
        shuffle = new JButton("shuffle");
        bar = new JProgressBar();
        barPanel = new JPanel();
        volume = new JSlider();

       bar.setBackground(Color.LIGHT_GRAY);
        bar.setPreferredSize(new Dimension(500,10));
        barPanel.add(bar);
        barPanel.setBackground((new Color(66,28,82)));
        volume.setPreferredSize(new Dimension(200,10));
        playButtons.add(prev);
        playButtons.add(pause);
        playButtons.add(next);
        playButtons.add(shuffle);

        songbar.add(playButtons,NORTH);
        songbar.add(barPanel,CENTER);
        volume.setBackground((new Color(66,28,82)));
        this.add(volume, EAST);
    }
}
