import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SearchPanel extends JPanel {

    private JLabel searchIcon;

    private JTextField findTextField;



    public SearchPanel() {
        //  this.frame = frame;
        //  this.model = model;
        createPartControl();
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
        // findButton.add(new ImageIcon(new ImageIcon("src\\favorite.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });
        this.add(findButton);
    }


}
