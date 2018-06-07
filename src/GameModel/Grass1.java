package GameModel;

import javafx.scene.image.Image;

public class Grass1 extends Scenery {
    public Grass1(double x, double y){
        setImage(new Image("resource/草1.png"));
        setPx(x);
        setPy(y);
    }
}
