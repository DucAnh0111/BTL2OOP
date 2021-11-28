package BomberGame.Entity;

import BomberGame.GloVariables.Direction;

public abstract class Move extends Entity{
    abstract public void die();
    abstract public void move(int steps, Direction direction);
}
