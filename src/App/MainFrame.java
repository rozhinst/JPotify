package App;
import Network.Client;
import Network.Server;
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
    private JButton albumsButton;
    private JPanel searchPanel;
    private JLabel lable;
    public static Client client;
    public static String name;
    private FriendsActivityLogic friendsActivityLogic;

    public MainFrame() throws IOException, InvalidDataException, UnsupportedTagException {
        super();
        client = new Client(this);
        client.setName("ro");
        name = client.getName();
        Server.clients.put(name,client);
        new Thread(client).start();
        lable = new JLabel(" Friends activity");
        lable.setBorder( BorderFactory.createEmptyBorder());
        lable.setBackground(new Color(20,20,20));
        lable.setForeground(Color.WHITE);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.getContentPane().setBackground(new Color(20,20,20) );
        searchPanel = new SearchPanel();
        library = new LibraryGUI();
        library.setBorder(emptyBorder);
        songbar = new SongbarGUI();
        songbar.setBorder(emptyBorder);
        middlePage = new MiddlePage();
        library.setMiddlePage(middlePage);
        middlePage.setBorder(emptyBorder);
        friendsActivity = FriendsActivityGUI.getInstance();
        friendsActivityLogic = FriendsActivityLogic.getLogicInstance();
        friendsActivity.setBorder(emptyBorder);
        new Thread(friendsActivityLogic).start();
        library.setPreferredSize(new Dimension(170,500));
        this.add(new JScrollPane(library),BorderLayout.WEST);
        this.add(new JScrollPane(songbar),BorderLayout.PAGE_END);
        this.add(new JScrollPane(friendsActivity),BorderLayout.EAST);
        this.add(new JScrollPane(middlePage),BorderLayout.CENTER);
        this.add(new JScrollPane(searchPanel),BorderLayout.NORTH);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        this.pack();
        setVisible(true);

    }
    public FriendsActivityLogic getFriendsActivityLogic(){
        return friendsActivityLogic;
    }
}
class Main{
    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        MainFrame frame = new MainFrame();
        SearchPanel.middlePage=(LibraryGUI.middlePage);
    }
}
