package entity;

public class LikedMusic {
    private int id;
    private int userId;
    private int musicId;

    public LikedMusic(int id, int userId, int musicId) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
    }

    public LikedMusic(int userId, int musicId) {
        this.userId = userId;
        this.musicId = musicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }
}
