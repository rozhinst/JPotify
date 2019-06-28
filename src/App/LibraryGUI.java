import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryGUI extends JPanel {

    private ImageIcon newPlaylistIcon;
    private ImageIcon songIcon;
    private ImageIcon albumIcon;
    private JButton song;
    private JButton album;
    private JButton addToLibrary;
    // private JFrame playListName;
    private JFrame nameFrame;
    private JTextArea getName;

    private MiddlePage middlePage;
    private JLabel label2;
    private PlayList favorite;
    private PlayList shared;
    private JPanel playlists;
    private ArrayList<PlayList> addedPlaylists;

    private ImageIcon libraryIcon;
    private ImageIcon lineIcon;
    private JLabel line;

    private JButton addToPlaylist;
    private Handler handler;
    private DisplaySongs display;
    private final JScrollPane scroll;

    public LibraryGUI() {
        super();
        Handler handler = new Handler();
        // playlist = new PlaylistsGUI();
        // System.out.println(p);
        // playlist.setMiddlePage(this.middlePage);
        this.setLayout(new GridLayout(0, 1));
        Border emptyBorder = BorderFactory.createEmptyBorder();


        display = new DisplaySongs();
        playlists = new JPanel();
        playlists.setBackground(new Color(20,20,20));
        playlists.setLayout(new GridLayout(0,1));
        playlists.setMinimumSize(new Dimension(120,120));

        favorite = new PlayList("Favorite");
        favorite.addActionListener(handler);
        favorite.setBackground(new Color(20,20,20));
        favorite.setBorder(emptyBorder);
        favorite.setForeground(Color.WHITE);
      //  favorite.addActionListener(handler);
        shared = new PlayList("Shared");
        shared.setBackground(new Color(20,20,20));
        shared.setBorder(emptyBorder);
        shared.setForeground(Color.WHITE);
       shared.addActionListener(handler);
        JButton favoriteButton = favorite;
        favoriteButton.setText("Favorite");
        favoriteButton.setForeground(Color.WHITE);
      //  favoriteButton.addActionListener(handler);

        JButton sharedButton = shared;
        shared.setText("Shared");
        sharedButton.setForeground(Color.WHITE);
       // sharedButton.addActionListener(handler);
        playlists.add(favoriteButton);
        playlists.add(sharedButton);

        label2 = new JLabel(" Playlist");
        label2.setForeground(Color.GRAY);
       label2.setBorder(new EmptyBorder(250, 0, 300, 50));
        label2.setFont(label2.getFont().deriveFont(Font.BOLD, 14f));
        label2.setMaximumSize(new Dimension(120,60));
        //   this.setBackground(new Color(20,20,20));
        ArrayList temp = new ArrayList();
        try {
             temp = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(temp==null)  addedPlaylists = new ArrayList<>();
        else addedPlaylists = temp;

        for (int i = 0; i <addedPlaylists.size() ; i++) {
            JButton button =addedPlaylists.get(i);
            button.setText(addedPlaylists.get(i).getName());
            button.setBackground(new Color(20,20,20));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addActionListener(handler);

            playlists.add(button);


        }
        playlists.revalidate();


        libraryIcon = new ImageIcon(new ImageIcon("src\\icons\\library.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        songIcon = new ImageIcon(new ImageIcon("src\\icons\\song.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        albumIcon = new ImageIcon(new ImageIcon("src\\icons\\album.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        newPlaylistIcon = new ImageIcon(new ImageIcon("src\\icons\\playList.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        lineIcon = new ImageIcon(new ImageIcon("src\\icons\\line.png").getImage().getScaledInstance(120, 5, Image.SCALE_DEFAULT));

        this.setBackground(new Color(20, 20, 20));
        //this.setBackground(new Color(150,0,205));
        JLabel label = new JLabel(" Library");
        label.setBorder(emptyBorder);
        label.setForeground(Color.GRAY);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        //label.setPreferredSize(new Dimension(150,60));
        setLayout(new GridLayout(0, 1));
        this.setBorder(emptyBorder);

        line = new JLabel();
        line.setIcon(lineIcon);
        line.setBorder(emptyBorder);
        line.setPreferredSize(new Dimension(120, 5));
        song = new JButton("Songs");
        song.setIcon(songIcon);
        album = new JButton("Albums");
        addToPlaylist = new JButton("New Playlist");
        addToPlaylist.setIcon(newPlaylistIcon);
        addToPlaylist.setBackground(new Color(20, 20, 20));
        addToPlaylist.setBorder(emptyBorder);
        addToPlaylist.setForeground(Color.WHITE);
        addToPlaylist.addActionListener(handler);
        album.setIcon(albumIcon);
        //playlist = new JLabel(" Playlist");
        //playList.setPreferredSize(new Dimension(10,10));
       /* favoritePlayList = new JButton("Favorite");
        favoritePlayList.setBackground(new Color(20,20,20));
        favoritePlayList.setForeground(Color.WHITE);
        favoritePlayList.setBorder(emptyBorder);

        sharedPlayist = new JButton("Shared");
        sharedPlayist.setBackground(new Color(20,20,20));
        sharedPlayist.setForeground(Color.WHITE);
        sharedPlayist.setBorder(emptyBorder);

        */

        // playlist.setForeground(Color.GRAY);
        // playlist.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        //  playlist.setPreferredSize(new Dimension(150,60));
        setLayout(new GridLayout(8, 1));
        song.setBorder(emptyBorder);
        song.setBackground(new Color(20, 20, 20));
        song.setForeground(Color.WHITE);
        song.setFocusable(false);
        song.addActionListener(handler);
        album.setBackground(new Color(20, 20, 20));
        album.setBorder(emptyBorder);
        album.setForeground(Color.WHITE);
        album.setFocusable(false);
        addToLibrary = new JButton("AddToLibrary");
        addToLibrary.setIcon(libraryIcon);
        addToLibrary.setForeground(Color.WHITE);
        addToLibrary.setBorder(emptyBorder);
        addToLibrary.setBackground(new Color(20, 20, 20));
        addToLibrary.setFocusable(false);
        song.setPreferredSize(new Dimension(90, 60));
        album.setPreferredSize(new Dimension(90, 60));
        addToLibrary.setPreferredSize(new Dimension(90, 60));
        playlists.setPreferredSize(new Dimension(100,200));

         scroll = new JScrollPane(playlists,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scroll.setBackground(Color.GRAY);
         scroll.setPreferredSize(new Dimension(100,200));


        addToPlaylist.setBorder((BorderFactory.createLineBorder(Color.WHITE)));
        JSeparator sep = new JSeparator();
        //  sep.setBorder(new EmptyBorder(0, 5, 100, 5));
//sep.setPreferredSize(new Dimension(60,0));
        //  Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));

        sep.setSize(0, 120);

        add(label);
        add(addToLibrary);
        add(album);
        add(song);


//add(sep);
        // add(new JSeparator(SwingConstants.HORIZONTAL));
        // add(playList);
        add(line);
//        add(playlist);

//        add(favoritePlayList);
        // add(sharedPlayist);
        add(label2);
        add(scroll);

        //  JScrollPane jScrollPane = new JScrollPane(playlist);
        // add(jScrollPane);
        add(addToPlaylist);
        addToLibrary.addActionListener(handler);
        album.addActionListener(handler);

    }

    public void setMiddlePage(MiddlePage middlePage) {
        this.middlePage = middlePage;
    }

    public void reValidateMiddlePageForSongs(String path) {
        middlePage.getSongsInLibrary().removeAll();
        middlePage.getAlbumsInLibrary().removeAll();
        middlePage.getSongsInAlbum().removeAll();
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

    public void reValidateMiddlePageForAlbums(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        // middlePage.getSongsInLibrary().removeAll();
        middlePage.getSongsInLibrary().removeAll();
        middlePage.getAlbumsInLibrary().removeAll();
        middlePage.getSongsInAlbum().removeAll();
        middlePage.addAlbumsToLibraryPanel(path);
        middlePage.revalidate();
    }

    public class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

           if(e.getSource() instanceof PlayList){
               System.out.println(((PlayList) e.getSource()).getName());
               middlePage.removeAll();
               middlePage.revalidate();
               System.out.println(((PlayList)e.getSource()).getName());
               try {


                   PlaylistsGUI.setMiddlePage(middlePage);



                   PlaylistsGUI playlistsGUI = new PlaylistsGUI(((PlayList)e.getSource()));
                   middlePage.revalidate();



               } catch (InvalidDataException ex) {
                   ex.printStackTrace();
               } catch (IOException ex) {
                   ex.printStackTrace();
               } catch (UnsupportedTagException ex) {
                   ex.printStackTrace();
               }

           }
           /*
           if(e.getSource()==favorite){

               System.out.println("favorite");
            //   PlaylistsGUI playlistsGUI = new PlaylistsGUI(favorite);
           }
           if(e.getSource()==shared){
               System.out.println("shared");
           }

            */
            if (e.getSource() == addToPlaylist) {
                nameFrame = new JFrame("Add to playlist");

                nameFrame.setSize(300, 200);
                nameFrame.setBackground(new Color(20, 20, 20));
                JLabel label = new JLabel("Please choose a name for playlist");
                label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

                label.setBackground(Color.lightGray);
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                JButton addButton = new JButton("add");

                addButton.setBackground(Color.gray);
                jPanel.setBackground(Color.lightGray);
                jPanel.add(addButton, BorderLayout.SOUTH);
                jPanel.add(label, BorderLayout.NORTH);
                // playListName.add(add);
                // playlist.add(jPanel);
                getName = new JTextArea();
                getName.setBackground(Color.LIGHT_GRAY);

                jPanel.add(getName, BorderLayout.CENTER);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        // action handling after pushing new playlist
                        String name = getName.getText();
                        try {
                            PlayList newOne =  display.creatPlayList(name);
                            newOne.setBackground(new Color(20,20,20));
                            newOne.setForeground(Color.WHITE);
                            newOne.setText(name);
                            System.out.println(newOne.getName());
                            newOne.setBorder(BorderFactory.createEmptyBorder());
                            newOne.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println(newOne.getName());
                                    middlePage.removeAll();
                                    middlePage.revalidate();
                                    PlaylistsGUI.setMiddlePage(middlePage);

                                    try {
                                        PlaylistsGUI playlistsGUI = new PlaylistsGUI((PlayList) newOne);
                                        middlePage.revalidate();
                                    } catch (InvalidDataException ex) {
                                        ex.printStackTrace();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    } catch (UnsupportedTagException ex) {
                                        ex.printStackTrace();
                                    }//////////////////////////////////////////////////////////////////////
                                }
                            });

                            playlists.add(newOne);
                            playlists.revalidate();
                            nameFrame.setVisible(false);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
                // playListName.add(getName);
                nameFrame.add(jPanel);
                nameFrame.repaint();
                nameFrame.revalidate();
                nameFrame.setVisible(true);




            }

                if (e.getSource() == song) {
                    middlePage.removeAll();

                    //  middlePage.
                    // middlePage.getSongsInLibrary().revalidate();

                    try {
                        Songs.orderingSongs();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    reValidateMiddlePageForSongs("src\\songs\\song.bin");
                    middlePage.add(middlePage.getSongsInLibrary());
                    middlePage.getSongsInLibrary().setVisible(true);
                }
                if (e.getSource() == album) {
                    middlePage.removeAll();
                    middlePage.repaint();

                    try {
                        Songs.orderingSongs();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        reValidateMiddlePageForAlbums("src\\songs\\Albums.bin");
                    } catch (InvalidDataException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedTagException ex) {
                        ex.printStackTrace();
                    }
                   // middlePage.getAlbumsInLibrary().setVisible(true);
                    middlePage.add(middlePage.getAlbumsInLibrary());
                    middlePage.getAlbumsInLibrary().setVisible(true);
                    middlePage.revalidate();

                }
                if (e.getSource() == addToLibrary) {
                    Songs song = new Songs();
                    boolean isNull = false;
                    ArrayList<Song> a = null;
                    try {
                        a = song.reafSongsFromFile("src\\songs\\song.bin");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // ArrayList name = (ArrayList) song.reafFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                    if (a == null) {
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
                    song.writeSongsToFile(song.getSongArrays(), "src\\songs\\song.bin");
                    //playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                    try {
                        SongbarGUI.setFilePath(song.reafSongsFromFile("src\\songs\\song.bin"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // Albums.manageAlbum(songToAlbum);

                    if (isNull) {
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
                    reValidateMiddlePageForSongs("src\\songs\\song.bin");

                }

            }
        }

    }

