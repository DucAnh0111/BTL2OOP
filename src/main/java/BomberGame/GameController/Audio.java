package BomberGame.GameController;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class Audio{
    public static void ThemeMusic() {
        String path = "Resourses\\SFX\\Theme.wav";
        try {

        } catch (Exception e) {

        }
    }

    public static String loadFile(String path) {
        File file = new File(path);
        String filePath = file.getAbsolutePath();
        return filePath;
    }
}
