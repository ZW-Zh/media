package Dao;

import Utils.IObjectMapper;
import Utils.JdbcTemplate;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    @SuppressWarnings("unchecked")
    public User selectByUserName(String name) throws SQLException {
        List<User> list=new ArrayList<>();

            list= JdbcTemplate.executeQuery("select * from user where name=?", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    User u=new User(rs.getInt("id"),rs.getString("name"),rs.getString("pwd"));
                    return u;
                }
            },name);

        return list.isEmpty()? null:list.get(0);
    }
    public void addUser(User u) throws SQLException {

            JdbcTemplate.executeUpdate("insert into user(name,pwd) values(?,?)",u.getName(),u.getPwd());

    }
}
