import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MiddlePage extends JPanel {
    private Border emptyBorder;
    private static GetID3 id3;
    private static ArrayList fileOfSongs;
    private static ArrayList<Albums> fileOfAlbums;
    private static ArrayList<JButton> songs;
    private static ArrayList<JButton> albums;
    //private
    private ArrayList<String> songPaths;
    private static Songs songsInLib;
    private static JPanel songsInLibrary;
    private JPanel albumsInLibrary;
    private JPanel songinPlaylist;
    private static JTextArea details;
    private Handler handler;


    public MiddlePage() throws InvalidDataException, IOException, UnsupportedTagException {

        //this.setPreferredSize(new Dimension(200,200));
        emptyBorder = BorderFactory.createEmptyBorder();
        songinPlaylist = new JPanel();
        songsInLibrary = new JPanel();
        albumsInLibrary = new JPanel();

        //panel.setBackground(new Color(0,0,0,0))

        songsInLibrary.setBackground(new Color(0, 0, 0, 0));
        // setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        //songsInLibrary.setLayout( new FlowLayout(FlowLayout.CENTER,15,20));
        songsInLibrary.setVisible(true);

        songsInLibrary.setLayout(new GridLayout(0, 4, 10, 10));

        addSongsToLibraryPannel("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");
        this.add(songsInLibrary);
        revalidate();


        albumsInLibrary.setBackground(new Color(0, 0, 0, 0));
        albumsInLibrary.setLayout(new GridLayout(0, 4, 10, 10));
        //addAlbumsToLibraryPanel("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");


    }

    public void addAlbumsToLibraryPanel(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        Object obj = Songs.reafAlbumsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
        System.out.println(obj instanceof Albums);
        fileOfAlbums = (ArrayList<Albums>) obj;
        if (fileOfAlbums == null) {
            fileOfAlbums = new ArrayList();
        }
        albums = new ArrayList<>(fileOfAlbums.size());
        for (int i = 0; i < fileOfAlbums.size(); i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            albums.add(button);
        }
        Handler handler = new Handler();
        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).addActionListener(handler);
        }
        for (int i = 0; i < fileOfAlbums.size(); i++) {
            Albums album = fileOfAlbums.get(i);
            System.out.println(fileOfAlbums.size());
            System.out.println(album.getName());
            System.out.println(album.getAlbumSongs().size() + "   kkkkkk");
            Song song = (Song) album.getAlbumSongs().get(0);
            System.out.println(song.getName());
            GetID3 id3 = new GetID3(song.getPath());
            Image newImage = id3.getImg().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel artWork = new JLabel();
            artWork.setIcon(new ImageIcon(newImage));
            albums.get(i).add(artWork);
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            details.append(album.getName());
            albums.get(i).add(details);
            System.out.println("album: " + albums.get(i));
            albumsInLibrary.add(albums.get(i));
        }
        //Songs.writeToFile(fileOfAlbums,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
    }

    public void addSongsToLibraryPannel(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        fileOfSongs = (ArrayList) Songs.reafSongsFromFile(path);
        if (fileOfSongs == null) fileOfSongs = new ArrayList();
        songs = new ArrayList<>(fileOfSongs.size());
        for (int i = 0; i < fileOfSongs.size(); i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songs.add(button);
        }
        Handler handler = new Handler();
        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).addActionListener(handler);
        }
        for (int i = 0; i < fileOfSongs.size(); i++) {
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) fileOfSongs.get(i);
            id3 = new GetID3(song.getPath());
            Image newImage = id3.getImg().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            for (int j = 0; j < id3.getDetails().size() - 2; j++) {
                // temp += id3.getDetails().get(i);
                details.append(id3.getDetails().get(j));
                details.append("\n");

            }

            JLabel artWork = new JLabel();
            artWork.setIcon(new ImageIcon(newImage));
            songs.get(i).add(artWork);
            songs.get(i).add(details);

            songs.get(i).setBackground(new Color(20, 20, 20));

            songsInLibrary.add(songs.get(i));
        }


    }

    public JPanel getSongsInLibrary() {
        return songsInLibrary;
    }

    public JPanel getAlbumsInLibrary() {
        return albumsInLibrary;
    }

    public class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < fileOfSongs.size(); i++) {
                if (e.getSource() == songs.get(i)) {
                    System.out.println("e peresssed  " + i);
                    SongbarGUI.setSongNum(i);

                    SongbarGUI.nextOrPrev();

                }
            }
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

