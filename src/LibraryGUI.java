

import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JPanel{
    private JButton song;
    private JButton album;
    private JButton addToLibrary;
    private JButton playlist;
    public LibraryGUI(){
        super();
        JLabel label = new JLabel("Library");
        label.setPreferredSize(new Dimension(90,60));
        setLayout(new GridLayout(8,1));
        song = new JButton();
        album = new JButton("Albums");
        playlist = new JButton("PlayList");
        addToLibrary = new JButton("AddToLibrary");
        song.setPreferredSize(new Dimension(90,60));
        album.setPreferredSize(new Dimension(90,60));
        addToLibrary.setPreferredSize(new Dimension(90,60));
        song.setIcon(new ImageIcon(getClass().getResource("hi.png")));
        //JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,album,song );
        //song.setSize(100,100);
        //album.setPreferredSize(new Dimension(100,100));
        JSeparator sep = new JSeparator();
        //  song.setBackground(Color.getHSBColor(1,1,0.5f));
        add(label);
        add(addToLibrary);
        add(album);
        add(song);
        add(playlist);
        add(sep);

    }

}
