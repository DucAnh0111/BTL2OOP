package BomberGame.GameController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {
    public static void play(String s) {
        File file = new File("C:\\Users\\admin\\Desktop\\BTLSo2\\BTL2OOP\\Resourses\\SFX\\Theme.wav");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public static void main(String[] args) {
        Sound s = new Sound();
        s.play("Theme");
    }
}
