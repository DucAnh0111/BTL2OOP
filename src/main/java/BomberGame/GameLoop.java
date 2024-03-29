package BomberGame;
import BomberGame.Entity.BombAndFlame.Bomb;
import BomberGame.Entity.BombAndFlame.Flame;
import BomberGame.Entity.Entity;
import BomberGame.GameController.InputManage;
import BomberGame.GameController.Sound;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Player.Player;
import BomberGame.Sence.board;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import java.io.IOException;
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
                Player.isImmortal = Player.immortalTime != 0 && (currentTime - Player.immortalTime) < 4;
                gc.clearRect(0, 0, GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_WIDTH);
                if(board.enemy == 0) {
                    System.out.println("nextlever");
                    GloVariables.Level ++;
                    Sound.play("passlevel");
                    nextLever();
                }
                updateGame();
                renderGame();
            }
        }.start();
    }

    public static void nextLever() {
        board.entities.clear();
        board.balloons.clear();
        board.tiles.clear();
        board.setTextBoard(GloVariables.Level);
        board.setTextPOINT(GloVariables.point);
        if (!GloVariables.passLevel) {
            Player.step = 4;
            Player.bombCount = 1;
            Bomb.radius = 1;
        }
        try {
            board.loadMap();
        } catch (IOException e) {
            System.err.println("Unable to load map file.");
            System.exit(1);
        }
    }

    public static void updateGame() {
        InputManage.handlePlayerMovements();
        Vector<Entity> entities = board.getEntities();
        Player player = board.getPlayer();
        board.setTextPOINT(GloVariables.point);

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
                        Sound.play("Bombplanted");
                        entities.remove(i);
                        player.incrementBombCount();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
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

