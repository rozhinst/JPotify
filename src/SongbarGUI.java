import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javax.sound.sampled.AudioFormat;
import javax.swing.*;
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
    private JButton prev;
    private JButton pause;
    private JButton next;
    private JButton shuffle;
    private JPanel playButtons;
    private JLabel songbar;
    private JPanel progressPanel;
    private JSlider bar;
    private JSlider volume;
    private JLabel artwork;
    private JPanel artPanel;
    private Handler handler;
    private TextNote details;
    private JPanel detailPanel;
    private JPanel metadata;
    private int counter = 0;
    private MP3 mp3;
    private GetID3 id3;
    private JPanel progressAndButtons;
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
    private Thread t;
    Timer timer;
    public SongbarGUI() throws IOException, InvalidDataException, UnsupportedTagException {
        super();
        path = "C:\\Users\\LENOVO\\Desktop\\JPotifyy\\src\\Ciara - Level Up.mp3";
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(20, 100));
        songs = new Songs();
        metadata = new JPanel();
        playButtons = new JPanel();
        progressPanel = new JPanel();
        artPanel = new JPanel();
        artwork = new JLabel();
        detailPanel = new JPanel();
        duration = new JLabel();
        songPlaying = new JLabel("0:00");
        playButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        artPanel.setLayout(new GridLayout(1, 1));
        progressAndButtons = new JPanel(new BorderLayout());
        detailPanel.setPreferredSize(new Dimension(300, 500));
        detailPanel.setLayout(new GridLayout(1, 2));
        metadata.setLayout(new GridLayout(1, 1));
        prev = new JButton("previous");
        next = new JButton("next");
        pause = new JButton("pause/play");
        shuffle = new JButton("shuffle");
        bar = new JSlider();
        volume = new JSlider();
        filePath = new ArrayList();
        vol = new SetVolume();
        timer = new Timer(1000, new Slider() );

        bar.setPreferredSize(new Dimension(200, 10));
        volume.setPreferredSize(new Dimension(80, 10));
        metadata.setPreferredSize(new Dimension(500, 50));

        progressAndButtons.setBorder(new EmptyBorder(0, 0, 0, 200));
        progressPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        playButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        artwork.setBorder(new EmptyBorder(10, 10, 10, 10));

        detailPanel.add(artPanel);
        detailPanel.add(metadata);
        progressAndButtons.add(playButtons, NORTH);
        progressAndButtons.add(progressPanel, CENTER);
        add(detailPanel, WEST);
        add(progressAndButtons, CENTER);
        artPanel.add(artwork);
        playButtons.add(prev);
        playButtons.add(pause);
        playButtons.add(next);
        playButtons.add(shuffle);
        progressPanel.add(songPlaying);
        progressPanel.add(bar);
        add(volume, EAST);
        handler = new Handler();
        pause.addActionListener(handler);
        next.addActionListener(handler);
        prev.addActionListener(handler);
        progressPanel.add(duration);
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
                }
                else if (counter % 2 != 0){
                    mp3.resume();
                    timer.start();
                }
                else {
                    timer.stop();
                    mp3.pauseMusic();
                    System.out.println("why");
                    System.out.println(timer.isRunning());

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
