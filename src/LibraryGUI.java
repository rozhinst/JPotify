import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryGUI extends JPanel{
    private JButton song;
    private JButton album;
    private JButton addToLibrary;
    private JComboBox<Object> comboBox;
    public LibraryGUI(){
        super();
        Border emptyBorder = BorderFactory.createEmptyBorder();


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
        album.setBackground(new Color(20,20,20));
        album.setBorder(emptyBorder);
        album.setForeground(Color.WHITE);
        addToLibrary = new JButton("AddToLibrary");
        addToLibrary.setForeground(Color.WHITE);
        addToLibrary.setBorder(emptyBorder);
        addToLibrary.setBackground(new Color(20,20,20));
        song.setPreferredSize(new Dimension(90,60));
        album.setPreferredSize(new Dimension(90,60));
        addToLibrary.setPreferredSize(new Dimension(90,60));

        JSeparator sep = new JSeparator();

        Handler handler = new Handler();

        add(label);
        add(addToLibrary);
        add(album);
        add(song);
        add(sep);
        addToLibrary.addActionListener(handler);

    }
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() ==  addToLibrary ){
                Songs playList = new Songs();
                ArrayList a = (ArrayList) playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.txt");
                ArrayList name = (ArrayList) playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                if(a==null)  a = new ArrayList();
                if(name == null) name = new ArrayList();
                try {
                    playList.addSong(a,name);
                } catch (InvalidDataException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
                playList.writeToFile(a,"src\\songs\\song.txt");
                playList.writeToFile(name,"src\\songs\\SongNames.txt");
                SongbarGUI.setFilePath((ArrayList) playList.reafFromFile("src\\songs\\song.txt"));
                //playList.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");

            }
        }
    }

}
