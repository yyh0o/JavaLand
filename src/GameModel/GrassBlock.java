package GameModel;

import javafx.scene.image.Image;

public class GrassBlock extends GroundBlock {
    public GrassBlock(double x, double y){
        super(x,y);
        setImage(new Image("/resource/草地.png"));
    }
}
