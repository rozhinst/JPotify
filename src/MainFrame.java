
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
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
    private PlayListGUI playList;
    private JButton albumsButton;
    private SearchPanel searchPanel;

    public MainFrame() throws IOException, InvalidDataException, UnsupportedTagException {
        super();
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.getContentPane().setBackground(new Color( 20,20,20) );
        searchPanel = new SearchPanel();
        // searchPanel.setMiddlePage(middlePage);
        library = new LibraryGUI();
        library.setBorder(emptyBorder);
       // playList = new PlayListGUI();
       // playList.setBorder(emptyBorder);
        songbar = new SongbarGUI();
        songbar.setBorder(emptyBorder);
        middlePage = new MiddlePage();
        library.setMiddlePage(middlePage);
        middlePage.setBorder(emptyBorder);
        friendsActivity = new FriendsActivityGUI();
        friendsActivity.setBorder(emptyBorder);
        library.setPreferredSize(new Dimension(170,500));
        this.add(new JScrollPane(library),BorderLayout.WEST);
       // this.add(new JScrollPane(playList),BorderLayout.WEST);
        this.add(new JScrollPane(songbar),BorderLayout.PAGE_END);
        this.add(new JScrollPane(friendsActivity),BorderLayout.EAST);
        this.add(new JScrollPane(middlePage),BorderLayout.CENTER);
        this.add(new JScrollPane(searchPanel),BorderLayout.NORTH);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

         */
        this.pack();
        setVisible(true);

    }
}
class Main{
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        MainFrame frame = new MainFrame();
        SearchPanel.middlePage=(LibraryGUI.middlePage);
    }
}
