import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.Separator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LibraryGUI extends JPanel {

    private ImageIcon newPlaylistIcon;
    private ImageIcon songIcon;
    private ImageIcon albumIcon;
    private JButton song;
    private JButton album;
    private JButton addToLibrary;
    private JButton favoritePlayList;
    private JButton sharedPlayist;
    private MiddlePage middlePage;
    //private PlayList playList;
    private JLabel playlist;

    private ImageIcon libraryIcon;
    private ImageIcon lineIcon;
    private JLabel line;
    private ArrayList <JButton> playlists;
    private JButton addToPlaylist;
    private Handler handler;
    public LibraryGUI(){
        super();
        PlayListGUI playList = new PlayListGUI();
        this.setLayout(new GridLayout(0,1));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Handler handler = new Handler();



        libraryIcon = new ImageIcon(new ImageIcon("src\\icons\\library.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        songIcon = new ImageIcon(new ImageIcon("src\\icons\\song.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        albumIcon = new ImageIcon(new ImageIcon("src\\icons\\album.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        newPlaylistIcon = new ImageIcon(new ImageIcon("src\\icons\\playList.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        lineIcon = new ImageIcon(new ImageIcon("src\\icons\\line.png").getImage().getScaledInstance(120, 5, Image.SCALE_DEFAULT));

        this.setBackground(new Color(20,20,20));
        //this.setBackground(new Color(150,0,205));
        JLabel label = new JLabel(" Library");
       label.setBorder(emptyBorder);
        label.setForeground(Color.GRAY);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        //label.setPreferredSize(new Dimension(150,60));
        setLayout(new GridLayout(0,1));
        this.setBorder(emptyBorder);

        line = new JLabel();
        line.setIcon(lineIcon);
        line.setBorder(emptyBorder);
        line.setPreferredSize(new Dimension(120,5));
        song = new JButton("Songs");
        song.setIcon(songIcon);
        album = new JButton("Albums");
        addToPlaylist = new JButton("New Playlist");
        addToPlaylist.setIcon(newPlaylistIcon);
        addToPlaylist.setBackground(new Color(20,20,20));
        addToPlaylist.setBorder(emptyBorder);
        addToPlaylist.setForeground(Color.WHITE);
        album.setIcon(albumIcon);
        playlist = new JLabel(" Playlist");
        playList.setPreferredSize(new Dimension(10,10));
        favoritePlayList = new JButton("Favorite");
        favoritePlayList.setBackground(new Color(20,20,20));
        favoritePlayList.setForeground(Color.WHITE);
        favoritePlayList.setBorder(emptyBorder);

        sharedPlayist = new JButton("Shared");
        sharedPlayist.setBackground(new Color(20,20,20));
        sharedPlayist.setForeground(Color.WHITE);
        sharedPlayist.setBorder(emptyBorder);

        playlist.setForeground(Color.GRAY);
        playlist.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        playlist.setPreferredSize(new Dimension(150,60));
        setLayout(new GridLayout(8,1));
        song.setBorder(emptyBorder);
        song.setBackground(new Color(20,20,20));
        song.setForeground(Color.WHITE);
        song.setFocusable(false);
        song.addActionListener(handler);
        album.setBackground(new Color(20,20,20));
        album.setBorder(emptyBorder);
        album.setForeground(Color.WHITE);
        album.setFocusable(false);
        addToLibrary = new JButton("AddToLibrary");
        addToLibrary.setIcon(libraryIcon);
        addToLibrary.setForeground(Color.WHITE);
        addToLibrary.setBorder(emptyBorder);
        addToLibrary.setBackground(new Color(20,20,20));
        addToLibrary.setFocusable(false);
        song.setPreferredSize(new Dimension(90,60));
        album.setPreferredSize(new Dimension(90,60));
        addToLibrary.setPreferredSize(new Dimension(90,60));

       JSeparator sep = new JSeparator();
     //  sep.setBorder(new EmptyBorder(0, 5, 100, 5));
//sep.setPreferredSize(new Dimension(60,0));
      //  Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));

sep.setSize(0,120);

        add(label);
        add(addToLibrary);

        add(album);

        add(song);


//add(sep);
       // add(new JSeparator(SwingConstants.HORIZONTAL));
       // add(playList);
        add(line);
        add(playlist);
        add(addToPlaylist);
        add(favoritePlayList);
       // add(sharedPlayist);
        addToLibrary.addActionListener(handler);
        album.addActionListener(handler);

    }

    public void setMiddlePage(MiddlePage middlePage) {
        this.middlePage = middlePage;
    }
    public void reValidateMiddlePage(String path){
        middlePage.getSongsInLibrary().removeAll();
        try {
            middlePage.addSongsToLibraryPannel(path);
        } catch (InvalidDataException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (UnsupportedTagException ex) {
            ex.printStackTrace();
        }
        middlePage.revalidate();
    }

    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==song){
              //  middlePage.
               // middlePage.getSongsInLibrary().revalidate();
                reValidateMiddlePage("src\\songs\\song.txt");
                middlePage.getSongsInLibrary().setVisible(true);
            }
            if(e.getSource()==album){
                middlePage.getSongsInLibrary().setVisible(false);
              //  middlePage.getSongsInLibrary().revalidate();
                middlePage.getAlbumsInLibrary().setVisible(true);
                reValidateMiddlePage("src\\songs\\song.txt");
            }
            if(e.getSource() ==  addToLibrary ){
                Songs song = new Songs();
                boolean isNull = false;
                ArrayList a = (ArrayList) song.reafFromFile("src\\songs\\song.txt");
               // ArrayList name = (ArrayList) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                if(a==null){
                    a = new ArrayList();
                    isNull = true;
                }
                //if(name == null) name = new ArrayList();
                try {
                    Song songToAlbum = song.addSong(a);
                    Albums album = Albums.manageAlbum(songToAlbum);
                    songToAlbum.setAlbum(album);
                   //if(songToAlbum == null) System.out.println("null");
                    System.out.println("hey");
                } catch (InvalidDataException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
                song.writeToFile(song.getSongArrays(),"src\\songs\\song.txt");
                //playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                SongbarGUI.setFilePath((ArrayList) song.reafFromFile("src\\songs\\song.txt"));
               // Albums.manageAlbum(songToAlbum);

                if(isNull) {
                    try {
                        SongbarGUI.newSong();
                    } catch (InvalidDataException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedTagException ex) {
                        ex.printStackTrace();
                    }
                }
                reValidateMiddlePage("src\\songs\\song.txt");


                //playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");

            }
            if(e.getSource() == album){
                Songs.orderingSongs((ArrayList<Song>) Songs.reafFromFile("src\\songs\\song.txt"));
                //reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
            }

        }
    }

}
