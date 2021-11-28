package BomberGame.Entity.PowerUp;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Tiles.Tile;

public class BombUp extends Tile {
    public BombUp(int x, int y){
        super(x,y);
        sprite = new Sprite(this,16,0,161,225,1,width,height,getScale());
    }
}
