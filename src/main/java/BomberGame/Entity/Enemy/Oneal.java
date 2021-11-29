package BomberGame.Entity.Enemy;

import BomberGame.Animations.OnealAnimations;
import BomberGame.Entity.Move;
import BomberGame.GloVariables.Direction;
import BomberGame.Render;
import BomberGame.Sence.board;

import java.util.Date;

public class Oneal extends Balloon {
    OnealAnimations onealAnimations;

    public Oneal(int x, int y) {
        super(x, y);
        onealAnimations = new OnealAnimations(this, scale);
        sprite = onealAnimations.getIdle();
        step = 4;
    }

    public void render() {
        if (sprite != null && isAlive()) {
            Render.playAnimation(sprite);
        }
        if (!isAlive()) {
            Render.playAnimation(onealAnimations.getDie());
            if (new Date().getTime() > (600 + dieTime.getTime())) {
                disappear = true;
            }
        }
    }



    public void move(int steps, Direction direction) {
        if (isAlive) {
            if (steps == 0) {
                setCurrentSprite(onealAnimations.getIdle());
            } else {
                switch (direction) {
                    case UP:
                        if (!checkCollisions(positionX, positionY - steps)) {
                            positionY -= steps;
                            setCurrentSprite(onealAnimations.getOneal());
                            currentDirection = Direction.UP;
                        }
                        break;
                    case DOWN:
                        if (!checkCollisions(positionX, positionY + steps)) {
                            positionY += steps;
                            setCurrentSprite(onealAnimations.getOneal());
                            currentDirection = Direction.DOWN;
                        }
                        break;
                    case LEFT:
                        if (!checkCollisions(positionX - steps, positionY)) {
                            positionX -= steps;
                            setCurrentSprite(onealAnimations.getOneal());
                            currentDirection = Direction.LEFT;
                        }
                        break;
                    case RIGHT:
                        if (!checkCollisions(positionX + steps, positionY)) {
                            positionX += steps;
                            setCurrentSprite(onealAnimations.getOneal());
                            currentDirection = Direction.RIGHT;
                        }
                        break;
                    default:
                        setCurrentSprite(onealAnimations.getIdle());
                }
            }
        }
    }
}
