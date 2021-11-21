package Entity.Bounder;

import Variables.GloVariable;
import javafx.geometry.Rectangle2D;
import static javafx.geometry.Rectangle2D.EMPTY;
public class Bounder {
    public int x;
    public int y;
    public int width;
    public int height;
    Rectangle2D bounder;

    public Bounder(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        bounder = new Rectangle2D(x, y, width, height);
    }

    public void setOffset() {this.x -= GloVariable.offSet;}

    public Rectangle2D getBounder() {
        return bounder;
    }

    public void removeBounder() {
        this.bounder = EMPTY;
    }

    public void setBounder() {
        this.bounder = new Rectangle2D(this.x, this.y, width, height);
    }

    public boolean checkCollision(Bounder b) {
        return b.getBounder().intersects(getBounder());
    }

    public void setPosition(int x, int y) {
        this.x = x + (int) (GloVariable.PLAYER_WIDTH);
        this.y = y + (int) (GloVariable.PLAYER_HEIGHT);
        bounder = new Rectangle2D(this.x, this.y, width, height);
    }
    public void setEnemyPosition(int x, int y) {
        this.x = x + 18;
        this.y = y + 18;
        bounder = new Rectangle2D(this.x, this.y, 30, 30);
    }
}

