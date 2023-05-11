package online.comfyplace.mediacenter.repository;

import online.comfyplace.mediacenter.model.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Repository
public class MediaRepository {
    private final static Set<String> VIDEO_FORMATS = Set.of("webm", "mkv", "flv", "avi", "mov", "wmv", "mp4", "mpg", "mpeg", "m4v", "3gp");

    private final Path moviesPath;
    private final Path seriesPath;

    public MediaRepository(@Value("${media.path}") String mediaBasePath, @Value("${media.path.movies}") String moviesPath, @Value("${media.path.series}") String seriesPath) {
        final var basePath = Paths.get(mediaBasePath);
        this.moviesPath = basePath.resolve(moviesPath);
        this.seriesPath = basePath.resolve(seriesPath);
    }

    public Map<Class<? extends Media>, List<? extends Media>> getMedia() throws IOException {
        // TODO cache media entries
        final Map<Class<? extends Media>, List<? extends Media>> media = new HashMap<>();
        try (Stream<Path> moviePaths = Files.list(moviesPath);
             Stream<Path> seriesPaths = Files.list(seriesPath)) {
            media.put(Movie.class, moviePaths.parallel().map(MediaRepository::pathToMovie).toList());
            media.put(Series.class, seriesPaths.parallel().map(MediaRepository::pathToSeries).toList());
        }
        return media;
    }

    private static Movie pathToMovie(final Path path) {
        var movieFile = path.toFile();
        if (movieFile.isDirectory()) {
            movieFile = Arrays.stream(Objects.requireNonNull(movieFile.listFiles()))
                    .filter(file -> VIDEO_FORMATS.contains(FilenameUtils.getExtension(file.getName())))
                    .findFirst().orElse(null);

        } else {
            // TODO log error
            movieFile = null;
        }
        if (movieFile != null) {
            //TODO read info file
            return new Movie(movieFile.getAbsolutePath(), FilenameUtils.getBaseName(movieFile.getName()), null);
        } else {
            return null;
        }
    }

    private static Series pathToSeries(final Path path) {
        var seriesBaseDir = path.toFile();
        if (!seriesBaseDir.isDirectory()) {
            // TODO log error
            return null;
        }

        var series = new Series(seriesBaseDir.getAbsolutePath(), FilenameUtils.getBaseName(seriesBaseDir.getName()), null);
        Arrays.stream(Objects.requireNonNull(seriesBaseDir.listFiles())).parallel()
                .map(File::toPath)
                .map(MediaRepository::pathToSeason)
                .forEach(series::addSeason);

        return series;
    }

    private static Season pathToSeason(final Path path) {
        var seasonBaseDir = path.toFile();
        if (!seasonBaseDir.isDirectory()) {
            return null;
        }

        try {
            var seasonNumber = Integer.parseInt(FilenameUtils.getBaseName(seasonBaseDir.getName()));
            var season = new Season(seasonNumber);

            Arrays.stream(Objects.requireNonNull(seasonBaseDir.listFiles())).parallel()
                    .map(File::toPath)
                    .map(MediaRepository::pathToEpisode)
                    .forEach(season::addEpisode);

            return season;

        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Episode pathToEpisode(final Path path) {
        var episodeDir = path.toFile();
        if (!episodeDir.isDirectory()) {
            return null;
        }
        var episodeFile = Arrays.stream(Objects.requireNonNull(episodeDir.listFiles()))
                .filter(file -> VIDEO_FORMATS.contains(FilenameUtils.getExtension(file.getName())))
                .findFirst().orElse(null);

        if (episodeFile != null) {
            try {
                var episodeNumber = Integer.parseInt(FilenameUtils.getBaseName(episodeDir.getName()));
                return new Episode(episodeNumber);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
