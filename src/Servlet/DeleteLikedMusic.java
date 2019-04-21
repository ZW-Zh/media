package Servlet;

import Dao.LikedMusicDao;
import entity.LikedMusic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLikedMusic extends HttpServlet {
    private LikedMusicDao dao=new LikedMusicDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int userid=Integer.valueOf(req.getParameter("userid"));
        int musicid=Integer.valueOf(req.getParameter("musicid"));
        dao.deleteLikeMusic(userid,musicid);
        resp.getWriter().print("success");
    }
}
