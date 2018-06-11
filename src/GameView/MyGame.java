package GameView;

import GameController.KeyboardManager;
import GameController.MainController;
import GameModel.Map;
import GameModel.River;
import GameModel.Role;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MyGame extends Application {
    final int WIDTH = 860;
    final int HEIGHT = 640;
    final long startNanoTime = System.nanoTime();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group(); //新建根节点
        Scene scene = new Scene(root);//在根节点新建一个场景
        Canvas canvas = new Canvas(WIDTH,HEIGHT);//新建画布
        root.getChildren().add(canvas);//添加画布到场景
        GraphicsContext gc = canvas.getGraphicsContext2D();//获得画布的graphicContext
        primaryStage.setTitle("Java Land");//设置title
        primaryStage.setScene(scene);//添加场景到舞台
        MainController mc = new MainController();//创建主控制器
        mc.initImageLibrary();//初始化ImageLibrary
        Role player = new Role();//创建角色
        Map map = new Map();//创建地图
        mc.initMap(map,player);//初始化地图
        mc.initGC(gc,map);//初始化graphicContext
        ArrayList<String> input = new ArrayList<String>();//新建储存KeyCode的链表
        KeyboardManager km = new KeyboardManager(scene, input);//新建键盘监听器



        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;
                player.move(km.getInput());
                map.drawMap(gc,t);
                mc.updateMap(map,gc);
//                map.mapMove("DOWN",gc);

//                mc.updateMap(map,gc);
//                map.move("UP");
//                map.save();
            }
        }.start();


        primaryStage.sizeToScene(); //设置舞台大小和场景一致
        primaryStage.setResizable(false); //设置窗口不可缩放
        primaryStage.show();

    }
}
