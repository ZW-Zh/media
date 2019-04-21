package Servlet;

import Dao.LikedMusicDao;
import Dao.MediaDao;
import com.google.gson.Gson;
import entity.LoginInfo;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetLikedMusic extends HttpServlet {
    private LikedMusicDao dao=new LikedMusicDao();
    private MediaDao mediaDao=new MediaDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int userid=Integer.valueOf(req.getParameter("userid"));
        List<Integer> list= dao.selectLikedMusic(userid);
        List<Music> musicList=new ArrayList<>();
        for(int musicid:list){
            Music music=mediaDao.selectById(musicid);
            musicList.add(music);
        }
        Gson gson=new Gson();
        String json=gson.toJson(musicList);
        System.out.println(json);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
}
