import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

        add(label);
        add(addToLibrary);
        add(album);
        add(song);
        add(sep);

    }

}
