package GameModel;

import javafx.scene.canvas.GraphicsContext;

public class Grass2 extends Scenery{
    public Grass2(double x, double y){
        setPx(x);
        setPy(y);
    }

    public double getWidth(){
        return 20;
    }

    public void draw(GraphicsContext gc,double fx, double fy){
        gc.drawImage(ImageLibray.getImage("Grass2"),getPx()+fx,getPy()+fy);
    }
}
