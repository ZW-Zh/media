package Servlet;

import Dao.MediaDao;
import com.google.gson.Gson;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchMusicServlet extends HttpServlet {
    private MediaDao dao=new MediaDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name=req.getParameter("search");
        List<Music> music= dao.selectByName(name);
        Gson gson=new Gson();
        String json=gson.toJson(music);
        System.out.println(json);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
}
