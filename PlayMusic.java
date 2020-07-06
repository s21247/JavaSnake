package sample;
import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayMusic
{

    public void start () throws Exception {

        String path = "C:\\Snake\\src\\sample\\WONSZ.mp3";

        Media hit = new Media(Paths.get(path).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        //mediaPlayer.setRate(a);
        mediaPlayer.setVolume(2);
        mediaPlayer.play();

    }


}