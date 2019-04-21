package Servlet;

import Dao.TypeDao;
import com.google.gson.Gson;
import entity.Music;
import entity.MusicType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetTypeServlet extends HttpServlet {
    private TypeDao dao=new TypeDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MusicType> list=dao.getType();
        Gson gson=new Gson();
        String json=gson.toJson(list);
        System.out.println(json);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
}
