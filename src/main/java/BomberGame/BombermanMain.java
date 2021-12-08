package BomberGame;

import BomberGame.GameController.Audio;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Sence.board;

import java.io.IOException;
public class BombermanMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BombermanMain.class.getResource("/BomberGame/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 350);
        stage.setTitle(GloVariables.GAME_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public void newGame() {
        Stage stage = new Stage();
        Audio.ThemeMusic();
        stage.setTitle(GloVariables.GAME_NAME);
        board.setupScene();
        Scene s = board.getScene();
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    Button buttonNewGame;
    @FXML
    Button buttonContinue;

    @FXML
    Button buttonGuide;

    @FXML
    Button buttonInfo;

    @FXML
    ImageView myImageview;

    public void NewGame() {
        System.out.println("new game");
        newGame();
    }

    public void continueGame() {
        System.out.println("continue game");
    }

    public void guide() {
        System.out.println("guide");
    }

    public void info() {
        System.out.println("Information");
    }


}