import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

public class GetID3 {
    static  final String encoding = "Cp437";
    static  private ArrayList<String> details;

    public GetID3() {
        this.details = new ArrayList<>();
    }

    public static ArrayList FileExtract(String path)throws IOException {                                                                                                       //Files Extreacting
        File dir = new File(path);
        File[] list = dir.listFiles();
        for(File file : list)
        {                                                 //CHECKING FOR FILE OR DIR
            if(file.isDirectory())
            {
                System.out.println("Directory found: "+ file.getName());
            }
            else
            {
                String fileFound = file.getName();
                String ext = fileFound.substring((fileFound.length()-3), (fileFound.length()));
                if(ext.equals("mp3"))                                                              //Checking for mp3 files
                {
                    return  ID3(file);   // Function all for ID3v1
                }
            }
        }
        return null;
    }
    public static ArrayList ID3(File file) throws IOException {
        if(ID3exists(file))                              // Prints the Tag Details if Tag is present
        {RandomAccessFile ar = new RandomAccessFile(file, "r");
            ar.seek(ar.length() - 125);
            byte []buffer = new byte[125];
            if (ar.read(buffer, 0, 125) != 125) {
                ar.close();
                throw new IOException("Tag abscent EX00");
            }
            ar.close();
            String tag = new String(buffer, 0, 125, encoding);
            details.add("Title: "+tag.substring(0, 30).trim());
            details.add("Artist: "+tag.substring(30, 60).trim());
            details.add("Album: " +tag.substring(60, 90).trim());
            details.add("Year: "+tag.substring(90, 94).trim());
            details.add("Comment: "+tag.substring(94, 123).trim());
            details.add("Genre: " + (byte) tag.charAt(124));
            details.add("Genre name: " + Genre.getGenreByByteId((byte) tag.charAt(124)));
            details.add("Track: "+ (byte) tag.charAt(123));

        }
        return details;
    }
    public static boolean ID3exists(File file)throws IOException    //Checks if ID3v1 tag is present or not
    {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        try {
            if (raf.length() < 129) {
                raf.close();
                return false;
            } else {
                long seekPos = raf.length() - 128;
                raf.seek(seekPos);

                byte buffer[] = new byte[3];

                if (raf.read(buffer, 0, 3) != 3) {
                    raf.close();
                    throw new IOException("Read beyond end of file");
                }

                String testTag = new String(buffer, 0, 3, encoding);
                raf.close();
                return testTag.equals("TAG");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public enum Genre {

        // Gets the Genre Name...
        ClassicRock(1,"Classic Rock"),
        Country(2,"Country"),
        Dance(3,"Dance"),
        Disco(4,"Disco"),
        Funk(5,"Funk"),
        Grunge(6,"Grunge"),
        HipHop(7,"Hip-Hop"),
        Jazz(8,"Jazz"),
        Metal(9,"Metal"),
        NewAge(10,"New Age"),
        Oldies(11,"Oldies"),
        Other(12,"Other"),
        Pop(13,"Pop"),
        RnB(14,"R&B"),
        Rap(15,"Rap"),
        Reggae(16,"Reggae"),
        Rock(17,"Rock"),
        Techno(18,"Techno"),
        Industrial(19,"Industrial"),
        Alternative(20,"Alternative"),
        Ska(21,"Ska"),
        DeathMetal(22,"Death Metal"),
        Pranks(23,"Pranks"),
        Soundtrack(24,"Soundtrack"),
        EuroTechno(25,"Euro-Techno"),
        Ambient(26,"Ambient"),
        TripHop(27,"Trip-Hop"),
        Vocal(28,"Vocal"),
        JazzFunk(29,"Jazz+Funk"),
        Fusion(30,"Fusion"),
        Trance(31,"Trance"),
        Classical(32,"Classical"),
        Instrumental(33,"Instrumental"),
        Acid(34,"Acid"),
        House(35,"House"),
        Game(36,"Game"),
        SoundClip(37,"Sound Clip"),
        Gospel(38,"Gospel"),
        Noise(39,"Noise"),
        AlternRock(40,"AlternRock"),
        Bass(41,"Bass"),
        Soul(42,"Soul"),
        Punk(43,"Punk"),
        Space(44,"Space"),
        Meditative(45,"Meditative"),
        InstrumentalPop(46,"Instrumental Pop"),
        InstrumentalRock(47,"Instrumental Rock"),
        Ethnic(48,"Ethnic"),
        Gothic(49,"Gothic"),
        Darkwave(50,"Darkwave"),
        TechnoIndustrial(51,"Techno-Industrial"),
        Electronic(52,"Electronic"),
        PopFolk(53,"Pop-Folk"),
        Eurodance(54,"Eurodance"),
        Dream(55,"Dream"),
        SouthernRock(56,"Southern Rock"),
        Comedy(57,"Comedy"),
        Cult(58,"Cult"),
        Gangsta(59,"Gangsta"),
        Top40(60,"Top 40"),
        ChristianRap(61,"Christian Rap"),
        PopFunk(62,"Pop/Funk"),
        Jungle(63,"Jungle"),
        NativeAmerican(64,"Native American"),
        Cabaret(65,"Cabaret"),
        NewWave(66,"New Wave"),
        Psychadelic(67,"Psychadelic"),
        Rave(68,"Rave"),
        Showtunes(69,"Showtunes"),
        Trailer(70,"Trailer"),
        LoFi(71,"Lo-Fi"),
        Tribal(72,"Tribal"),
        AcidPunk(73,"Acid Punk"),
        AcidJazz(74,"Acid Jazz"),
        Polka(75,"Polka"),
        Retro(76,"Retro"),
        Musical(77,"Musical"),
        RockAndRoll(78,"Rock & Roll"),
        HardRock(79,"Hard Rock"),

        // the winamp extensions
        Folk(80,"Folk"),
        FolkRock(81,"Folk-Rock"),
        NationalFolk(82,"National Folk"),
        Swing(83,"Swing"),
        FastFusion(84,"Fast Fusion"),
        Bebop(85,"Bebop"),
        Latin(86,"Latin"),
        Revival(87,"Revival"),
        Celtic(88,"Celtic"),
        Bluegrass(89,"Bluegrass"),
        Avantgarde(90,"Avantgarde"),
        GothicRock(91,"Gothic Rock"),
        ProgressiveRock(92,"Progressive Rock"),
        PsychedelicRock(93,"Psychedelic Rock"),
        SymphonicRock(94,"Symphonic Rock"),
        SlowRock(95,"Slow Rock"),
        BigBand(96,"Big Band"),
        Chorus(97,"Chorus"),
        EasyListening(98,"Easy Listening"),
        Acoustic(99,"Acoustic"),
        Humour(100,"Humour"),
        Speech(101,"Speech"),
        Chanson(102,"Chanson"),
        Opera(103,"Opera"),
        ChamberMusic(104,"Chamber Music"),
        Sonata(105,"Sonata"),
        Symphony(106,"Symphony"),
        BootyBass(107,"Booty Bass"),
        Primus(108,"Primus"),
        PornGroove(109,"Porn Groove"),
        Satire(110,"Satire"),
        SlowJam(111,"Slow Jam"),
        Club(112,"Club"),
        Tango(113,"Tango"),
        Samba(114,"Samba"),
        Folklore(115,"Folklore"),
        Ballad(116,"Ballad"),
        PowerBallad(117,"Power Ballad"),
        RhytmicSoul(118,"Rhythmic Soul"),
        Freestyle(119,"Freestyle"),
        Duet(120,"Duet"),
        PunkRock(121,"Punk Rock"),
        DrumSolo(122,"Drum Solo"),
        ACapella(123,"A capella"),
        EuroHouse(124,"Euro-House"),
        DanceHall(125,"Dance Hall");

        private int id;
        private String name;

        Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        private static HashMap<String,Genre> namesToNumbersMap;

        static {
            namesToNumbersMap = new HashMap<String, Genre>(2*values().length);
            for (Genre genre : values()) {
                namesToNumbersMap.put(genre.getName(), genre);
            }
        }

        public static Genre getGenreByName(String name) {
            return namesToNumbersMap.get(name);
        }

        public static Genre getGenreByByteId(byte id) {
            int convertedByte = (id >= 0 ? id : (int)id + 256);
            if (convertedByte < 0 || convertedByte >= values().length) {
                return null;
            } else {
                return values()[convertedByte];
            }
        }

        public static Genre getGenreByIntId(int id) {
            if (id < 0 || id >= values().length) {
                return null;
            } else {
                return values()[id];
            }
        }
    }

}
