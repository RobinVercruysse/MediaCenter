package online.comfyplace.mediacenter.model;

public class Episode {
    private int number;

    private String path;

    public Episode(int number, String path) {
        this.number = number;
        this.path = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
