package GameModel;

import javafx.scene.image.Image;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ImageLibray{
    private static Map<String,Image> imageLibrary = new HashMap();
//    static {
//        File dat = new File("ImageLibrary.dat");
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(dat));
//            String line = in.readLine();
//            while (line != null){
//                String[] value = line.split("_");
//                imageLibrary.put(value[0],new Image(value[1]));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        imageLibrary.put("GrassBlock",new Image("resource/草地.png"));
////        imageLibrary.put("WaterBlock",new Image("resource/水.png"));
//    }
    public static void addImage(String code, Image image){
        imageLibrary.put(code,image);
}
    public static Image getImage(String k){
        return imageLibrary.get(k);
    }

}
