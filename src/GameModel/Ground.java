package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ground {
    private Terrain[] terrains;
    private GroundBlock[][] testGround = new GroundBlock[40][40];

    public Ground(){
        for (int i = 0; i < testGround.length; i++){
            for (int j = 0; j < testGround[0].length; j++){
                testGround[i][j] = new GrassBlock();
            }
        }

    }

    public GroundBlock[][] getTestGround() {
        return testGround;
    }

    public void drawGroud(GraphicsContext gc, double pictWidth, double pictHeight){
        for (int i = 0; i < getTestGround().length; i++) {
            for (int j = 0; j < getTestGround()[0].length; j++) {
                Image image = getTestGround()[i][j].getImage();
                gc.drawImage(image,pictWidth*j,pictHeight*i);
            }
        }
    }
}
