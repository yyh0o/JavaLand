package GameModel;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageLibray{
    private static Map<String,Image> imageLibray = new HashMap();
    public static void addImage(String code, Image image){
        imageLibray.put(code,image);
    }
    public static Image getImage(String k){
        return imageLibray.get(k);
    }

}
