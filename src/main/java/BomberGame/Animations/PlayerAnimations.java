package BomberGame.Animations;

import BomberGame.Entity.Entity;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PlayerAnimations {
    Sprite moveRight;
    Sprite moveLeft;
    Sprite moveUp;
    Sprite moveDown;
    Sprite idle;
    Sprite die;
    double playSpeed;

    public PlayerAnimations(Entity e, double scale) {
        Image img = Render.getMainSheet();
        playSpeed = 0.1;
        moveDown = new Sprite(e, 30, playSpeed, 0, 0, 3, GloVariables.PLAYER_WIDTH, GloVariables.PLAYER_HEIGHT, scale);
        moveLeft = new Sprite(e, 30, playSpeed, 30, 0, 3, GloVariables.PLAYER_WIDTH, GloVariables.PLAYER_HEIGHT, scale);
        moveUp = new Sprite(e, 30, playSpeed, 60, 0, 3, GloVariables.PLAYER_WIDTH , GloVariables.PLAYER_HEIGHT, scale);
        moveRight = new Sprite(e, 30, playSpeed, 90, 0, 3, GloVariables.PLAYER_WIDTH, GloVariables.PLAYER_HEIGHT, scale);
        idle = new Sprite(e, 30, playSpeed, 118, 0, 1, GloVariables.PLAYER_WIDTH+2, GloVariables.PLAYER_HEIGHT, scale);

        List<Rectangle> Die = new ArrayList<>();
        Die.add(new Rectangle(149, 0, 20, 21));
        Die.add(new Rectangle(179, 1, 19, 20));
        Die.add(new Rectangle(118, 30, 21, 21));
        Die.add(new Rectangle(149, 30, 20, 21));
        Die.add(new Rectangle(179, 30, 19, 21));
        Die.add(new Rectangle(118, 60, 21, 21));
        Die.add(new Rectangle(147, 60, 23, 22));
        die = new Sprite(e, 30, 0.12, img, Die, GloVariables.PLAYER_WIDTH + 2, GloVariables.PLAYER_HEIGHT + 2, scale);
    }

    public Sprite getMoveRightSprite() {
        return moveRight;
    }

    public Sprite getMoveLeftSprite() {
        return moveLeft;
    }

    public Sprite getMoveUpSprite() {
        return moveUp;
    }

    public Sprite getMoveDownSprite() {
        return moveDown;
    }

    public Sprite getPlayerIdleSprite() {
        return idle;
    }

    public Sprite getPlayerDying() {
        return die;
    }
}
