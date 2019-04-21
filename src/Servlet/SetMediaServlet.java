package Servlet;

import Dao.MediaDao;
import Utils.FileUtil;
import entity.Music;
import entity.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SetMediaServlet extends HttpServlet {
    List<Music> musicList = new ArrayList<>();
    List<String> lrclist = new ArrayList<>();
    List<Video> videoList=new ArrayList<>();
    private MediaDao dao=new MediaDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        videoList.clear();
        musicList.clear();
        File file = new File(req.getSession().getServletContext().getRealPath("Media"));
        List<File> files = new ArrayList<>();
        Collections.addAll(files, file.listFiles());
        for (File f : files) {
            System.out.println(f.getName());
            String fileName = f.getName();
            String end = FileUtil.getFileEnd(fileName);
            if (!Objects.equals(end, "")) {
                if (Objects.equals(end, ".mp3")) {
                    //如果是音乐文件
                    Music music=FileUtil.Mp3InfoRead(f.getAbsolutePath());
                    music.setPath(fileName);
                    musicList.add(music);
                } else if (Objects.equals(end, ".lrc")) {
                    //如果是歌词文件
                    lrclist.add(fileName);
                }
                if(Objects.equals(end,".mp4")){
                    Video video=new Video(FileUtil.getFileName(fileName),FileUtil.getVideoTime(f.getAbsolutePath()),fileName);
                    videoList.add(video);
                }

            }
        }
        for (Music m:musicList){
            for(String lrc:lrclist){
                if(FileUtil.getFileName(m.getPath()).equals(FileUtil.getFileName(lrc))){
                    m.setLrcPath(lrc);
                }
            }
            System.out.println(m.getPath()+" "+m.getAlbum()+" "+m.getArtist());
            dao.addMusic(m);
        }
        for(Video v:videoList){
            dao.addVideo(v);
        }

    }
}

