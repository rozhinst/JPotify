import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class SongbarGUI extends JPanel {
    private JButton prev;
    private JButton pause;
    private JButton next;
    private JButton shuffle;
    private JPanel playButtons;
    private JLabel songbar;
    private JPanel progressPanel;
    private JProgressBar bar;
    private JSlider volume;
    private JLabel artwork;
    public SongbarGUI() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(20,100));
        playButtons = new JPanel();
        progressPanel = new JPanel();
        artwork = new JLabel("artwork");
        playButtons.setLayout( new FlowLayout(FlowLayout.CENTER,10,3));
        progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        add(playButtons, NORTH);
        add(progressPanel,CENTER);
        add(artwork,WEST);
        progressPanel.setBorder(new EmptyBorder(10,10,10,10));
        playButtons.setBorder(new EmptyBorder(10,10,10,10));
        artwork.setBorder(new EmptyBorder(10,10,10,10));
        prev = new JButton("previous");
        next = new JButton( "next");
        pause = new JButton("pause/play");
        shuffle = new JButton("shuffle");
        bar = new JProgressBar();
        volume = new JSlider();
        bar.setPreferredSize(new Dimension(200,10));
        volume.setPreferredSize(new Dimension(80,10));
        playButtons.add(prev);
        playButtons.add(pause);
        playButtons.add(next);
        playButtons.add(shuffle);
        progressPanel.add(bar);
        progressPanel.add(volume);
    }
}
