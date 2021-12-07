package BomberGame;

import BomberGame.GameController.Audio;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Sence.board;

import static javafx.scene.paint.Color.color;

public class BombermanMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
        primaryStage.setTitle(GloVariables.GAME_NAME);
        Button b1 = new Button("New Game");
        b1.setMaxSize(300,100);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("click new game");
                newGame();
            }
        });


        Button b2 = new Button("Continue");
        b2.setMaxSize(300,100);
        VBox vBox = new VBox();

        Button b3 = new Button("Guide");
        b3.setMaxSize(300,100);

        Button b4 = new Button("Thông tin");
        b4.setMaxSize(300,100);

        Button b5 = new Button("2 người chơi");
        b4.setMaxSize(300,100);

        vBox.getChildren().addAll(b1,b2,b3,b4,b5);
        primaryStage.setScene(new Scene(vBox,300,300));
        primaryStage.show();
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

    public void continueGame() {
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
}