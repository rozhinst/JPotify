package App;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
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

        JSeparator sep = new JSeparator();





        add(label);
        add(addToLibrary);
        add(album);
        add(song);
        add(sep);
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
public void reValidateAlbums(){
        middlePage.getSongsInLibrary().removeAll();
}
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==song){
                Songs.orderingSongs(SongbarGUI.getFilePath());
              //  middlePage.
               // middlePage.getSongsInLibrary().revalidate();
                reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\App\\songs\\song.txt");
                middlePage.getSongsInLibrary().setVisible(true);
            }
            if(e.getSource()==album){
                Songs.orderingSongs(SongbarGUI.getFilePath());
                middlePage.getSongsInLibrary().setVisible(false);
              //  middlePage.getSongsInLibrary().revalidate();
                middlePage.getAlbumsInLibrary().setVisible(true);
                //reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.txt");
            }
            if(e.getSource() ==  addToLibrary ){
                Songs song = new Songs();
                boolean isNull = false;
                ArrayList a = (ArrayList) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\App\\songs\\song.txt");
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
                song.writeToFile(song.getSongArrays(),"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\App\\songs\\song.txt");
                //playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                SongbarGUI.setFilePath((ArrayList) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\App\\songs\\song.txt"));
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
                reValidateMiddlePage("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\App\\songs\\song.txt");


                //playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");

            }

        }
    }

}
