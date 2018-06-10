package GameController;

import GameModel.ImageLibray;
import GameModel.Map;
import GameModel.Role;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainController {
    public void initGC(GraphicsContext gc, Map map){
        gc.translate(-(map.getWidth()-gc.getCanvas().getWidth())/2,-(map.getHeight()-gc.getCanvas().getHeight())/2);
    }
    public void initImageLibrary(){
        File dat = new File("Dat/ImageLibrary.dat");
        try {
            BufferedReader in = new BufferedReader(new FileReader(dat));
            String line;
            line = in.readLine();
            while (line != null){
                String[] value = line.split("_");
                ImageLibray.addImage(value[0],new Image(value[1]));
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMap(Map map, Role player){
        player.setPx(map.getWidth()/2);
        player.setPy(map.getHeight()/2);
        map.setPlayer(player);
    }

    public void updateMap(Map map, GraphicsContext gc){
        map.winMove(map.getWinToward(),gc);
        map.mapMove(map.getMapToward(),gc);
    }

}
