package Servlet;

import Dao.MediaDao;
import com.google.gson.Gson;
import entity.Music;

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

public class GetMusicServlet extends HttpServlet {
    private MediaDao dao=new MediaDao();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //System.out.println(req.getSession().getServletContext().getRealPath("Music"));
//        List<Music> music= dao.selectAllMusic();
//        Gson gson=new Gson();
//        String json=gson.toJson(music);
//        System.out.println(json);
//        resp.setCharacterEncoding("utf-8");
//        resp.getWriter().print(json);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int type=Integer.valueOf(req.getParameter("type"));
        List<Music> music= dao.selectByType(type);
        Gson gson=new Gson();
        String json=gson.toJson(music);
        System.out.println(json);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
}
