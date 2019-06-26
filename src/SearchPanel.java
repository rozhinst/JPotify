import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class SearchPanel extends JPanel {

    private JLabel searchIcon;

    private JTextField findTextField;



    public SearchPanel() {
        //  this.frame = frame;
        //  this.model = model;
        createPartControl();
    }
    public Song searchBySong(String name) throws IOException {
        ArrayList<Song> songs = (ArrayList<Song>) Songs.reafSongsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\song.bin");
        for(int i=0;i<songs.size();i++){
            if(songs.get(i).getName().contains(name))
                return songs.get(i);
        }
        return null;
    }

    protected void createPartControl() {
        this.setPreferredSize(new Dimension(30,30));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // this.setLayout(new FlowLayout());
        this.setBackground(new Color(20,20,20));
        this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 1000));

       // JLabel findLabel = new JLabel();
        searchIcon = new JLabel(new  ImageIcon(new ImageIcon("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\icons\\search.png").getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT)));
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
                    Song song = null;
                    try {
                        song = searchBySong(findTextField.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(song ==null) System.out.println("naaaaaaaaaa");
                    System.out.println(song.getName()+"  "+song.getPath());

                }
            }
        });
        this.add(findButton);
    }



}
