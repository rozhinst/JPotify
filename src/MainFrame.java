
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final String WINDOWS_TITLE = "JPotify";
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int X = 100,Y = 100;
    private LibraryGUI library;
    private SongbarGUI songbar;
    private FriendsActivityGUI friendsActivity;
    private MiddlePage middlePage;
    private JButton albumsButton;

    public MainFrame(){

        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.getContentPane().setBackground(new Color(66,28,82) );
        library = new LibraryGUI();
        songbar = new SongbarGUI();
        middlePage = new MiddlePage();
        friendsActivity = new FriendsActivityGUI();
        library.setPreferredSize(new Dimension(120,80));
        this.add(new JScrollPane(library),BorderLayout.WEST);
        this.add(new JScrollPane(songbar),BorderLayout.PAGE_END);
        this.add(new JScrollPane(friendsActivity),BorderLayout.EAST);
        this.add(new JScrollPane(middlePage),BorderLayout.CENTER);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
}
class Main{
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
