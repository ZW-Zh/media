package Servlet;

import Dao.MediaDao;
import com.google.gson.Gson;
import entity.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetVideoServlet extends HttpServlet {
    private MediaDao dao=new MediaDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> list=dao.selectAllVideo();
        Gson gson=new Gson();
        String json=gson.toJson(list);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
}
