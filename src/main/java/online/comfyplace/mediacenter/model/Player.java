package online.comfyplace.mediacenter.model;

import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

@Component
public class Player {
    private final EmbeddedMediaPlayerComponent mediaPlayer;

    public Player() {
        this.mediaPlayer = new EmbeddedMediaPlayerComponent();
        var frame = new JFrame();
        frame.setContentPane(mediaPlayer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public EmbeddedMediaPlayerComponent getMediaPlayer() {
        return mediaPlayer;
    }
}
