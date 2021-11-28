package BomberGame.GameController;

import BomberGame.Entity.BombAndFlame.Bomb;
import BomberGame.Entity.Enemy.Balloon;
import BomberGame.GloVariables.Direction;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Player.Player;
import BomberGame.Sence.board;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Vector;

public class InputManage {
    public static void handlePlayerMovements() {

        List keyInputs = Event.getInputList();
        Player player = board.getPlayer();
        Vector<Balloon> balloons = board.getBalloons();
        for (int i = 0; i < balloons.size(); i++) {
          balloons.elementAt(i).RandomMoving();
        }

        if (keyInputs.contains(KeyCode.UP)) {
            player.move(player.step, Direction.UP);
            System.out.println("move up");
        }
        if (keyInputs.contains(KeyCode.DOWN)) {
            player.move(player.step, Direction.DOWN);
            System.out.println("move down");

        }
        if (keyInputs.contains(KeyCode.LEFT)) {
            if (player.updatePosition()) {
                GloVariables.CameraMoving = true;
            }
            player.move(player.step, Direction.LEFT);
            System.out.println("move left");

        }
        if (keyInputs.contains(KeyCode.RIGHT)) {
            if (player.updatePosition()) {
                GloVariables.CameraMoving = true;
            }
            player.move(player.step, Direction.RIGHT);
            System.out.println("move right");

        }

        if (!keyInputs.contains(KeyCode.RIGHT) &&
                !keyInputs.contains(KeyCode.DOWN) &&
                !keyInputs.contains(KeyCode.LEFT) &&
                !keyInputs.contains(KeyCode.UP)
        ) {
            GloVariables.CameraMoving = false;
            player.move(0, Direction.UP);
        }

        if (keyInputs.contains(KeyCode.SPACE)) {
            if (player.hasMoreBombs()) {
                double temp = 48.0;
                int a = player.getPositionX();
                int b = player.getPositionY();
                double c = Math.round((double) a / temp);
                double e = Math.round((double) b / temp);
                board.addEntityToGame(new Bomb((int) c * 48 + 8, (int) e * 48 + 8));
                player.decrementBombCount();
                keyInputs.remove(KeyCode.SPACE);
                System.out.println("set bomb");
            }
        }

    }
}
