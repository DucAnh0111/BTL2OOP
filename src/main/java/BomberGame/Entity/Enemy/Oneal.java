package BomberGame.Entity.Enemy;

import BomberGame.Animations.OnealAnimations;
import BomberGame.Entity.Tiles.Brick;
import BomberGame.Entity.Tiles.Tile;
import BomberGame.Entity.Tiles.Wall;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import BomberGame.Sence.board;
import java.util.Date;

public class Oneal extends Balloon {
    OnealAnimations onealAnimations;
    int keepMoving;

    public Oneal(int x, int y) {
        super(x, y);
        onealAnimations = new OnealAnimations(this, scale);
        sprite = OnealAnimations.getOneal();
        step = 4;
        keepMoving = 0;
    }

    public void render() {
        if (sprite != null && isAlive()) {
            Render.playAnimation(sprite);
            move(1);
        }
        if (!isAlive()) {
            Render.playAnimation(onealAnimations.getDie());
            if (new Date().getTime() > (600 + dieTime.getTime())) {
                disappear = true;
                GloVariables.point += 6;
            }
        }
    }



    public void move(int steps) {
        if (isAlive) {
            if (steps == 0) {
                setCurrentSprite(onealAnimations.getIdle());
            } else {
                RandomMoving(step);
            }
        }
    }

    public boolean checkCoordinates(int x, int y) {
        for(Tile e : board.getTiles()) {
            if(e instanceof Brick || e instanceof Wall) {
                if(x == e.positionX && y == e.positionY) {
                    return false;
                }
            }
        }
        return true;
    }

    public int roadMove(int x, int y) {
        if(checkCoordinates(x, y - 48) && keepMoving == 0) {
            return 0;
        }
        if(checkCoordinates(x , y + 48) && keepMoving == 1) {
            return 1;
        }
        if(checkCoordinates(x + 48, y)&& keepMoving == 2) {
            return 2;
        }
        if(checkCoordinates(x - 48, y )&& keepMoving == 3) {
            return 3;
        }
        return 4;
    }

    public void RandomMoving(int step) {
        dir = roadMove(positionX,positionY);
        switch (dir) {
            case 0:
                if(keepMoving == 0) {
                    positionY -= step/2;
                }
                break;
            case 1:
                if(keepMoving == 1) {
                    positionY+= step/2;
                }
                break;
            case 2:
                if(keepMoving == 2) {
                    positionX += step/2;
                }
                break;
            case 3:
                if(keepMoving == 3) {
                    positionX -= step/2;
                }
                break;
            case 4:
                if(keepMoving < 4) {
                    keepMoving++;
                }
                else {
                    keepMoving = 0;
                }
        }
    }
}
