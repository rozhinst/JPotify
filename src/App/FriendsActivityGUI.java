package App;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsActivityGUI extends JPanel {
    private Border emptyBorder;
    private  HashMap<String,String> friends;
    private ArrayList<JButton> friend;
    private static FriendsActivityGUI friendsActivityGUI  =null;
    private JLabel label;
    private FriendsActivityGUI(){
        super();
        setLayout(new GridLayout(0,1));
        emptyBorder = BorderFactory.createEmptyBorder();
        /*
        label = new JLabel(" Friends Activity");
        label.setBorder(emptyBorder);
        label.setForeground(Color.GRAY);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setPreferredSize(new Dimension(150,60));

         */
        setBackground(new Color(20,20,20));
        setPreferredSize(new Dimension(150,80));
     //   this.add(label);

    }
    synchronized public  void setFriends(HashMap friends1) {

      SwingUtilities.invokeLater(  new Runnable() {
            @Override
            public void run() {
                removeAll();
                System.out.println("huraaa");
                //this.add(label);
                friends = friends1;

                //for(String friend : friends.keySet()){}
                friend = new ArrayList<>(friends.size());
                for (int i = 0; i < friends.size(); i++) {
                    JButton button = new JButton();
                    button.setLayout(new GridLayout(2, 1));
                    button.setPreferredSize(new Dimension(50,40));
                    friend.add(button);
                }
//        Handler handler = new Handler();
//
//        for (int i = 0; i < friend.size(); i++) {
//            friend.get(i).addActionListener(handler);
//        }
                for (int i = 0; i < friends.size(); i++) {
                    JTextArea details = new JTextArea();
                    details.setBackground(new Color(20, 20, 20));
                    details.setForeground(Color.WHITE);
                    details.setPreferredSize(new Dimension(50,40) );
                    for(String f : friends.keySet()){
                        details.append(f);
                        details.append(friends.get(f));
                    }
                    friend.get(i).add(details);
                    add(friend.get(i));
                }
                revalidate();

            }
        }
        );
    }
    public static FriendsActivityGUI getInstance(){
        if(friendsActivityGUI == null) friendsActivityGUI = new FriendsActivityGUI();
        return friendsActivityGUI;
    }



}

