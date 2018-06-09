package GameModel;

import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Base {
    public void updateItem(){}

    public abstract void draw(GraphicsContext gc, double fx, double fy);
}
