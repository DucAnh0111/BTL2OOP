package BomberGame.Animations;

import BomberGame.Entity.Entity;
import BomberGame.Render;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BrickAnimations {
    Sprite BrickAnimation;
    double playspeed;
    public Sprite getBrickAnimation(){
        return BrickAnimation;
    }
    public void setBrickAnimation(Sprite sprite){this.BrickAnimation = sprite;}
    public BrickAnimations(Entity e){
        Image img = Render.getMainSheet();
        playspeed = 0.15;

        List<Rectangle> destroyed = new ArrayList<>();

        destroyed.add(new Rectangle(35,225,15,15));
        destroyed.add(new Rectangle(53,225,15,15));
        destroyed.add(new Rectangle(71,225,15,15));
        destroyed.add(new Rectangle(89,225,15,15));
        destroyed.add(new Rectangle(107,225,15,15));
        destroyed.add(new Rectangle(125,225,15,15));

        BrickAnimation = new Sprite(e,16,playspeed,img,destroyed,16,16,e.getScale());
    }
}
