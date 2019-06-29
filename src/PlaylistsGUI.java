import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.tools.jconsole.JConsoleContext;

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
    private JTextArea song1;
    private JTextArea song2;

    private ArrayList<JButton> songButtons;
    private JTextArea details;
    private JButton addSong;
    private JButton sortSong;
    private  int size2;
    private  ArrayList songs;
    private Handler handler;
    private ImageIcon removeIcon;
    private ArrayList<JButton>  removeSongs;



    // private ArrayList <JButton> playlistsButtons;
    //  private JList <String> list;
    private static MiddlePage middlePage;


    public static void setMiddlePage(MiddlePage middlePage) {
        PlaylistsGUI.middlePage = middlePage;
    }


    public PlaylistsGUI(PlayList playlist) throws InvalidDataException, IOException, UnsupportedTagException {
        removeIcon = new ImageIcon(new ImageIcon("src\\icons\\remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

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

        finalSet(songs,size2);



    }

    public void finalSet(ArrayList songs,int size2) throws InvalidDataException, IOException, UnsupportedTagException {
        songButtons = new ArrayList<>(size2);
        removeSongs = new ArrayList<>(size2);
        for (int i = 0; i < size2; i++) {
            JButton button1 = new JButton();
            button1.setIcon(removeIcon);
            button1.setBackground(new Color(20,20,20));
            button1.setToolTipText("Remove song from playlist");
            button1.setPreferredSize(new Dimension(20,20));
            button1.setSize(new Dimension(20,20));
            button1.setSize(20,20);
            button1.setBorder(BorderFactory.createEmptyBorder());
            removeSongs.add(button1);


            JButton button = new JButton();
            button.setLayout(new GridLayout(3, 1));
            songButtons.add(button);
        }

        for (int i = 0; i < size2; i++) {
            songButtons.get(i).addActionListener(handler);
            removeSongs.get(i).addActionListener(handler);
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
            songButtons.get(i).add(removeSongs.get(i));

            this.add(songButtons.get(i));
        }
        addSong = new JButton("add new song");
        addSong.setBackground(new Color(20, 20, 20));
        addSong.setPreferredSize(new Dimension(120, 200));
        addSong.setForeground(Color.WHITE);
        addSong.addActionListener(handler);

        sortSong = new JButton();
        sortSong.setToolTipText("change place of two songs");
        sortSong.setLayout(new GridLayout(0,1));
        JLabel jLabel1 = new JLabel("Change place of", SwingConstants.CENTER);
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setBackground(new Color(20,20,20));
        JLabel jLabel2 = new JLabel("And",SwingConstants.CENTER);
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setBackground(new Color(20,20,20));
         song1 = new JTextArea();
        song1.setBackground(Color.lightGray);
         song2 = new JTextArea();
        song2.setBackground(Color.lightGray);
       sortSong.add(jLabel1);
       sortSong.add(song1);
       sortSong.add(jLabel2);
       sortSong.add(song2);
        sortSong.setBackground(new Color(20, 20, 20));
        sortSong.setPreferredSize(new Dimension(120, 200));
        sortSong.setForeground(Color.WHITE);
        sortSong.addActionListener(handler);

        if(size2==0){
            this.removeAll();
            middlePage.removeAll();
            middlePage.repaint();

        }
        this.add(addSong);
        this.add(sortSong);
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
        finalSet(songs,size2);




    }
    public void setMiddlePageButtons(PlayList playlist) throws InvalidDataException, IOException, UnsupportedTagException {
        this.removeAll();
        middlePage.revalidate();
        this.setLayout(new GridLayout(0, 4, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));

        ArrayList playlists1 = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
        for (int i = 0; i <playlists1.size() ; i++) {
            if(((PlayList)playlists1.get(i)).getName().equals(playlist.getName())){
                songs = ((PlayList)playlists1.get(i)).getPlayListSongs();
                size2 = songs.size();
            }
        }

       finalSet(songs,size2);

    }


    public class Handler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i <removeSongs.size() ; i++) {
                if(e.getSource()==removeSongs.get(i)){
                    System.out.println(i);
                    if(playlist.getName().equals("Favorite")){
                        try {
                            ArrayList songs = Songs.reafSongsFromFile("src\\playlists\\Favorite.bin");
                            songs.remove(i);

                            Songs.writeSongsToFile(songs,"src\\playlists\\Favorite.bin");
                            setMiddleForFavorite();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(playlist.getName().equals("Shared")){
                        try {
                            ArrayList songs = Songs.reafSongsFromFile("src\\playlists\\Shared.bin");
                            songs.remove(i);

                            Songs.writeSongsToFile(songs,"src\\playlists\\Shared.bin");
                            setMiddleForShared();
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
                            ArrayList playlists = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
                            for (int j = 0; j < playlists.size(); j++) {
                                if(((PlayList)playlists.get(j)).getName().equals(playlist.getName())){
                                    ArrayList songs = ((PlayList)playlists.get(j)).getPlayListSongs();
                                    songs.remove(i);
                                    ((PlayList)playlists.get(j)).setPlayListSongs(songs);
                                    playlists.set(j,((PlayList)playlists.get(j)));
                                    Songs.writeLibrariesToFile(playlists,"src\\playlists\\playlists.bin");
                                    setMiddlePageButtons(playlist);
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
            }

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

            if(e.getSource()==sortSong){
                int firstIndex = Integer.parseInt(song1.getText());
                int secondIndex = Integer.parseInt(song2.getText());
                if(playlist.getName().equals("Favorite")){
                    try {
                        ArrayList songs = Songs.reafSongsFromFile("src\\playlists\\Favorite.bin");
                        Song song1 = (Song) songs.get(firstIndex);
                        Song song2 = (Song) songs.get(secondIndex);
                        songs.set(firstIndex,song2);
                        songs.set(secondIndex,song1);
                        Songs.writeSongsToFile(songs,"src\\playlists\\Favorite.bin");
                        setMiddleForFavorite();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InvalidDataException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedTagException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(playlist.getName().equals("Shared")){
                    ArrayList songs = null;
                    try {
                        songs = Songs.reafSongsFromFile("src\\playlists\\Shared.bin");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Song song1 = (Song) songs.get(firstIndex);
                    Song song2 = (Song) songs.get(secondIndex);
                    songs.set(firstIndex,song2);
                    songs.set(secondIndex,song1);
                    Songs.writeSongsToFile(songs,"src\\playlists\\Shared.bin");
                    try {
                        setMiddleForShared();
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
                        ArrayList playlists = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
                        for (int i = 0; i < playlists.size(); i++) {
                            if(((PlayList)(playlists.get(i))).getName().equals(playlist.getName())){
                                ArrayList songs = ((PlayList)(playlists.get(i))).getPlayListSongs();
                                Song song1 = (Song) songs.get(firstIndex);
                                Song song2 = (Song) songs.get(secondIndex);
                                songs.set(firstIndex,song2);
                                songs.set(secondIndex,song1);
                                ((PlayList)(playlists.get(i))).setPlayListSongs(songs);
                                playlists.set(i,((PlayList)(playlists.get(i))));
                                Songs.writeLibrariesToFile(playlists,"src\\playlists\\playlists.bin");
                                setMiddlePageButtons(playlist);
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
                    ArrayList finalFileOfSongs = fileOfSongs;
                    songs.get(i).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {



                            if (!playlist.getName().equals("Favorite") && !(playlist.getName().equals("Shared"))) {
                                System.out.println(finalI + "****************************************");
                                try {

                                  //  playlist.addSongs(finalI);

                                    ArrayList playlists = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
                                    if (playlists == null) playlists = new ArrayList();
                                    for (int j = 0; j < playlists.size(); j++) {
                                        if (((PlayList) playlists.get(j)).getName().equals(playlist.getName())) {
                                            System.out.println(((PlayList) playlists.get(j)).getName()+"*****");
                                           // playlists.set(j, playlist);
                                            ArrayList songs = ((PlayList) playlists.get(j)).getPlayListSongs();
                                            songs.add(finalFileOfSongs.get(finalI));
                                            ((PlayList) playlists.get(j)).setPlayListSongs(songs);
                                            playlists.set(j,((PlayList) playlists.get(j)));
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