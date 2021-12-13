package BomberGame;

import BomberGame.GameController.Sound;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Sence.board;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
public class BombermanMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BombermanMain.class.getResource("/BomberGame/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 350);
        stage.setTitle(GloVariables.GAME_NAME);
        stage.setScene(scene);
        stage.show();
        Sound.play("Theme");
    }

    public void newGame() {
        Stage stage = new Stage();
        Sound.play("Theme");
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
    Button hightcore;

    @FXML
    ImageView myImageview;

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    public void NewGame() {
        System.out.println("new game");
        newGame();
        tf1.setVisible(false);
        tf2.setVisible(false);
    }

    public void continueGame() {
        System.out.println("continue game");
    }

    public void guide() {
        System.out.println("guide");
        tf1.setVisible(true);
        tf2.setVisible(false);
    }

    public void info() {
        tf1.setVisible(false);
        tf2.setVisible(true);
        System.out.println("Information");
    }
}