package App;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MiddlePage extends JPanel {
    private Border emptyBorder;
    private static GetID3 id3;
    private static ArrayList fileOfSongs;
    private static ArrayList<Albums> fileOfAlbums;
    private ArrayList<Song> songArrayList;
  //  private ArrayList<S>
    private static ArrayList<JButton> songs;
    private static ArrayList<JButton> albums;
    private ArrayList<JButton> songsOfAlbum;
    private String songName;
    //private
    private ArrayList<String> songPaths;
    private String source;
    private static Songs songsInLib;
    private static JPanel songsInLibrary;
    private JPanel albumsInLibrary;
    private JPanel songsInAlbum;
    private JPanel songinPlaylist;
    private static JTextArea details;
    private Albums album1;
    private Handler handler;
   // private DisplaySongs displaySongs;


    public MiddlePage() throws InvalidDataException, IOException, UnsupportedTagException {

        //this.setPreferredSize(new Dimension(200,200));
        emptyBorder = BorderFactory.createEmptyBorder();
        songinPlaylist = new JPanel();
        songsInLibrary = new JPanel();
        albumsInLibrary = new JPanel();
        songsInAlbum = new JPanel();
      //  displaySongs = new DisplaySongs();

        //panel.setBackground(new Color(0,0,0,0))

        songsInLibrary.setBackground(new Color(0, 0, 0, 0));
        albumsInLibrary.setBackground(new Color(0, 0, 0, 0));
        songsInAlbum.setBackground(new Color(0, 0, 0, 0));
        // setLayout( new FlowLayout(FlowLayout.LEFT,15,20));
        //songsInLibrary.setLayout( new FlowLayout(FlowLayout.CENTER,15,20));
        songsInLibrary.setVisible(true);
        albumsInLibrary.setVisible(false);
        songsInAlbum.setVisible(false);
        songsInLibrary.setLayout(new GridLayout(0, 4, 10, 10));
        albumsInLibrary.setLayout(new GridLayout(0, 4, 10, 10));
        songsInAlbum.setLayout(new GridLayout(0, 4, 10, 10));

        addSongsToLibraryPannel("src\\App\\songs\\song.bin");
        this.add(songsInLibrary);
        revalidate();

       // addAlbumsToLibraryPanel("src\\songs\\Albums.bin");
        this.add(albumsInLibrary);


        //addAlbumsToLibraryPanel("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");


    }

    public void addAlbumsToLibraryPanel(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        Object obj = Songs.reafAlbumsFromFile(path);
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
        source = "album";
        for (int i = 0; i < albums.size(); i++) {
            albums.get(i).addActionListener(handler);

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
            albums.get(i).setBackground(new Color(20,20,20));
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
        source = "song";
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

    public void showSongsOfLibrary(Albums album) throws InvalidDataException, IOException, UnsupportedTagException {
       songsOfAlbum = new ArrayList<>(album.getAlbumSongs().size());
        for (int i = 0; i < album.getAlbumSongs().size(); i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songsOfAlbum.add(button);
        }
        source = "songOfAlbum";
        Handler handler = new Handler();
        for (int i = 0; i < songsOfAlbum.size(); i++) {
            songsOfAlbum.get(i).addActionListener(handler);
        }
        for (int i = 0; i < album.getAlbumSongs().size(); i++) {
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) album.getAlbumSongs().get(i);
            id3 = new GetID3(song.getPath());
            Image newImage = id3.getImg().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            for (int j = 0; j < id3.getDetails().size() - 2; j++) {
                // temp += id3.getDetails().get(i);
                details.append(id3.getDetails().get(j));
                details.append("\n");

            }

            JLabel artWork = new JLabel();
            artWork.setIcon(new ImageIcon(newImage));
            songsOfAlbum.get(i).add(artWork);
            songsOfAlbum.get(i).add(details);

            songsOfAlbum.get(i).setBackground(new Color(20, 20, 20));

            songsInAlbum.add(songsOfAlbum.get(i));
        }
        songsInAlbum.setVisible(true);
        this.add(songsInAlbum);

        this.revalidate();


    }

    public JPanel getSongsInLibrary() {
        return songsInLibrary;
    }

    public JPanel getAlbumsInLibrary() {
        return albumsInLibrary;
    }

    public JPanel getSongsInAlbum() {
        return songsInAlbum;
    }

    public class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(source.equals("song")) {
                for (int i = 0; i < fileOfSongs.size(); i++) {
                    if (e.getSource() == songs.get(i) ) {
                        System.out.println("e peresssed  " + i);
                        SongbarGUI.setSongNum(i);
                        SongbarGUI.setFilePath( fileOfSongs);

                        SongbarGUI.nextOrPrev();
                        ((Song)fileOfSongs.get(i)).setTimePlayed();
                        try {
                            Songs.orderingSongs();
                            repaint();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }

            if(source.equals("album")){
                songName = new String();
                System.out.println("Albummmmmmmmmmmmmmmmmmmmmmmmm");
                album1 = new Albums();
                songArrayList=new ArrayList<>();
            for (int i = 0; i <fileOfAlbums.size() ; i++) {
                if(e.getSource()==albums.get(i) ) {
                    System.out.println(i);
                    try {
                       // songsInAlbum.removeAll();
                        songsInAlbum.removeAll();
                        songinPlaylist.removeAll();
                        songsInLibrary.removeAll();
                        albumsInLibrary.removeAll();
                        showSongsOfLibrary(fileOfAlbums.get(i));
                         album1 = fileOfAlbums.get(i);
                         songArrayList = album1.getAlbumSongs();
                        System.out.println(fileOfAlbums.get(i).getName());


                    } catch (InvalidDataException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedTagException ex) {
                        ex.printStackTrace();
                    }
                }
                    /*
                    for (int j = 0; j <fileOfAlbums.get(i).getAlbumSongs().size() ; j++) {
                        JButton button = new JButton();
                        button.setLayout(new GridLayout(2, 1));
                        details = new JTextArea();
                        details.setBackground(new Color(20, 20, 20));
                        details.setForeground(Color.WHITE);
                        Song song = (Song) fileOfAlbums.get(i).getAlbumSongs().get(j);
                        id3 = new GetID3(song.getPath());
                        Image newImage = id3.getImg().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        for (int k = 0; k < id3.getDetails().size() - 2; k++) {
                            // temp += id3.getDetails().get(i);
                            details.append(id3.getDetails().get(j));
                            details.append("\n");

                        }
                        JLabel label = new JLabel();
                        label.setIcon(new ImageIcon(newImage));
                    }

                     */
                }
            }
            if(source.equals("songOfAlbum")) {
                int index = 0;
                songName = new String();
                for (int j = 0; j < songsOfAlbum.size(); j++) {
                    if (e.getSource() == songsOfAlbum.get(j)) {
                       String tmp = ((JTextArea)((JButton)songsInAlbum.getComponent(j)).getComponent(1)).getToolTipText();
                        System.out.println(tmp+"**********************************");
                        System.out.println(j);
                        for(int k=0;k<fileOfSongs.size();k++){
                            if (((Song)(fileOfSongs.get(k))).getName().equals(((Song)(songArrayList.get(j))).getName())){
                                index= j;
                                System.out.println(((Song)(fileOfSongs.get(k))).getName());
                                songName = ((Song)(fileOfSongs.get(k))).getName();
//                                SongbarGUI.setSongNum(k);
//
//                                SongbarGUI.nextOrPrev();
                            }
                        }

                        //System.out.println("e peresssed  " + j);


                    }
                }

                for (int i = 0; i <fileOfSongs.size() ; i++) {
                    if(((Song)(fileOfSongs.get(i))).getName()==songName){
                        System.out.println(songName+" songName");
                        System.out.println(((Song)(fileOfSongs.get(i))).getName()+" fileOfSong");


//                        System.out.println(i);
//                       MP3 id33 = ;

                      //  SongbarGUI.setFilePath(songsOfAlbum);
                       // System.out.println(fileOfSongs.);
                        SongbarGUI.setSongNum(index);
                        SongbarGUI.setFilePath(songArrayList);
                        SongbarGUI.nextOrPrev();
                        ((Song)(fileOfSongs.get(i))).setTimePlayed();
                        try {
                            Songs.orderingSongs();

                            repaint();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
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

