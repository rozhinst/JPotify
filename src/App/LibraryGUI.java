package App;
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

    public static MiddlePage middlePage;
    private ImageIcon newPlaylistIcon;
    private ImageIcon songIcon;
    private ImageIcon albumIcon;
    private ImageIcon removeIcon;
    private ImageIcon renameIcon;
    private JButton song;
    private JButton album;
    private ArrayList<JButton>  removePlaylist;
    private ArrayList<JButton>  renamePlaylist;
    private JButton addToLibrary;
    // private JFrame playListName;
    private JButton favoriteButton;
    private JButton sharedButton;
    private JFrame nameFrame;
    private JTextArea getName;


    private Handler hanler2;
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

    public LibraryGUI() throws IOException {
        super();
        Handler handler = new Handler();
        // playlist = new PlaylistsGUI();
        // System.out.println(p);
        // playlist.setMiddlePage(this.middlePage);
        this.setLayout(new GridLayout(0, 1));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        // this.setPreferredSize(new Dimension(200,500));


        display = new DisplaySongs();
        playlists = new JPanel();
        playlists.setBackground(new Color(20,20,20));
        playlists.setLayout(new GridLayout(0,1));
        playlists.setMinimumSize(new Dimension(120,120));
        renameIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\rename.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        removeIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        /*
        removePlaylist = new JButton();
        removePlaylist.setBackground(new Color(20,20,20));
        removePlaylist.setBorder(emptyBorder);
        removePlaylist.setToolTipText("Remove playlist");
        removePlaylist.setIcon(removeIcon);

         */



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
        favoriteButton = favorite;
        favoriteButton.setText("Favorite");
        favoriteButton.setForeground(Color.WHITE);
        //  favoriteButton.addActionListener(handler);

        sharedButton = shared;
        shared.setText("Shared");
        sharedButton.setForeground(Color.WHITE);
        // sharedButton.addActionListener(handler);


        label2 = new JLabel(" Playlist");
        label2.setForeground(Color.GRAY);
        label2.setBorder(new EmptyBorder(250, 0, 300, 50));
        label2.setFont(label2.getFont().deriveFont(Font.BOLD, 14f));
        label2.setMaximumSize(new Dimension(120,60));
        //   this.setBackground(new Color(20,20,20));
        /*
        ArrayList temp = new ArrayList();
        try {
             temp = Songs.reafPlayListFromFile("src\\playlists\\playlists.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(temp==null)  addedPlaylists = new ArrayList<>();
        else addedPlaylists = temp;


         */



        setPlaylistButtons();

//        for (int i = 0; i <removePlaylist.size() ; i++) {
//            removePlaylist.get(i).addActionListener(handler);
//        }
        playlists.repaint();



        libraryIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\library.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        songIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\song.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        albumIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\album.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        newPlaylistIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\playList.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        lineIcon = new ImageIcon(new ImageIcon("src\\App\\icons\\line.png").getImage().getScaledInstance(170, 5, Image.SCALE_DEFAULT));

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
        scroll.setPreferredSize(new Dimension(130,140));



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

    public void setPlaylistButtons() throws IOException {
        playlists.removeAll();
        playlists.add(favoriteButton);
        playlists.add(sharedButton);

        addedPlaylists = Songs.reafPlayListFromFile("src\\App\\playlists\\playlists.bin");
        if(addedPlaylists==null) addedPlaylists = new ArrayList<>();

        removePlaylist = new ArrayList<>();
        renamePlaylist = new ArrayList<>();
        hanler2 = new Handler();

        for (int i = 0; i <addedPlaylists.size() ; i++) {
            JButton button1 = new JButton();
            button1.setIcon(removeIcon);
            button1.setBorder(BorderFactory.createEmptyBorder());
            button1.setBackground(new Color(20,20,20));
            button1.setToolTipText("Delete playlist");



            // button1.addActionListener(handler);

            removePlaylist.add(button1);

        }
        for (int i = 0; i <addedPlaylists.size() ; i++) {
            JButton button1 = new JButton();
            button1.setIcon(renameIcon);
            button1.setBorder(BorderFactory.createEmptyBorder());
            button1.setBackground(new Color(20,20,20));
            button1.setToolTipText("Rename playlist");



            // button1.addActionListener(handler);

            renamePlaylist.add(button1);
        }

        for (int i = 0; i < removePlaylist.size(); i++) {
            removePlaylist.get(i).addActionListener(hanler2);
            // renamePlaylist.get(i).addActionListener(hanler2);
        }
        for (int i = 0; i < renamePlaylist.size(); i++) {
            renamePlaylist.get(i).addActionListener(hanler2);
            // renamePlaylist.get(i).addActionListener(hanler2);
        }
        for (int i = 0; i <addedPlaylists.size() ; i++) {
            PlayList playList = new PlayList(addedPlaylists.get(i).getName());
            // playList.setText(addedPlaylists.get(i).getName());
            JLabel label = new JLabel(addedPlaylists.get(i).getName(),SwingConstants.CENTER);
            label.setForeground(Color.WHITE);

            playList.setBackground(new Color(20,20,20));
            playList.setForeground(Color.WHITE);
            playList.setBorder(BorderFactory.createEmptyBorder());
            // button.add(removePlaylist);

            // button.addActionListener(handler);
            // addedPlaylists.add(button);
            playlists.add(playList);
            //addedPlaylists.set(i,button);
            //  playList.addActionListener(handler);
            playList.setLayout(new GridLayout());
            playList.addActionListener(hanler2);
            //  addedPlaylists.set(i,playList) ;
            playList.add(removePlaylist.get(i));
            playList.add(label);
            playList.add(renamePlaylist.get(i));


        }

        /*
        for (int i = 0; i <addedPlaylists.size() ; i++) {
            ((PlayList) addedPlaylists.get(i)).addActionListener(handler);
        }

         */





        playlists.revalidate();

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

            for (int i = 0; i <removePlaylist.size() ; i++) {
                if(e.getSource()==removePlaylist.get(i)){
                    System.out.println(i);

                    try {
                        System.out.println(addedPlaylists.get(i).getName()+"pressed");
                        PlayList playList = addedPlaylists.get(i);

                        display.removePlaylist(playList);
                        setPlaylistButtons();

                        repaint();
                        playlists.repaint();
                        playlists.revalidate();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    // setPlaylistButtons();



                }
            }

            for (int i = 0; i < renamePlaylist.size(); i++) {
                if(e.getSource()==renamePlaylist.get(i)){
                    PlayList playList = addedPlaylists.get(i);
                    nameFrame = new JFrame("Rename playlist");
                    nameFrame.setVisible(true);
                    nameFrame.setSize(300, 200);
                    nameFrame.setBackground(new Color(20, 20, 20));
                    JLabel label = new JLabel("Please choose a name for playlist");
                    label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

                    label.setBackground(Color.lightGray);
                    JPanel jPanel = new JPanel();
                    jPanel.setLayout(new BorderLayout());
                    JButton renameButton = new JButton("rename");

                    renameButton.setBackground(Color.gray);
                    jPanel.setBackground(Color.lightGray);
                    jPanel.add(renameButton, BorderLayout.SOUTH);
                    jPanel.add(label, BorderLayout.NORTH);
                    nameFrame.add(jPanel);
                    // playListName.add(add);
                    // playlist.add(jPanel);
                    getName = new JTextArea();
                    getName.setBackground(Color.LIGHT_GRAY);

                    jPanel.add(getName, BorderLayout.CENTER);
                    int finalI = i;
                    renameButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            // Rename playlist
                            String newName = getName.getText();
                            addedPlaylists.get(finalI).rename(newName);
                            Songs.writeLibrariesToFile(addedPlaylists,"src\\App\\playlists\\playlists.bin");
                            try {
                                setPlaylistButtons();
                                playlists.repaint();
                                nameFrame.setVisible(false);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    });

                }
            }

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
                            /*
                            newOne.setBackground(new Color(20,20,20));
                            newOne.setForeground(Color.WHITE);
                            newOne.setText(name);

                             */

                            //addedPlaylists.add(newOne);

                            setPlaylistButtons();

                            playlists.repaint();
                            System.out.println(newOne.getName());
                            //  newOne.setBorder(BorderFactory.createEmptyBorder());
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

                            //  playlists.add(newOne);
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
                middlePage.repaint();
                try {
                    ArrayList<Song> songArrayList = (ArrayList<Song>) Songs.reafSongsFromFile("src\\App\\songs\\song.bin");
                    SongbarGUI.setFilePath(songArrayList);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                //  middlePage.
                // middlePage.getSongsInLibrary().revalidate();

                try {
                    Songs.orderingSongs();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                reValidateMiddlePageForSongs("src\\App\\songs\\song.bin");
                middlePage.add(middlePage.getSongsInLibrary());
                middlePage.getSongsInLibrary().setVisible(true);
            }
            if (e.getSource() == album) {
                middlePage.removeAll();
                middlePage.repaint();

                try {
                    Songs.orderingSongs();
                    middlePage.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    reValidateMiddlePageForAlbums("src\\App\\songs\\Albums.bin");
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
                    a = song.reafSongsFromFile("src\\App\\songs\\song.bin");
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
                song.writeSongsToFile(song.getSongArrays(), "src\\App\\songs\\song.bin");
                //playList.writeToFile(name,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\SongNames.txt");
                try {
                    SongbarGUI.setFilePath(song.reafSongsFromFile("src\\App\\songs\\song.bin"));
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
                reValidateMiddlePageForSongs("src\\App\\songs\\song.bin");

            }

        }
    }

}

