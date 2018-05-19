package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GrassBlock extends GroundBlock {

    Image image = new Image("resource/草地.png");

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,getPx(),getPy());
    }

    public GrassBlock(double x, double y){
        super(x,y);
    }

}
