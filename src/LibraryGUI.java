import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LibraryGUI extends JPanel{
    private JButton song;
    private JButton album;
    private JButton addToLibrary;
    private JComboBox<Object> comboBox;
    private JPanel playlistPannel;
    private JLabel playlistLabel;
    private MiddlePage middlePage;
    private Handler handler;
    private DisplaySongs display;
    public LibraryGUI(){
        super();
        display = new DisplaySongs();
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Handler handler = new Handler();

        this.setBackground(new Color(20,20,20));
        //this.setBackground(new Color(150,0,205));
        JLabel label = new JLabel(" Library");
        label.setBorder(emptyBorder);
        label.setForeground(Color.GRAY);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setPreferredSize(new Dimension(150,60));
        setLayout(new GridLayout(8,1));
        this.setBorder(emptyBorder);

        song = new JButton("Songs");
        album = new JButton("Albums");
        playlistPannel = new JPanel();
        playlistLabel = new JLabel("Playlist");
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
        addToLibrary.setForeground(Color.WHITE);
        addToLibrary.setBorder(emptyBorder);
        addToLibrary.setBackground(new Color(20,20,20));
        addToLibrary.setFocusable(false);
        song.setPreferredSize(new Dimension(90,60));
        album.setPreferredSize(new Dimension(90,60));
        addToLibrary.setPreferredSize(new Dimension(90,60));
        playlistLabel.setBorder(new EmptyBorder(0,5,0,0));
        playlistLabel.setPreferredSize(new Dimension(90,35));

        JSeparator sep = new JSeparator();





        add(label);
        add(addToLibrary);
        add(album);
        add(song);
        add(sep);
        //add(playlistPannel);
        //playlistPannel.add(playlistLabel);

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
public void reValidateAlbums() throws InvalidDataException, IOException, UnsupportedTagException {
       // middlePage.getSongsInLibrary().removeAll();
        middlePage.addAlbumsToLibraryPanel("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
        middlePage.revalidate();
}
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==song){
                Songs.orderingSongs(SongbarGUI.getFilePath());
              //  middlePage.
               // middlePage.getSongsInLibrary().revalidate();
                reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");
                middlePage.getSongsInLibrary().setVisible(true);
            }
            if(e.getSource()==album){
                middlePage.getSongsInLibrary().setVisible(false);
              //  middlePage.getSongsInLibrary().revalidate();
                // middlePage.getSongsInLibrary().removeAll();
                Songs.orderingSongs(SongbarGUI.getFilePath());
                try {
                    middlePage.addAlbumsToLibraryPanel("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
                } catch (InvalidDataException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
                middlePage.revalidate();

                //Songs.orderingSongs(SongbarGUI.getFilePath());
                middlePage.getAlbumsInLibrary().setVisible(true);
                //reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
            }
            if(e.getSource() ==  addToLibrary ){
                Songs song = new Songs();
                boolean isNull = false;
                ArrayList<Song> a = null;
                try {
                    a = song.reafSongsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // ArrayList name = (ArrayList) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                if(a==null){
                    a = new ArrayList();
                    isNull = true;
                }
                try {
                     //songToAlbum = new Song();
                    Song songToAlbum = song.addSong(a);
                    display.managingAlbumSongs(songToAlbum);

                    System.out.println("hey");
                } catch (InvalidDataException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
                song.writeSongsToFile(song.getSongArrays(),"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");
                //playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                try {
                    SongbarGUI.setFilePath( song.reafSongsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
                reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");


                //playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");

            }

        }
    }

}
