package Dao;

import Utils.IObjectMapper;
import Utils.JdbcTemplate;
import entity.Music;
import entity.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDao  {
    public void addMusic(Music music){
        try {
            JdbcTemplate.executeUpdate("insert into music(path,lrcpath,title,artist,album,length,albumBip)values(?,?,?,?,?,?,?)",
                    music.getPath(),music.getLrcPath(),music.getTitle(),music.getArtist(),music.getAlbum(),music.getLength(),music.getAlbumBip());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Music> selectAllMusic(){
        List<Music> list=new ArrayList<>();
        try {
            list=JdbcTemplate.executeQuery("select * from music", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    Music music=new Music(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("artist"),
                            rs.getString("album"),
                            rs.getInt("length"),
                            rs.getString("albumBip"),
                            rs.getString("path"),
                            rs.getString("lrcPath")
                            );
                    return music;

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addVideo(Video v) {
        try {
            JdbcTemplate.executeUpdate("insert into video(title,time,path) values(?,?,?)",v.getTitle(),v.getTime(),v.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public List<Video> selectAllVideo(){
        List<Video> list=new ArrayList<>();
        try {
            list=JdbcTemplate.executeQuery("select * from video", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    Video v=new Video(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getLong("time"),
                            rs.getString("path"));
                    return v;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    public Music selectById(int musicid){
        List<Music> list=new ArrayList<>();
        try {
            list=JdbcTemplate.executeQuery("select * from music where id=?", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    Music music=new Music(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("artist"),
                            rs.getString("album"),
                            rs.getInt("length"),
                            rs.getString("albumBip"),
                            rs.getString("path"),
                            rs.getString("lrcPath")
                    );
                    return music;

                }
            },musicid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size()==0? null:list.get(0);
    }
    @SuppressWarnings("unchecked")
    public List<Music> selectByName(String name){
        List<Music> list=new ArrayList<>();
        try {
            list=JdbcTemplate.executeQuery("select * from music where title like '%"+name+"%'", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    Music music=new Music(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("artist"),
                            rs.getString("album"),
                            rs.getInt("length"),
                            rs.getString("albumBip"),
                            rs.getString("path"),
                            rs.getString("lrcPath")
                    );
                    return music;

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    public List<Music> selectByType(int type){
        List<Music> list=new ArrayList<>();
        try {
            list=JdbcTemplate.executeQuery("select * from music where type=? ", new IObjectMapper() {
                @Override
                public Object getObjectFromRow(ResultSet rs) throws SQLException {
                    Music music=new Music(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("artist"),
                            rs.getString("album"),
                            rs.getInt("length"),
                            rs.getString("albumBip"),
                            rs.getString("path"),
                            rs.getString("lrcPath")
                    );
                    return music;

                }
            },type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
