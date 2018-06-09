package GameModel;

import javafx.scene.canvas.GraphicsContext;

public class Grass1 extends Scenery {
    public Grass1(double x, double y){
        setPx(x);
        setPy(y);
    }

    public void draw(GraphicsContext gc, double fx, double fy){
        gc.drawImage(ImageLibray.getImage("Grass1"),getPx()+fx,getPy()+fy);
    }
}
