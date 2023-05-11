package online.comfyplace.mediacenter.controller;

import online.comfyplace.mediacenter.model.Media;
import online.comfyplace.mediacenter.model.Movie;
import online.comfyplace.mediacenter.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class MediaController {
    @Autowired
    private MediaRepository mediaRepository;

    @GetMapping("/media")
    public ResponseEntity<List<? extends Media>> getMedia() {
        try {
            return ResponseEntity.ok(mediaRepository.getMedia().get(Movie.class));
        } catch (IOException e) {
            // TODO logging
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
