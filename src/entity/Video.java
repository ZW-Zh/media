package entity;

public class Video {
    private int id;
    private String title;
    private long time;
    private String path;

    public Video(int id, String title, long time, String path) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.path = path;
    }

    public Video(String title, long time, String path) {
        this.title = title;
        this.time = time;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
