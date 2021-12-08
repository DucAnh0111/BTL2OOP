package BomberGame;
import BomberGame.Entity.BombAndFlame.Bomb;
import BomberGame.Entity.BombAndFlame.Flame;
import BomberGame.Entity.Entity;
import BomberGame.GameController.InputManage;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Player.Player;
import BomberGame.Sence.board;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.Vector;

public class GameLoop {

    static double currentTime;
    static double prevTime;
    final static long startTime = System.nanoTime();
    public static double getCurrentTime() {
        return currentTime;
    }
    public static void start(GraphicsContext gc) {
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                prevTime = currentTime;
                currentTime = (currentNanoTime - startTime) / (1000000000.0);
                gc.clearRect(0, 0, GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_WIDTH);
                updateGame();
                renderGame();
            }
        }.start();
    }

    public static void updateGame() {
        InputManage.handlePlayerMovements();
        Vector<Entity> entities = board.getEntities();
        Player player = board.getPlayer();
        if (GloVariables.NewGame) {
            board.NewGame();
            GloVariables.NewGame = false;
            GloVariables.passLevel = false;
        }
        for (int i = 0; i < entities.size(); ++i) {
            if (GloVariables.CameraMoving) {
                entities.elementAt(i).setOffset();
            }
            try {
                if (!(entities.elementAt(i) instanceof Bomb) && entities.elementAt(i).remove()) {
                    entities.remove(i);
                }
                if (entities.elementAt(i) instanceof Bomb) {
                    if (entities.elementAt(i).remove()) {
                        entities.remove(i);
                        player.incrementBombCount();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }


    public static void renderGame() {
        Vector<Entity> entities = board.getEntities();
        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.elementAt(i);
            if (e instanceof Flame) {
                if (((Flame) e).getFlameState()) {
                    e.render();
                }
            } else {
                e.render();
            }
        }
    }

}

