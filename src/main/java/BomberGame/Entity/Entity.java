package BomberGame.Entity;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.GloVariables.GloVariables;

public abstract class Entity {
    public int positionX;
    public int positionY;
    public int layer;
    public int width;
    public int height;
    public double scale;
    public Sprite sprite;
    public BounderBox bounderBox;

    public Entity(int x, int y) {}

    public Entity() {

    }

    abstract public boolean isCollideEntity(Entity b);

    abstract public boolean isCollidePlayer();

    abstract public void render();

    public abstract void die();

    abstract public boolean remove();

    public void setOffset() {
        this.positionX -= GloVariables.offSet;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getLayer() {
        return layer;
    }

    public double getScale() {
        return scale;
    }

    public BounderBox getBoundingBox() {
        return bounderBox;
    }
}
