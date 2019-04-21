package Utils;

import entity.Music;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static String getFileName(String fileName) {
        String name = null;
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0) {
                    /* 获取文件名*/
            name = fileName.substring(0, dotIndex);
        }
        return name;
    }

    public static String getFileEnd(String fileName) {
        String end = null;
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0) {
                    /* 获取文件的后缀名*/
            end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        }
        return end;
    }

    public static Music Mp3InfoRead(String path) {
        MP3File file;
        try {
            file = new MP3File(path);
            String songName = file.getID3v2Tag().frameMap.get("TIT2").toString();
            String artist = file.getID3v2Tag().frameMap.get("TPE1").toString();
            String album = file.getID3v2Tag().frameMap.get("TALB").toString();
            int length = file.getMP3AudioHeader().getTrackLength();
            songName = songName.substring(6, songName.length() - 3).replaceAll("[\u0000]", "");
            artist = artist.substring(6, artist.length() - 3).replaceAll("[\u0000]", "");
            album = album.substring(6, album.length() - 3).replaceAll("[\u0000]", "");


            Music music = new Music();
            music.setTitle(songName);
            music.setAlbum(album);
            music.setLength(length);
            music.setArtist(artist);
            music.setAlbumBip(getMp3Picture(path));
            return music;
        } catch (IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
            e.printStackTrace();
            throw new RuntimeException("获取Mp3 tag信息出错！");

        }

//          System.out.println("歌名"+songName);
//          System.out.println("歌手"+singer);
//          System.out.println("专辑:"+author);
    }

    public static String getMp3Picture(String mp3path) {
        String storePath;
        try {
            String url = mp3path;
            File sourceFile = new File(url);
            MP3File mp3file = new MP3File(sourceFile);

            AbstractID3v2Tag tag = mp3file.getID3v2Tag();
            AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
            FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
            byte[] imageData = body.getImageData();
            storePath = mp3path;
            storePath = storePath.substring(0, storePath.length() - 3);
            storePath += "jpg";
            System.out.println(storePath);
            FileOutputStream fos = new FileOutputStream(storePath);
            fos.write(imageData);
            fos.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("读取Mp3图片出错");
        }
        return new File(storePath).getName();
    }

    public static long getVideoTime(String path) {
        System.out.println(path);
        File file = new File(path);
        Encoder encoder = new Encoder();
        long time=0;
        try {
            MultimediaInfo m = encoder.getInfo(file);
            time = m.getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

}
