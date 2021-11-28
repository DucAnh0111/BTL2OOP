package BomberGame.Entity.Tiles;

import BomberGame.Animations.BrickAnimations;
import BomberGame.Animations.Sprite;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.Entity.Entity;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import BomberGame.Sence.board;

import java.util.Date;

public class Brick extends Tile {
    int items;
    boolean isAlive = true;
    boolean disappear = false;
    boolean check = true;
    boolean Animated = false;

    Date destroyedTime;
    Date animationTime;
    Sprite grass;
    Sprite CurrentSprite;
    BrickAnimations brickAnimations;

    public Brick(int x, int y, int items) {
        super(x, y);
        this.items = items;
        scale = 3.1;
        brickAnimations = new BrickAnimations(this);
        sprite = new Sprite(this, 16, 0, 17, 225, 1, super.width, super.height, getScale());
        grass = new Sprite(this, 16, 0, 0, 245, 1, 16, 16, 2.0);
        CurrentSprite = sprite;
        super.bounderBox = new BounderBox(positionX, positionY, (int) (super.width * (getScale() + 0.9)), (int) (super.height * (getScale() + 0.9)));
    }

    public void setBrickState(boolean temp) {
        GloVariables.BrickTiming = 0;
        if (check && temp) {
            die();
        }
    }

    public boolean getBrickState() {
        return check;
    }

    public boolean BrickState() {
        if (!Animated && !check && new Date().getTime() > (2000 - GloVariables.BrickTiming) + destroyedTime.getTime()) {
            CurrentSprite = grass;
            animationTime = new Date();
            Animated = true;
            isAlive = false;
        }
        if (Animated && new Date().getTime() > 280 + animationTime.getTime()) {
            disappear = true;
        }
        return isAlive;
    }

    public void render() {
        if (BrickState()) {
            Render.playAnimation(CurrentSprite);
        }
        if (!BrickState()) {
            Render.playAnimation(brickAnimations.getBrickAnimation());
        }
    }

    @Override
    public void die() {
        check = false;
        destroyedTime = new Date();
    }

    @Override
    public boolean remove() {
        return false;
    }

    /*
    public boolean remove() {
        if (disappear) {
            switch (items) {
                case 0:
                    board.addEntityToGame(new Portal(positionX, positionY));
                    break;
                case 1:
                    board.addEntityToGame(new FlamePowerup(positionX, positionY));
                    break;
                case 2:
                    board.addEntityToGame(new BombPowerup(positionX, positionY));
                    break;
                case 3:
                    board.addEntityToGame(new SpeedPowerup(positionX, positionY));
                    break;
            }
        }
        return disappear;
    }
     */

    public boolean isCollideEntity(Entity e) {
        BounderBox rect = e.getBoundingBox();
        return super.bounderBox.checkCollision(rect);
    }

    @Override
    public boolean isCollidePlayer() {
        return false;
    }

}
