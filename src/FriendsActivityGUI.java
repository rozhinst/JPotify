import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FriendsActivityGUI extends JPanel {
    private Border emptyBorder;
    public  FriendsActivityGUI(){
        super();
        emptyBorder = BorderFactory.createEmptyBorder();

        JLabel label = new JLabel(" Friends Activity");
        label.setBorder(emptyBorder);
        label.setForeground(Color.GRAY);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setPreferredSize(new Dimension(150,60));
        setBackground(new Color(20,20,20));
        setPreferredSize(new Dimension(150,80));
        this.add(label);
    }
}

