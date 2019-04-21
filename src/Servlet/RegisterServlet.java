package Servlet;

import Dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    private UserDao dao=new UserDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        System.out.println(name + " " + pwd);
        User u=new User(name,pwd);
        try {
            dao.addUser(u);
            resp.getWriter().print("success");
        } catch (SQLException e) {
            resp.getWriter().print("error");
            e.printStackTrace();

        }
    }
}
