package Servlet;

import Dao.LikedMusicDao;
import Dao.UserDao;
import com.google.gson.Gson;
import entity.LoginInfo;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class loginServlet extends HttpServlet {
    private UserDao dao = new UserDao();
    private LikedMusicDao likedMusicDao=new LikedMusicDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        System.out.println(name + " " + pwd);
        User user = null;
        try {
            user = dao.selectByUserName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        if (user != null) {
            if (pwd.equals(user.getPwd())) {
                List<Integer> list= likedMusicDao.selectLikedMusic(user.getId());
                LoginInfo loginInfo=new LoginInfo(user.getId(),list);
                Gson gson=new Gson();
                String json=gson.toJson(loginInfo);
                System.out.println(json);
                resp.getWriter().print(json);

            } else {
                resp.getWriter().print("error1");
            }
        } else {
            resp.getWriter().print("error2");
        }
    }

}

