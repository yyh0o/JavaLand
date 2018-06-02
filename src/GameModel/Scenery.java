package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Scenery extends Item {
    public String toString(){
        String s = "";
        s += this.getClass() + "," + getPx() + "," + getPy();
        return s;
    }
}
