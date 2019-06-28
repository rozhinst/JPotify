import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * GUI of the playlist that will be displayed after pushing the playlist button
 */
public class PlaylistsGUI extends JPanel {

    private GetID3 id3;
    //  private JLabel label;
    private Border emptyBorder;
    private PlayList playlist;

    private ArrayList<JButton> songButtons;
    private JTextArea details;
    private JButton addSong;
    Handler handler;


    // private ArrayList <JButton> playlistsButtons;
    //  private JList <String> list;
    private static MiddlePage middlePage;


    public static void setMiddlePage(MiddlePage middlePage) {
        PlaylistsGUI.middlePage = middlePage;
    }

    public PlaylistsGUI(PlayList playlist) throws InvalidDataException, IOException, UnsupportedTagException {
        this.setLayout(new GridLayout(0, 4, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));
        this.playlist = playlist;
        int size = playlist.getPlayListSongs().size();
        songButtons = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songButtons.add(button);
        }
        Handler handler = new Handler();
        for (int i = 0; i < size; i++) {
            songButtons.get(i).addActionListener(handler);
        }

        for (int i = 0; i < size; i++) {
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) playlist.getPlayListSongs().get(i);
            id3 = new GetID3(song.getPath());
            Image newImage = id3.getImg().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            for (int j = 0; j < id3.getDetails().size() - 2; j++) {

                details.append(id3.getDetails().get(j));
                details.append("\n");

            }

            JLabel artWork = new JLabel();
            artWork.setIcon(new ImageIcon(newImage));
            songButtons.get(i).add(artWork);
            songButtons.get(i).add(details);

            songButtons.get(i).setBackground(new Color(20, 20, 20));

            this.add(songButtons.get(i));
        }
        addSong = new JButton("add new song");
        addSong.setBackground(new Color(20, 20, 20));
        addSong.setPreferredSize(new Dimension(120, 200));
        addSong.setForeground(Color.WHITE);
        addSong.addActionListener(handler);
        this.add(addSong);
        if (middlePage.getAlbumsInLibrary() != null) middlePage.getAlbumsInLibrary().removeAll();
        if (middlePage.getSongsInLibrary() != null) middlePage.getSongsInLibrary().removeAll();
        if (middlePage.getSongsInAlbum() != null) middlePage.getSongsInAlbum().removeAll();
        middlePage.add(this);
        middlePage.revalidate();

    }


    public class Handler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < playlist.getPlayListSongs().size(); i++) {
                if (e.getSource() == playlist.getPlayListSongs().get(i)) {
                    SongbarGUI.setSongNum(i);

                    SongbarGUI.nextOrPrev();

                }
            }
            if (e.getSource() == addSong) {
                JFrame frame = new JFrame("add new song to playlist");
                frame.setSize(900,700);
                JPanel panel = new JPanel();
                panel.setBackground(new Color(20,20,20));
                frame.setBackground(new Color(20,20,20));
                panel.setLayout(new GridLayout(0, 4, 10, 10));
                panel.setBackground(new Color(0,0,0,0));
                ArrayList fileOfSongs = null;
                try {
                    fileOfSongs = (ArrayList) Songs.reafSongsFromFile("src\\songs\\song.bin");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (fileOfSongs == null) fileOfSongs = new ArrayList();
                ArrayList<JButton> songs = new ArrayList<>(fileOfSongs.size());
                for (int i = 0; i < fileOfSongs.size(); i++) {
                    JButton button = new JButton();
                    button.setLayout(new GridLayout(2, 1));
                    songs.add(button);
                }
             //   Handler2 handler2 = new Handler2();

                for (int i = 0; i < songs.size(); i++) {
                    int finalI = i;
                    songs.get(i).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println(finalI+"****************************************");
                            try {

                                playlist.addSongs(finalI);
                               ///////////////////////////////////////////////////////////////////////////////////////////////here!!////////////////////////////////////////////////////////////////////////


                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });
                }

                for (int i = 0; i < fileOfSongs.size(); i++) {
                    details = new JTextArea();
                    details.setBackground(new Color(20, 20, 20));
                    details.setForeground(Color.WHITE);
                    Song song = (Song) fileOfSongs.get(i);
                    try {
                        id3 = new GetID3(song.getPath());
                    } catch (InvalidDataException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedTagException ex) {
                        ex.printStackTrace();
                    }
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

                  panel.add(songs.get(i));
                }
                frame.add(panel);
                frame.setVisible(true);


            }


        }
    }

}
