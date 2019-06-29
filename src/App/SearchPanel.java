import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class SearchPanel extends JPanel {

    public static MiddlePage middlePage;
    private JLabel searchIcon;

    private JTextField findTextField;
    private ArrayList<JButton>songs;
    private Handler hanldler;
    private ArrayList<Song> foundSongs;





    public SearchPanel() {
        //  this.frame = frame;
        //  this.model = model;
        createPartControl();
    }



    private void setMiddleAfterFound(ArrayList<Song> found) throws InvalidDataException, IOException, UnsupportedTagException {
        middlePage.removeAll();


        foundSongs = found;
        if (foundSongs == null) foundSongs = new ArrayList();
        songs = new ArrayList<JButton>(foundSongs.size());
        for (int i = 0; i < foundSongs.size(); i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            songs.add(button);
        }
         hanldler = new Handler();

        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).addActionListener(hanldler);
        }
        for (int i = 0; i < foundSongs.size(); i++) {
            JTextArea details;
            GetID3 id3;
            details = new JTextArea();
            details.setBackground(new Color(20, 20, 20));
            details.setForeground(Color.WHITE);
            Song song = (Song) foundSongs.get(i);
            id3 = new GetID3(song.getPath());
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

            middlePage.add(songs.get(i));
            middlePage.revalidate();

        }
    }

    public ArrayList<Song> searchBySong(String name) throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList<Song> songs = (ArrayList<Song>) Songs.reafSongsFromFile("src\\songs\\song.bin");
        ArrayList<Song> searched = new ArrayList<>();
        for(int i=0;i<songs.size();i++){
            GetID3 id3 = new GetID3(songs.get(i).getPath());
            for(int j=0;j<id3.getDetails().size();j++){
                if(id3.getDetails().get(j).contains(name))
                    searched.add(songs.get(i));

            }
        }
        return searched;
    }

    protected void createPartControl() {
        this.setPreferredSize(new Dimension(30,30));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // this.setLayout(new FlowLayout());
        this.setBackground(new Color(20,20,20));
        this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 1000));

        // JLabel findLabel = new JLabel();
        searchIcon = new JLabel(new  ImageIcon(new ImageIcon("src\\icons\\search.png").getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT)));
        // findLabel.setBackground(Color.GRAY);

        this.add(searchIcon);

        this.add(Box.createRigidArea(new Dimension(6, 0)));

        findTextField = new JTextField(30);
        findTextField.setBackground(Color.GRAY);
        this.add(findTextField);

        this.add(Box.createRigidArea(new Dimension(6, 0)));

        JButton findButton = new JButton("Search");
        findButton.setFocusable(false);
        // findButton.add(new ImageIcon(new ImageIcon("src\\favorite.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(event.getSource() == findButton){
                    ArrayList<Song> found = null;
                    try {
                        found = searchBySong(findTextField.getText());
                        setMiddleAfterFound(found);


                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    }
                    if(found ==null) System.out.println("naaaaaaaaaa");
                    for (int i=0;i<found.size();i++)
                        System.out.println(found.get(i).getName()+"  "+found.get(i).getPath());

                }
            }
        });
        this.add(findButton);
    }



    public class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i <songs.size() ; i++) {
                if(e.getSource()==songs.get(i)){
                    System.out.println(i);
                    try {
                        ArrayList fileOfSongs = Songs.reafSongsFromFile("src\\App\\songs\\song.bin");

                        for (int j = 0; j <fileOfSongs.size(); j++) {
                            if(((Song)(fileOfSongs.get(j))).getName().equals(((Song)(foundSongs.get(i))).getName())){

                                SongbarGUI.setSongNum(j);

                                SongbarGUI.nextOrPrev();
                                ((Song)fileOfSongs.get(j)).setTimePlayed();
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }
    }
}

