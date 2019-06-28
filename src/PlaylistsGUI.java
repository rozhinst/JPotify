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
    private  int size2;
    private  ArrayList songs;
    private Handler handler;



    // private ArrayList <JButton> playlistsButtons;
    //  private JList <String> list;
    private static MiddlePage middlePage;


    public static void setMiddlePage(MiddlePage middlePage) {
        PlaylistsGUI.middlePage = middlePage;
    }


    public PlaylistsGUI(PlayList playlist) throws InvalidDataException, IOException, UnsupportedTagException {
        this.playlist = playlist;
        handler = new Handler();

       this.removeAll();
//        this.revalidate();

        if(!playlist.getName().equals("Favorite") && !playlist.getName().equals("Shared"))
         setMiddlePageButtons(playlist);
        else if(playlist.getName().equals("Shared")) setMiddleForShared();
        else setMiddleForFavorite();



    }
    public void setMiddleForShared() throws IOException, InvalidDataException, UnsupportedTagException {
        this.removeAll();
        middlePage.revalidate();
        this.setLayout(new GridLayout(0, 4, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));

        songs = Songs.reafSongsFromFile("src\\playlists\\Shared.bin");
        if(songs==null) songs = new ArrayList();
         size2 = songs.size();

        songButtons = new ArrayList<>(size2);
        for (int i = 0; i < size2; i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songButtons.add(button);
        }

        for (int i = 0; i < size2; i++) {
            songButtons.get(i).addActionListener(handler);
        }


        for (int i = 0; i < size2; i++) {
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) songs.get(i);
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
        if(size2==0){
            this.removeAll();
            middlePage.removeAll();
            middlePage.repaint();

        }
        this.add(addSong);
        middlePage.getAlbumsInLibrary().removeAll();
        middlePage.getSongsInLibrary().removeAll();
        middlePage.getSongsInAlbum().removeAll();
        middlePage.add(this);
        middlePage.revalidate();



    }

    public void setMiddleForFavorite() throws IOException, InvalidDataException, UnsupportedTagException {
        this.removeAll();
        middlePage.revalidate();
        this.setLayout(new GridLayout(0, 4, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));

         songs = Songs.reafSongsFromFile("src\\playlists\\Favorite.bin");
         if(songs==null) songs = new ArrayList();
          size2 = songs.size();
         songButtons = new ArrayList<>(size2);

        for (int i = 0; i < size2; i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songButtons.add(button);
        }

        for (int i = 0; i < size2; i++) {
            songButtons.get(i).addActionListener(handler);
        }


        for (int i = 0; i < size2; i++) {
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) songs.get(i);
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
        if(size2==0){
            this.removeAll();
            middlePage.removeAll();
            middlePage.repaint();

        }
        this.add(addSong);
        middlePage.getAlbumsInLibrary().removeAll();
        middlePage.getSongsInLibrary().removeAll();
        middlePage.getSongsInAlbum().removeAll();
        middlePage.add(this);
        middlePage.revalidate();




    }
    public void setMiddlePageButtons(PlayList playlist) throws InvalidDataException, IOException, UnsupportedTagException {
        this.removeAll();
        middlePage.revalidate();
        this.setLayout(new GridLayout(0, 4, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));

         size2 = playlist.getPlayListSongs().size();
        songs = playlist.getPlayListSongs();
        songButtons = new ArrayList<>(size2);
        for (int i = 0; i < size2; i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songButtons.add(button);
        }

        for (int i = 0; i < size2; i++) {
            songButtons.get(i).addActionListener(handler);
        }


        for (int i = 0; i < size2; i++) {
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
        if(size2==0){
            this.removeAll();
            middlePage.removeAll();
            middlePage.repaint();

        }
        this.add(addSong);
         middlePage.getAlbumsInLibrary().removeAll();
         middlePage.getSongsInLibrary().removeAll();
         middlePage.getSongsInAlbum().removeAll();
        middlePage.add(this);
        middlePage.revalidate();
    }


    public class Handler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < size2; i++) {
                if (e.getSource() == songButtons.get(i)) {
                    System.out.println("this is i"+i);
                    ArrayList songs2 = new ArrayList();
                    try {
                        songs2 = Songs.reafSongsFromFile("src\\songs\\song.bin");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    for (int j = 0; j <songs2.size() ; j++) {
                        if(((Song)(songs2.get(j))).getName().equals( ((Song)songs.get(i)).getName())){
                            SongbarGUI.setSongNum(j);


                            SongbarGUI.nextOrPrev();
                            ((Song)songs2.get(j)).setTimePlayed();
                        }
                    }

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
                            if (!playlist.getName().equals("Favorite") && !(playlist.getName().equals("Shared"))) {
                                System.out.println(finalI + "****************************************");
                                try {

                                    playlist.addSongs(finalI);

                                    ArrayList playlists = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
                                    if (playlists == null) playlists = new ArrayList();
                                    for (int j = 0; j < playlists.size(); j++) {
                                        if (((PlayList) playlists.get(j)).getName().equals(playlist.getName())) {
                                            playlists.set(j, playlist);
                                            Songs.writeLibrariesToFile(playlists, "src\\playlists\\playlists.bin");
                                            setMiddlePageButtons(playlist);
                                            middlePage.repaint();
                                            frame.setVisible(false);
                                        }
                                    }


                                } catch (IOException | InvalidDataException | UnsupportedTagException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if(playlist.getName().equals("Shared")){
                                try {
                                  //  playlist.addSongs(finalI);
                                    ArrayList songs;
                                     songs = Songs.reafSongsFromFile("src\\playlists\\Shared.bin");
                                    if(songs==null)  songs = new ArrayList();
                                    ArrayList songsInLibrary = Songs.reafSongsFromFile("src\\songs\\song.bin");
                                    for (int j = 0; j <songsInLibrary.size() ; j++) {
                                        if(finalI==j){
                                            songs.add(songsInLibrary.get(j));
                                            Songs.writeSongsToFile(songs,"src\\playlists\\Shared.bin");
                                           // setMiddlePageButtons(playlist);
                                            setMiddleForShared();
                                            middlePage.repaint();
                                            frame.setVisible(false);
                                        }
                                    }

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (InvalidDataException ex) {
                                    ex.printStackTrace();
                                } catch (UnsupportedTagException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else{
                                try {
                                    //  playlist.addSongs(finalI);

                                    ArrayList songs;
                                    songs = Songs.reafSongsFromFile("src\\playlists\\Favorite.bin");
                                    if(songs==null)  songs = new ArrayList();
                                    ArrayList songsInLibrary = Songs.reafSongsFromFile("src\\songs\\song.bin");
                                    for (int j = 0; j <songsInLibrary.size() ; j++) {
                                        if(finalI==j){
                                            songs.add(songsInLibrary.get(j));
                                            Songs.writeSongsToFile(songs,"src\\playlists\\Favorite.bin");
                                            //setMiddlePageButtons(playlist);
                                            setMiddleForFavorite();
                                            frame.setVisible(false);
                                        }
                                    }

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (InvalidDataException ex) {
                                    ex.printStackTrace();
                                } catch (UnsupportedTagException ex) {
                                    ex.printStackTrace();
                                }

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
