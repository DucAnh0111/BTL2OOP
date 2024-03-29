package BomberGame.Animations;

import BomberGame.Entity.Entity;
import BomberGame.Render;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BalloonAnimations {
    Sprite balloon;
    Sprite die;
    Sprite idle;
    double playSpeed;
    public BalloonAnimations(Entity e, double scale){
        Image img = Render.getMainSheet();
        playSpeed = 0.18;

        List<Rectangle> Balloon = new ArrayList<>();
        List<Rectangle> Die = new ArrayList<>();

        Balloon.add(new Rectangle(1,123,16,16));
        Balloon.add(new Rectangle(31,123,16,16));
        Balloon.add(new Rectangle(61,123,16,16));
        balloon = new Sprite(e,16,playSpeed,img,Balloon,16,16,scale);
        idle = new Sprite(e,16,playSpeed,1,123,1,16,16,scale);
        Die.add(new Rectangle(91,123,16,16));
        Die.add(new Rectangle(1,153,16,16));
        Die.add(new Rectangle(31,153,16,16));
        Die.add(new Rectangle(61,153,16,16));
        Die.add(new Rectangle(91,153,16,16));
        Die.add(new Rectangle(121,153,16,16));
        Die.add(new Rectangle(151,153,16,16));
        die = new Sprite(e,16,0.12,img,Die,16,16,scale);
    }
    public Sprite getBalloon(){return balloon;}
    public Sprite getDie(){return die;}
    public Sprite getIdle(){return idle;}
}
