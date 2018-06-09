package GameModel;

import javafx.scene.canvas.GraphicsContext;

public class GrassBlock extends GroundBlock {
    public void draw(GraphicsContext gc) {
        gc.drawImage(ImageLibray.getImage("GrassBlock"),getPx(),getPy());
    }

    public GrassBlock(double x, double y){
        super(x,y);
    }

    @Override
    public void draw(GraphicsContext gc, double fx, double fy) {
        gc.drawImage(ImageLibray.getImage("GrassBlock"),getPx()+fx,getPy()+fy);
    }

    //    @Override
//    public String toString() {
//        String s = "GrassBlock:"+getPx()+"."+getPx();
//        return s;
//    }
}
