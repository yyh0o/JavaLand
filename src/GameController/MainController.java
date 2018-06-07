package GameController;

import GameModel.ImageLibray;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainController {
    public static void initImageLibrary(){
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

}
