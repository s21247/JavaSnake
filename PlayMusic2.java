package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import java.nio.file.Paths;

public class PlayMusic2 {

    public void start () throws Exception {

        String path = "C:\\Users\\Użytkownik\\Downloads\\Mooo.mp3";

        Media hit = new Media(Paths.get(path).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setVolume(2);
        mediaPlayer.play();

    }

}
