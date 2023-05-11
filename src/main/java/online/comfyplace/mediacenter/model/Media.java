package online.comfyplace.mediacenter.model;

public abstract class Media {
    private String basePath;
    private String title;

    public Media(String basePath, String title) {
        this.basePath = basePath;
        this.title = title;
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
}
