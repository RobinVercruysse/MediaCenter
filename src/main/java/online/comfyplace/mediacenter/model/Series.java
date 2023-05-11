package online.comfyplace.mediacenter.model;

import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
    private final List<Season> seasons = new ArrayList<>();

    public Series(String basePath, String title, SeriesInfo info) {
        super(basePath, title, info);
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void addSeason(final Season season) {
        this.seasons.add(season);
    }

    public void removeSeason(final Season season) {
        this.seasons.remove(season);
    }
}
