package Dao;

import Utils.IObjectMapper;
import Utils.JdbcTemplate;
import entity.Music;
import entity.MusicType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TypeDao {

    @SuppressWarnings("unchecked")
    public List<MusicType> getType(){
        List<MusicType> typeList= null;
        try {
            typeList = JdbcTemplate.executeQuery("select * from musicType", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    MusicType type=new MusicType();
                    type.setId(rs.getInt("id"));
                    type.setType(rs.getString("name"));
                    return type;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }
}
