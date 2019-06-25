import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayListGUI extends JPanel {
    private JLabel label;
    private Border emptyBorder;

    public PlayListGUI(){
        this.setBackground(new Color(20,20,20));
        this.setBorder(emptyBorder);
        label = new JLabel("Playlist");
        label.setForeground(Color.GRAY);
        label.setBorder(new EmptyBorder(0, 5, 100, 5));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        this.add(label);
    }
}
