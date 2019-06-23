import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import static java.awt.BorderLayout.*;

public class SongbarGUI extends JPanel {
    private  JButton refresh;
    private JButton prev;
    private JButton pause;
    private JButton next;
    private JButton shuffle;
    private JPanel playButtons;
    private JButton favorite;
    private ImageIcon pauseIcon;
    private ImageIcon playIcon;
    private ImageIcon favoriteIcon;
    private ImageIcon shuffleIcon;
    private ImageIcon nextIcon;
    private ImageIcon previousIcon;
    private ImageIcon refreshIcon;


    private JSlider bar;
    private JSlider volume;
    private JLabel artwork;
    private JPanel songBar;
    private JPanel artPanel;
    private Handler handler;
    private TextNote details;
    private JPanel detailPanel;
    private JPanel metadata;
    private int counter = 0;
    private MP3 mp3;
    private GetID3 id3;

    private String temp = "";
    private String path;
    private Songs songs;
    private ArrayList filePath;
    private SetVolume vol;
    private JLabel duration;
    private JLabel songPlaying;
    private SliderHandler sliderHandler;
    private int sliderValue;
    private int songNum=0;
    private long totalTime;
    private JPanel barPanel;
    private Thread t;
    Timer timer;
    public SongbarGUI() throws IOException, InvalidDataException, UnsupportedTagException {
        super();
        //creating an empty border
        Border emptyBorder = BorderFactory.createEmptyBorder();

        songBar = new JPanel();
        songBar.setLayout(new BorderLayout());

        //Icons of playBottons
         playIcon = new ImageIcon(new ImageIcon("src\\icons\\play.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
         pauseIcon = new ImageIcon(new ImageIcon("src\\icons\\pause-512.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
         nextIcon = new ImageIcon(new ImageIcon("src\\icons/next.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
         previousIcon = new ImageIcon(new ImageIcon("src\\icons\\previous.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
         shuffleIcon = new ImageIcon(new ImageIcon("src\\icons\\Shuffle-2-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
         favoriteIcon = new ImageIcon(new ImageIcon("src\\icons\\favorite.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
         refreshIcon = new ImageIcon(new ImageIcon("src\\icons\\refresh.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));


        setBackground(new Color(20,20,20));
        //Layout of hole song bar
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100,120));
        songs = new Songs();
        metadata = new JPanel();
        playButtons = new JPanel();
      //  progressPanel = new JPanel();
        artPanel = new JPanel();
        artwork = new JLabel();
        detailPanel = new JPanel();
        duration = new JLabel();
        songPlaying = new JLabel("0:00");

        artPanel.setLayout(new GridLayout(1, 1));


        detailPanel.setPreferredSize(new Dimension(250,200));
        artPanel.setBackground(new Color(20,20,20));
        detailPanel.setLayout(new GridLayout(1, 2));
        metadata.setLayout(new GridLayout(1, 1));
        prev = new JButton();
        next = new JButton();
        pause = new JButton();
        shuffle = new JButton();
        favorite = new JButton();
        refresh = new JButton();
        bar = new JSlider();
        volume = new JSlider();
        filePath = new ArrayList();
        vol = new SetVolume();
        timer = new Timer(1000, new Slider() );

        pause.setIcon(playIcon);
        pause.setBorder(emptyBorder);
        pause.setBackground(new Color(20,20,20));
        pause.setToolTipText("Play/Pause");

        favorite.setIcon(favoriteIcon);
        favorite.setBorder(emptyBorder);
        favorite.setBackground(new Color(20,20,20));
        favorite.setToolTipText("Add to favorite");

        shuffle.setIcon(shuffleIcon);
        shuffle.setBorder(emptyBorder);
        shuffle.setBackground(new Color(20,20,20));
        shuffle.setToolTipText("Shuffle");

        next.setIcon(nextIcon);
        next.setBorder(emptyBorder);
        next.setBackground(new Color(20,20,20));
        next.setToolTipText("Next Song");

        prev.setIcon(previousIcon);
        prev.setBorder(emptyBorder);
        prev.setBackground(new Color(20,20,20));
        prev.setToolTipText("Previous Song");

        refresh.setIcon(refreshIcon);
        refresh.setBorder(emptyBorder);
        refresh.setBackground(new Color(20,20,20));
        refresh.setToolTipText("Replay the Song");




        volume.setPreferredSize(new Dimension(150,5));
        volume.setBackground(new Color(20,20,20));
        volume.setBorder(new EmptyBorder(50, 10, 10, 0));
        metadata.setPreferredSize(new Dimension(500, 50));
        metadata.setBackground(new Color(20,20,20));

        playButtons.setBackground(new Color(20,20,20));
        playButtons.setPreferredSize(new Dimension(100,70));
        playButtons.setBorder(new EmptyBorder(10, 10, 10, 50));
        playButtons.setLayout( new FlowLayout(FlowLayout.CENTER,15,20));
        songBar.add(playButtons, BorderLayout.NORTH);

        artwork.setBorder(new EmptyBorder(10, 10, 10, 10));
        artwork.setBackground(new Color(20,20,20));
       // artwork.setPreferredSize(new Dimension(125,120));

        bar.setBackground(new Color(20,20,20));
        bar.setPreferredSize(new Dimension(500,10));
        bar.setToolTipText("Seek Bar");



     barPanel = new JPanel();
        barPanel.setLayout( new FlowLayout(FlowLayout.CENTER,10,3));
        barPanel.setBorder(new EmptyBorder(0, 10, 20, 50));
        barPanel.setBackground(new Color(20,20,20));




        detailPanel.add(artPanel);
        detailPanel.setBackground(new Color(20,20,20));

        detailPanel.add(metadata);

        add(detailPanel, WEST);
        artPanel.add(artwork);

        playButtons.add(shuffle);
        playButtons.add(prev);
        playButtons.add(pause);
        playButtons.add(next);
        playButtons.add(refresh);
        playButtons.add(favorite);
        barPanel.add(songPlaying);
        barPanel.add(bar);
        barPanel.add(duration);
        songBar.add(barPanel,CENTER);
        add(volume, EAST);
        add(songBar,CENTER);
        handler = new Handler();
        pause.addActionListener(handler);
        next.addActionListener(handler);
        prev.addActionListener(handler);
        SkipMusic skip = new SkipMusic();
        sliderHandler = new SliderHandler();
        volume.addChangeListener(sliderHandler);
        bar.addChangeListener(sliderHandler);
        bar.addMouseListener(skip);

        filePath = (ArrayList) songs.reafFromFile();
        newSong();
    }
    public void newSong() throws InvalidDataException, IOException, UnsupportedTagException {
        sliderValue = 0;
        temp="";
        System.out.println((String) filePath.get(songNum));
        id3 = new GetID3((String) filePath.get(songNum));
        for (int i = 0; i < id3.getDetails().size() - 1; i++) {
            temp += id3.getDetails().get(i);
            System.out.print(id3.getDetails().get(i));
        }
        totalTime =id3.getMp3File().getLengthInSeconds();
        bar.setMaximum((int)id3.getMp3File().getLengthInSeconds());
        System.out.println("frames:"+id3.getTotalFrames());
        bar.setValue(0);
        String[] s = id3.getDetails().get(id3.getDetails().size() - 1).split("\\s+");
        duration.setText(s[1]);
        mp3 = new MP3(counter, (String) filePath.get(songNum), id3.getTotalFrames());
        t = new Thread(mp3);
        details = new TextNote(temp);
        if(metadata.getComponentCount()!=0){
            metadata.remove(metadata.getComponent(0));

        }
        metadata.add(details);
        details.setPreferredSize(new Dimension(100, 50));
        Image newImage = id3.getImg().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        artwork.setIcon(new ImageIcon(newImage));


    }
    public void nextOrPrev(){
        if(counter>0) {
            counter = 1;
            timer.stop();
            mp3.stop();
            try {
                newSong();
            } catch (InvalidDataException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedTagException ex) {
                ex.printStackTrace();
            }
            mp3.playMusic(t);
            sliderValue = 0;
            timer.start();
        }
        else {
            try {
                newSong();
            } catch (InvalidDataException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedTagException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == pause) {
                counter++;
                if (counter == 1) {
                    sliderValue = 0;
                    mp3.playMusic(t);
                    timer.restart();
                    pause.setIcon(pauseIcon);


                }
                else if (counter % 2 != 0){
                    mp3.resume();
                    timer.start();
                    pause.setIcon(pauseIcon);
                }
                else {
                    timer.stop();
                    mp3.pauseMusic();
                    System.out.println("why");
                    System.out.println(timer.isRunning());
                    pause.setIcon(playIcon);
                }
            }
            if(e.getSource() == next) {
                System.out.println("pressed prev  "+ filePath.size()+"  hddhfl   "+songNum);
                songNum++;

                if(songNum<filePath.size()) {
                    nextOrPrev();
                    System.out.println("next done");
                }
            }
            if(e.getSource() == prev) {
                System.out.println("pressed prev  "+ filePath.size()+"  hddhfl   "+songNum);
                songNum--;
                if(songNum>=0) {
                    nextOrPrev();
                    System.out.println("prev done");
                }
            }


        }
    }
    class Slider implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bar.setValue(sliderValue);
            sliderValue++;

        }
    }
   class SliderHandler implements ChangeListener {
       @Override
       public void stateChanged(ChangeEvent e) {
           if (e.getSource() == volume) {
               System.out.println(volume.getValue());
               try {
                   vol.open(new AudioFormat(22000, 16, 1, true, false));
               } catch (JavaLayerException ex) {
                   ex.printStackTrace();
               }
               short[] data = new short[22000 / 10];
               try {
                   vol.writeImpl(data, 0, data.length);
               } catch (JavaLayerException ex) {
                   ex.printStackTrace();
               }
               vol.flushImpl();
               vol.closeImpl();

           } else {
               if (bar.getValue() % 60 < 10)
                   songPlaying.setText(bar.getValue() / 60 + ":0" + bar.getValue() % 60);
               else songPlaying.setText(bar.getValue() / 60 + ":" + bar.getValue() % 60);
           }
       }
   }
   public class SkipMusic implements MouseListener{

       @Override
       public void mouseClicked(MouseEvent e) {
           if(e.getSource()== bar){
               System.out.println("hiiii");
               timer.stop();
               mp3.stop();
               t = new Thread(mp3);
               System.out.println(bar.getValue());
               sliderValue = bar.getValue();
                   mp3.playLocation((int) (bar.getValue() * id3.getTotalFrames() / totalTime));
                   mp3.playMusic(t);
                   timer.start();
           }
       }
       public void mousePressed(MouseEvent e) { }
       public void mouseReleased(MouseEvent e) { }
       public void mouseEntered(MouseEvent e) { }
       public void mouseExited(MouseEvent e) { }
   }
       public class TextNote extends JTextArea {
           public TextNote(String text) {
               super(text);
               setBackground(null);
               setEditable(false);
               setBorder(null);
               setLineWrap(true);
               setWrapStyleWord(true);
               setFocusable(false);
           }
       }

}
