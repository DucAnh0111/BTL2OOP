package BomberGame.Entity.Enemy;

import BomberGame.Animations.BalloonAnimations;
import BomberGame.Animations.Sprite;
import BomberGame.Entity.BombAndFlame.Bomb;
import BomberGame.Entity.BombAndFlame.Flame;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.Entity.Entity;
import BomberGame.Entity.Move;
import BomberGame.Entity.Tiles.Wall;
import BomberGame.GloVariables.Direction;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import BomberGame.Sence.board;

import java.util.Date;
import java.util.Random;

public class Balloon extends Move {
    int dir = 0;
    boolean isAlive = true;
    boolean disappear = false;
    boolean checkCollision = false;
    public int step;
    Random random = new Random();
    Date dieTime;
    BalloonAnimations balloonAnimations;
    Direction currentDirection;

    public Balloon(int x, int y) {
        init(x, y);
        balloonAnimations = new BalloonAnimations(this, scale);
        sprite = balloonAnimations.getIdle();
        step = 2;

    }

    public void init(int x, int y) {
        layer = 0;
        scale = 2.8;
        positionX = x;
        positionY = y;
        bounderBox = new BounderBox(positionX + 18, positionY + 18, 48, 48);
    }

    public void setCurrentSprite(Sprite s) {
        if (s != null) {
            sprite = s;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setOffset() {
        this.positionX -= GloVariables.offSet;
        this.bounderBox.setOffset();
    }

    public boolean isCollideEntity(Entity b) {
        BounderBox temp = b.getBoundingBox();
        return bounderBox.checkCollision(temp);
    }

    public void render() {
        if (sprite != null && isAlive()) {
            Render.playAnimation(balloonAnimations.getBalloon());
        }
        if (!isAlive()) {
            Render.playAnimation(balloonAnimations.getDie());
            if (new Date().getTime() > (600 + dieTime.getTime())) {
                disappear = true;
            }
        }
    }

    public void die() {
        isAlive = false;
        dieTime = new Date();
        board.enemy--;
    }

    public boolean checkCollisions(int x, int y) {
        bounderBox.setEnemyPosition(x, y);
        for (Entity e : board.getEntities()) {
            if (e != this && isCollideEntity(e) && e instanceof Bomb) {
                bounderBox.setEnemyPosition(positionX, positionY);
                return true;
            }
            if (!(e instanceof Balloon) && isCollideEntity(e) && !e.isCollidePlayer()) {
                checkCollision = true;
                bounderBox.setEnemyPosition(positionX, positionY);
                return true;
            }
            if(e instanceof Wall) {
                return false;
            }

        }
        checkCollision = false;
        bounderBox.setEnemyPosition(positionX, positionY);
        return false;
    }

    public boolean remove() {
        if (isAlive) {
            for (Entity e : board.getEntities()) {
                if (e instanceof Flame && ((Flame) e).getFlameState()) {
                    if (Math.abs(this.positionX - ((Flame) e).getPositionX()) < 40 && Math.abs(this.positionY - ((Flame) e).getPositionY()) < 40) {
                        die();
                        break;
                    }
                }
            }
        }
        return disappear;
    }

    public void move(int steps, Direction direction) {
        if (isAlive) {
            if (steps == 0) {
                setCurrentSprite(balloonAnimations.getIdle());
                return;
            } else {
                switch (direction) {
                    case UP:
                        if (!checkCollisions(positionX, positionY - steps)) {
                            positionY -= steps;
                            setCurrentSprite(balloonAnimations.getBalloon());
                            currentDirection = Direction.UP;
                        }
                        break;
                    case DOWN:
                        if (!checkCollisions(positionX, positionY + steps)) {
                            positionY += steps;
                            setCurrentSprite(balloonAnimations.getBalloon());
                            currentDirection = Direction.DOWN;
                        }
                        break;
                    case LEFT:
                        if (!checkCollisions(positionX - steps, positionY)) {
                            positionX -= steps;
                            setCurrentSprite(balloonAnimations.getBalloon());
                            currentDirection = Direction.LEFT;
                        }
                        break;
                    case RIGHT:
                        if (!checkCollisions(positionX + steps, positionY)) {
                            positionX += steps;
                            setCurrentSprite(balloonAnimations.getBalloon());
                            currentDirection = Direction.RIGHT;
                        }
                        break;
                    default:
                        setCurrentSprite(balloonAnimations.getIdle());
                }
            }
        }
    }

    @Override
    public BounderBox getBoundingBox() {
        bounderBox.setPosition(positionX, positionY);
        return bounderBox;
    }

    public boolean isCollidePlayer() {
        return false;
    }
}
