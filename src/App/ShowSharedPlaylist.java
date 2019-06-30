package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowSharedPlaylist extends JFrame {
    JPanel panel;
    private ArrayList<JButton> buttons;
    ArrayList<Song> song;
    String name;
    public ShowSharedPlaylist(){
        super();
        this.setLayout(new GridLayout(8,1));
        //panel = new JPanel();
       // add(panel);
        setVisible(true);
    }
    synchronized public void showShared(String name){
        SwingUtilities.invokeLater(  new Runnable() {
            @Override
            public void run() {
        DownloadMusic dn = new DownloadMusic();
        dn.sendSharedPlayList(name);
        song =(ArrayList<Song>) Songs.reafFromFile("src\\App\\Files\\playlist\\Shared.bin");/////kharabeeeeeeeeeeeee fuck this shittttttttttttttttttttttttttttttttttttttttttttttttt
        buttons = new ArrayList<>(song.size());
        for (int i = 0; i < song.size(); i++) {
            JButton button = new JButton();
            button.setLayout(new GridLayout(2, 1));
            button.setPreferredSize(new Dimension(30,10));
            buttons.add(button);
        }
        Handler handler = new Handler();

       for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(handler);
        }
        for (int i = 0; i < song.size(); i++) {
            buttons.get(i).setText(song.get(i).getName());
            add(buttons.get(i));
        }


            }
                                     }
        );

    }
    public void setName(String n){name = n;}
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < buttons.size(); i++) {
                if (e.getSource() == buttons.get(i)) {
                    DownloadMusic dn = new DownloadMusic();
                    dn.getSong(song.get(i).getPath(),name);
                }

            }
        }
    }

}
