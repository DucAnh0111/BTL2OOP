package BomberGame.Entity.PowerUp;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Tiles.Tile;

public class FlameUp extends Tile {
    public FlameUp(int x, int y){
        super(x,y);
        super.setSprite(new Sprite(this,16,0,143,225,1,width,height,getScale()));
    }
}
