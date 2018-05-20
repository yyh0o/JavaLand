package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GrassBlock extends GroundBlock {

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(ImageLibray.getImage("GrassBlock"),getPx(),getPy());
    }

    public GrassBlock(double x, double y){
        super(x,y);
    }

}
