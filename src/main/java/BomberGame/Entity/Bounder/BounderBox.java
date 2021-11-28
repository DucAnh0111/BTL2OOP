package BomberGame.Entity.Bounder;
import BomberGame.GloVariables.GloVariables;
import javafx.geometry.Rectangle2D;
import static javafx.geometry.Rectangle2D.EMPTY;
public class BounderBox {
    public int x;
    public int y;
    int width;
    int height;
    Rectangle2D boundary;
    public BounderBox(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        boundary = new Rectangle2D(x, y, width, height);
    }

    public void setOffset() {
        this.x -= GloVariables.offSet;
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public void removeRect() {
        this.boundary = EMPTY;
    }

    public void setBoundary() {
        this.boundary = new Rectangle2D(this.x, this.y, width, height);
    }

    public boolean checkCollision(BounderBox b) {
        return b.getBoundary().intersects(this.boundary);
    }

    public void setPosition(int x, int y) {
        this.x = x + (int) (GloVariables.PLAYER_WIDTH);
        this.y = y + (int) (GloVariables.PLAYER_HEIGHT);
        boundary = new Rectangle2D(this.x, this.y, width, height);
    }

    public void setEnemyPosition(int x, int y) {
        this.x = x + 18;
        this.y = y + 18;
        boundary = new Rectangle2D(this.x, this.y, 30, 30);
    }
}
