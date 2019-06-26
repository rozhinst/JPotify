import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DisplaySongs {

    public void showAlbumSongs(Albums album){//parameter area
        ArrayList songs = album.getAlbumSongs();


    }
    public void addAlbum() throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList<Albums> albums = Songs.reafAlbumsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
        Albums album = new Albums();
        if (albums == null) albums = new ArrayList();
        JFileChooser chooser = new JFileChooser("src");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File[] filesInDirectory = chooser.getCurrentDirectory().listFiles();
        Song song = new Song();
        for (int i = 0; i < filesInDirectory.length; i++) {
            GetID3 id3 = new GetID3(filesInDirectory[i].getAbsolutePath());
            song.setName(id3.getDetails().get(0));
            song.setPath(filesInDirectory[i].getAbsolutePath());
            //album.setAlbumSong(song); problemmmmmmmmmmmmmmmmmmmmmmmmmmm
        }
        //album.setName();
        albums.add(album);
        Songs.writeAlbumsToFile(albums, "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");

    }
    public void creatPlayList(String name) throws IOException {
        ArrayList<PlayList> playLists = Songs.reafPlayListFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\playlists\\playists.bin");
        PlayList playList = new PlayList(name);
        if(playLists == null) playLists = new ArrayList();
        playLists.add(playList);
        Songs.writeLibrariesToFile(playLists,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\playlists\\playists.bin");


    }
    public void removePlaylist(PlayList playList ) throws IOException {
        ArrayList<PlayList> playLists =  Songs.reafPlayListFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\playlists\\playists.bin");
        Iterator it = playLists.iterator();
        while(it.hasNext()){
            PlayList play = (PlayList) it.next();
            if(play.equals(playList)){
                playLists.remove(play);
            }
        }
        Songs.writeLibrariesToFile(playLists, "C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\playlists\\playists.bin");
    }
    public void managingAlbumSongs(Song song) throws InvalidDataException, IOException, UnsupportedTagException {
        ArrayList<Albums> albums = Songs.reafAlbumsFromFile("C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
        if(albums == null) albums = new ArrayList<>();
        boolean exist = false;
        ArrayList<Song> albumSongs = new ArrayList<>();
        GetID3 id3 = new GetID3(song.getPath());
        String [] s = id3.getDetails().get(1).split(":");
        for (int i=0;i<albums.size();i++){
            if(s[1].equals(albums.get(i).getName())){
              albumSongs =  albums.get(i).getAlbumSongs();
              albumSongs.add(song);
              albums.get(i).setAlbumSong(albumSongs);
              exist = true;
              break;
            }
        }
        if(exist == false || albums.size() == 0 ){
            Albums album = new Albums();
            albumSongs.add(song);
            album.setAlbumSong(albumSongs);
            album.setId3(id3);
            System.out.println("torokhoda doros sho  "+album.getAlbumSongs().size());
            //album.setName();
            albums.add(album);
        }
        for(int i =0;i<albums.size();i++) System.out.println(albums.get(i).getName());
        Songs.writeAlbumsToFile(albums,"C:\\Users\\LENOVO\\Desktop\\JPotify\\JPotify\\src\\songs\\Albums.bin");
    }

    }

//class Main3{
//    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
//        DisplaySongs songs = new DisplaySongs();
//        //songs.showSongs();
//
//    }
//}