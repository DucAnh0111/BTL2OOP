package BomberGame.Entity.BombAndFlame;

import BomberGame.Animations.BombAnimations;
import BomberGame.Animations.FlameAnimations;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.Entity.Entity;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import BomberGame.Sence.board;

import java.util.Date;

public class Bomb extends Entity {
    public static int radius = 1;
    public boolean CollidedPlayer = false;
    public boolean PlayerCollisionFriendly = true;
    int timerDurationInMillis = 2000;

    boolean exploded = false;
    boolean explodedbyFlame = false;

    Date plantedTime;
    Date explosionTime;
    STATE bombState;
    FlameAnimations[] Explosion;
    BombAnimations bomb_animations;

    enum STATE {
        INACTIVE,
        ACTIVE,
        EXPLODED;
    }

    public Bomb(int x, int y) {
        positionX = x;
        positionY = y;
        width = 16;
        height = 16;
        layer = 1;
        scale = 1.8;
        bomb_animations = new BombAnimations(this);
        sprite = bomb_animations.getBlackBomb();
        bounderBox = new BounderBox(positionX, positionY, (int) (width * (getScale() + 1.26)), (int) (height * (getScale() + 1.26)));
        plantedTime = new Date();
        bombState = STATE.ACTIVE;
        Explosion = new FlameAnimations[5];
        for (int i = 0; i < 5; i++) {
            Explosion[i] = new FlameAnimations(x, y, i, radius);
            Explosion[i].addFlameToGame();
        }
    }

    public boolean remove() {
        STATE s = checkBombState();
        if (s == STATE.INACTIVE) {
            return true;
        }
        return false;
    }

    public STATE checkBombState() {
        long plantedtime = plantedTime.getTime();
        if (!exploded) {
            for (Entity e : board.getEntities()) {
                if (e instanceof Flame && ((Flame) e).getFlameState()) {
                    if (e.isCollideEntity(this)) {
                        explodedbyFlame = true;
                        explosionTime = new Date();
                        GloVariables.BrickTiming = 2000 - (int) (explosionTime.getTime() - plantedtime);
                        for (int i = 0; i < Explosion.length; ++i) {
                            Explosion[i].setTimeDuration(2000 - (int) (explosionTime.getTime() - plantedtime));
                        }
                        break;
                    }
                }
            }
        }
        if (explodedbyFlame) {
            if (!exploded) {
                exploded = true;
                sprite = bomb_animations.getGrass();
                board.removeBrick(sprite.getXPosition(),sprite.getYPosition());
            }
            if (exploded) {
                return STATE.INACTIVE;
            }
        }
        if (!explodedbyFlame) {
            if (!exploded && (new Date().getTime() > timerDurationInMillis + plantedtime)) {
                explosionTime = new Date();
                exploded = true;
                sprite = bomb_animations.getGrass();
                return STATE.EXPLODED;
            }
            if (exploded && new Date().getTime() > 350 + explosionTime.getTime()) {
                return STATE.INACTIVE;
            }
        }
        return STATE.ACTIVE;
    }


    @Override
    public void render() {
        if (checkBombState() == STATE.ACTIVE) {
            Render.playAnimation(sprite);
        }
        if (exploded) {
            Render();
        }

    }

    @Override
    public void die() {

    }

    public void Render() {
        for (int i = 0; i < Explosion.length; i++) {
            Explosion[i].Render();
        }
    }

    @Override
    public boolean isCollideEntity(Entity e) {
        BounderBox rect = e.getBoundingBox();
        return bounderBox.checkCollision(rect);
    }

    @Override
    public boolean isCollidePlayer() {
        return this.PlayerCollisionFriendly;
    }

}
