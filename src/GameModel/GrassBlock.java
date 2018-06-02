package GameModel;

import javafx.scene.canvas.GraphicsContext;

public class GrassBlock extends GroundBlock {
    public void draw(GraphicsContext gc) {
        gc.drawImage(ImageLibray.getImage("GrassBlock"),getPx(),getPy());
    }

    public GrassBlock(double x, double y){
        super(x,y);
    }

}
