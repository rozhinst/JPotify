
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final String WINDOWS_TITLE = "JPotify";
    private final int WIDTH = 1000;
    private final int HEIGHT = 650;
    private final int X = 100,Y = 100;
    private LibraryGUI library;
    private SongbarGUI songbar;
    private FriendsActivityGUI friendsActivity;
    private MiddlePage middlePage;
    private JButton albumsButton;

    public MainFrame() throws IOException, InvalidDataException, UnsupportedTagException {
        super();
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        library = new LibraryGUI();
        songbar = new SongbarGUI();
        middlePage = new MiddlePage();
        friendsActivity = new FriendsActivityGUI();
        library.setPreferredSize(new Dimension(120,80));
        this.add(new JScrollPane(library),BorderLayout.WEST);
        this.add(new JScrollPane(songbar),BorderLayout.PAGE_END);
        this.add(new JScrollPane(friendsActivity),BorderLayout.EAST);
        this.add(new JScrollPane(middlePage),BorderLayout.CENTER);
        setVisible(true);

    }
}
class Main{
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        MainFrame frame = new MainFrame();
    }
}
