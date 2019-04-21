package entity;

import java.util.List;

public class LoginInfo {
    private int userId;
    private List<Integer> musicId;

    public LoginInfo(int userId, List<Integer> musicId) {
        this.userId = userId;
        this.musicId = musicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getMusicId() {
        return musicId;
    }

    public void setMusicId(List<Integer> musicId) {
        this.musicId = musicId;
    }
}
