package GameController;

import GameModel.ImageLibray;
import GameModel.Map;
import GameModel.River;
import GameModel.Role;
import GameView.Test;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainController {
    private GraphicsContext gc;
    private Map map;
    private Role player;
    private Group root;
    private ArrayList<String> input;
    private KeyboardManager km;

    public MainController(GraphicsContext initGC, Map initMap, Role initPlayer, Group initRoot){
        gc = initGC;
        map = initMap;
        player = initPlayer;
        root = initRoot;
    }

    public void initGC(){
//        gc.translate(-(map.getWidth()-map.getWinWidth())/2,-(map.getHeight()-map.getWinHeight())/2);
//        System.out.println(map.getWidth()+","+map.getHeight());
//        System.out.println(map.getwX()+","+map.getwY());
//
//        System.out.println((-(map.getWidth()-map.getWinWidth())/2)+","+(-(map.getHeight()-map.getWinHeight())/2));

        gc.translate(-map.getwX(),-map.getwY());

//        gc.translate((map.getWinWidth()/2-map.getwX()),(map.getWinHeight()/2-map.getwY()));
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

    public void initMap(){
//        player.setPx(map.getWidth()/2);
//        player.setPy(map.getHeight()/2);
        map.setPlayer(player);

    }

    public void updateMap(){
        map.winMove(map.getWinToward(),gc);
        map.mapMove(map.getMapToward(),gc);

    }

    public void initLabel(){
        //退出按钮
        Label start1= new Label();
        start1.setTranslateX(830);
        start1.setTranslateY(10);
        Image sta = new Image("resource/4.png");
        start1.setGraphic(new ImageView(sta));
        start1.setOnMouseEntered((MouseEvent e)->{
            start1.setGraphic(new ImageView(new Image("resource/2.png")));
            start1.setScaleX(1.5);
            start1.setScaleY(1.5);
        });
        start1.setOnMouseExited((MouseEvent e)->{
            start1.setGraphic(new ImageView(new Image("resource/4.png")));
            start1.setScaleX(1);
            start1.setScaleY(1);
        });
        start1.setOnMouseClicked((MouseEvent e)->{
            map.save();
            System.exit(0);
        });
        //帮助按钮
        Label start2 =new Label();
        start2.setTranslateX(800);
        start2.setTranslateY(10);
        start2.setGraphic(new ImageView(new Image("resource/3.png")));
        start2.setOnMouseEntered((MouseEvent e)->{
            start2.setGraphic(new ImageView(new Image("resource/1.png")));
            start2.setScaleX(1.5);
            start2.setScaleY(1.5);
        });
        start2.setOnMouseExited((MouseEvent e)->{
            start2.setGraphic(new ImageView(new Image("resource/3.png")));
            start2.setScaleX(1);
            start2.setScaleY(1);
        });
        start2.setOnMouseClicked((MouseEvent e)->{
            new Test();
        });
        root.getChildren().add(start1);
        root.getChildren().add(start2);
    }
}
