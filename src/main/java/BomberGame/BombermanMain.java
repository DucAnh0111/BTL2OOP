package BomberGame;

import BomberGame.Entity.Tiles.Grass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import BomberGame.GloVariables.GloVariables;
import BomberGame.Sence.board;

import java.io.IOException;

public class BombermanMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(GloVariables.GAME_NAME);
        board.setupScene();
        Scene s = board.getScene();
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}