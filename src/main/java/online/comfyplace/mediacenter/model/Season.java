package online.comfyplace.mediacenter.model;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private final List<Episode> episodes = new ArrayList<>();

    private int number;

    public Season(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void addEpisode(final Episode episode) {
        this.episodes.add(episode);
    }

    public void removeEpisode(final Episode episode) {
        this.episodes.remove(episode);
    }
}
