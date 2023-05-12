package online.comfyplace.mediacenter.controller;

import online.comfyplace.mediacenter.model.Media;
import online.comfyplace.mediacenter.model.Player;
import online.comfyplace.mediacenter.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class MediaController {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private Player player;

    @GetMapping("/media")
    public ResponseEntity<Map<Class<? extends Media>, List<? extends Media>>> getMedia() {
        try {
            return ResponseEntity.ok(mediaRepository.getMedia());
        } catch (IOException e) {
            // TODO logging
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/media/{reference}/play")
    public ResponseEntity<String> playMedia(@PathVariable String reference) {
        var pathString = new String(Base64.getDecoder().decode(reference), StandardCharsets.UTF_8);
        player.getMediaPlayer().mediaPlayer().media().play(Paths.get(pathString).toUri().toString());
        return ResponseEntity.ok(pathString);
    }
}
