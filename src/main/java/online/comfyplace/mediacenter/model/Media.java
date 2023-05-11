package online.comfyplace.mediacenter.model;

public abstract class Media {
    private String basePath;
    private String title;

    private MediaInfo info;

    public Media(String basePath, String title, MediaInfo info) {
        this.basePath = basePath;
        this.title = title;
        this.info = info;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaInfo getInfo() {
        return info;
    }

    public void setInfo(MediaInfo info) {
        this.info = info;
    }
}
