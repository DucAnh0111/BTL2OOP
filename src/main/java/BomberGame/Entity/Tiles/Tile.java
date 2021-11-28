package BomberGame.Entity.Tiles;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Bounder.BounderBox;
import BomberGame.Entity.Entity;
import BomberGame.Render;

public class Tile extends Entity {
    boolean remove = false;
    BounderBox bounderBox;
    public Tile(int x, int y) {
        positionX = x;
        positionY = y;
        width = 16;
        height = 16;
        scale = 3.1;
        layer = 1;
        bounderBox = new BounderBox(positionX, positionY, (int) (width * (getScale() + 0.9)), (int) (height * (getScale() + 0.9)));
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void checkCollision(boolean remove) {
        this.remove = remove;
    }

    public boolean remove() {
        return remove;
    }

    public boolean isCollideEntity(Entity e) {
        BounderBox rect = e.getBoundingBox();
        return bounderBox.checkCollision(rect);
    }
    public void die(){ }
    public boolean isCollidePlayer() {
        return false;
    }

    @Override
    public void render() {
        Render.playAnimation(sprite);
    }
}
