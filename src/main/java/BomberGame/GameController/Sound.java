package BomberGame.GameController;
import java.awt.*;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
public class Sound {
    public static void init(String string) {
        String path = "C:\\Users\\admin\\Desktop\\BTLSo2\\BTL2OOP\\Resourses\\SFX\\" + string + ".wav";
        File f = new File(path);
        Media media = new Media(f.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public static void play(String string) {
        init(string);
    }
}
