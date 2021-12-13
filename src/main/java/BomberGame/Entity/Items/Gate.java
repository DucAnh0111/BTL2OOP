package BomberGame.Entity.Items;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Tiles.Tile;

public class Gate extends Tile {
    public Gate(int x, int y) {
        super(x, y);
        super.setSprite(new Sprite(this,16,0,0,207,1,width,height,getScale()));
    }
    //khi di vao vi tri nay ng choi se dich chuyen den Gate khac
}
