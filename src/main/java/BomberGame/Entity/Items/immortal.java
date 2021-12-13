package BomberGame.Entity.Items;

import BomberGame.Animations.Sprite;
import BomberGame.Entity.Tiles.Tile;

public class immortal extends Tile {
    public immortal(int x, int y) {
        super(x, y);
        super.setSprite(new Sprite(this,16,0,197,225,1,width, height, getScale()));
    }
    //khi ăn item này sẽ giúp cho người chơi bất tử
}
