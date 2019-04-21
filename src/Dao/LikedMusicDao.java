package Dao;

import Utils.IObjectMapper;
import Utils.JdbcTemplate;
import entity.Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikedMusicDao {
    @SuppressWarnings("unchecked")
    public List<Integer> selectLikedMusic(int userid){
        List<Integer> list=new ArrayList<>();
        try {
            list= JdbcTemplate.executeQuery("select musicid from LIKEMUSIC where userid=?", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    return rs.getInt("musicid");
                }
            },userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addLikeMusic(int userid,int musicid){
        try {
            JdbcTemplate.executeUpdate("insert into LIKEMUSIC(userid,musicid) values(?,?)",userid,musicid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteLikeMusic(int userid,int musicid){
        try {
            JdbcTemplate.executeUpdate("delete from LIKEMUSIC where userid=? and musicid=?",userid,musicid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
