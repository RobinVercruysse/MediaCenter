package online.comfyplace.mediacenter.controller;

import online.comfyplace.mediacenter.model.Media;
import online.comfyplace.mediacenter.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MediaController {
    @Autowired
    private MediaRepository mediaRepository;

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
}
