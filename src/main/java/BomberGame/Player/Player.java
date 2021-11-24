package BomberGame.Player;

import BomberGame.Animations.PlayerAnimations;
import BomberGame.Animations.Sprite;
import BomberGame.Entity.BombAndFlame.Flame;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.Entity.Entity;
import BomberGame.Entity.Move;
import BomberGame.GloVariables.Direction;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Render;
import BomberGame.Sence.board;


import java.util.Date;

public class Player extends Move {
    public static int step = 4;
    public static int bombCount = 1;
    boolean isAlive = true;
    boolean disappear = false;

    Date dieTime;
    Direction currentDirection;
    PlayerAnimations playerAnimations;

    public Player(int posX, int posY) {
        init(posX, posY);
        layer = 0;
    }

    private void init(int x, int y) {
        playerAnimations = new PlayerAnimations(this, 2.2);
        positionX = x;
        positionY = y;
        scale = 2;
        bounderBox = new BounderBox(positionX + (int) (GloVariables.PLAYER_WIDTH),
                positionY + (int) (GloVariables.PLAYER_WIDTH),
                (int) (GloVariables.PLAYER_WIDTH * (getScale() - 0.6)),
                (int) (GloVariables.PLAYER_HEIGHT * (getScale() - 0.8))
        );
        sprite = playerAnimations.getPlayerIdleSprite();
    }


    private void setSprite(Sprite s) {
        if (s != null) {
            sprite = s;
        }
    }

    @Override
    public void setOffset() {
        this.positionX -= GloVariables.offSet;
        this.bounderBox.setOffset();
    }

    @Override
    public boolean isCollideEntity(Entity b) {
        BounderBox otherEntityBoundary = b.getBoundingBox();
        return bounderBox.checkCollision(otherEntityBoundary);
    }

    public void render() {
        if (sprite != null && isAlive) {
            Render.playAnimation(sprite);
        }
        if (!isAlive) {
            Render.playAnimation(playerAnimations.getPlayerDying());
            if (new Date().getTime() > (570 + dieTime.getTime())) {
                disappear = true;
                GloVariables.NewGame = true;
            }
        }
    }

    public void die() {
        isAlive = false;
        dieTime = new Date();
    }

    private boolean checkCollisions(int x, int y) {
        return true;
//        bounderBox.setPosition(x, y);
//        for (Entity e : board.getEntities()) {
//            if (e instanceof Portal && isCollideEntity(e) && Board.enemy == 0) {
//                Level = (Level % 4) + 1;
//                GlobalVariables.NewGame = true;
//                passLevel = true;
//            } else {
//                if (e instanceof FlamePowerup && isCollideEntity(e)) {
    //BlackBomb.radius++;
//                    ((FlamePowerup) e).checkCollision(true);
//                }
//                if (e instanceof BombPowerup && isCollideEntity(e)) {
//                    bombCount++;
//                    ((BombPowerup) e).checkCollision(true);
//                }
//                if (e instanceof SpeedPowerup && isCollideEntity(e)) {
//                    step++;
 //                   ((SpeedPowerup) e).checkCollision(true);
//                }
//                if (e instanceof BlackBomb) {
//                    boolean bol1 = Math.abs(this.getPositionY() - e.getPositionY()) < 42;
//                    boolean bol2 = Math.abs(this.getPositionX() - e.getPositionX()) < 42;
//                    if (bol1 && bol2 && ((BlackBomb) e).CollidedPlayer == false && e.isCollidePlayer() == true) {
//                        ((BlackBomb) e).CollidedPlayer = true;
//                    }
 //                   if (!bol1 || !bol2 && ((BlackBomb) e).CollidedPlayer == true) {
//                        ((BlackBomb) e).PlayerCollisionFriendly = false;
//                    }
//                }
//                if (!(e instanceof Balloom) && e != this && isCollideEntity(e) && !e.isCollidePlayer()) {
 //                   bounderBox.setPosition(positionX, positionY);
 //                   return true;
 //               }
 //           }
//        }
//        bounderBox.setPosition(positionX, positionY);
//        return false;
        }

    public boolean remove() {
        if (isAlive) {
            for (Entity e : board.getEntities()) {
                if ((e instanceof Flame && ((Flame) e).getFlameState())) {
                    if (isCollideEntity(e)) {
                        die();
                        break;
                    }
                }
            }
        }
        return disappear;
    }

    public boolean updatePosition() {
        if (getPositionX() - 47 < 0 && currentDirection == Direction.LEFT) {
            GloVariables.offSet = -96;
            return true;
        }
        if (getPositionX() - 864 > 0 && currentDirection == Direction.RIGHT) {
            GloVariables.offSet = 96;
            return true;
        } else {
            GloVariables.CameraMoving = false;
        }
        return false;
    }

    public void move(int steps, Direction direction) {
        if (isAlive) {
            if (steps == 0) {
                setSprite(playerAnimations.getPlayerIdleSprite());
                GloVariables.CameraMoving = false;
                return;
            } else {
                switch (direction) {
                    case UP:
                        if (!checkCollisions(positionX, positionY - steps)) {
                            positionY -= steps;
                            setSprite(playerAnimations.getMoveUpSprite());
                            currentDirection = Direction.UP;
                        }
                        break;
                    case DOWN:
                        if (!checkCollisions(positionX, positionY + steps)) {
                            positionY += steps;
                            setSprite(playerAnimations.getMoveDownSprite());
                            currentDirection = Direction.DOWN;
                        }
                        break;
                    case LEFT:
                        if (!checkCollisions(positionX - steps, positionY)) {
                            positionX -= steps;
                            setSprite(playerAnimations.getMoveLeftSprite());
                            currentDirection = Direction.LEFT;
                        }
                        break;
                    case RIGHT:
                        if (!checkCollisions(positionX + steps, positionY)) {
                            positionX += steps;
                            setSprite(playerAnimations.getMoveRightSprite());
                            currentDirection = Direction.RIGHT;
                        }
                        break;
                    default:
                        setSprite(playerAnimations.getPlayerIdleSprite());
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
        return true;
    }

    public boolean hasMoreBombs() {
        return bombCount > 0;
    }

    public void incrementBombCount() {
        bombCount++;
    }

    public void decrementBombCount() {
        bombCount--;
    }
}
