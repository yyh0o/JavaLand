package GameView;

import GameModel.Ground;
import GameModel.Role;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TestTimer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group(); //新建根节点
        Scene scene = new Scene(root);//在根节点新建一个场景
        Canvas canvas = new Canvas(1200,800);//新建画布
        root.getChildren().add(canvas);//添加画布到场景
        GraphicsContext gc = canvas.getGraphicsContext2D();//获得画布的graphicContext
        primaryStage.setTitle("TestTimer");//设置title
//        scene.setCursor(new ImageCursor(new Image("/resource/草2.png")));
        primaryStage.setScene(scene);//添加场景到舞台

        Role testRole = new Role();//新建角色
        Ground ground = new Ground();//新建地

        ArrayList<String> input = new ArrayList<String>();//新建储存KeyCode的链表

        //添加键盘按键KeyCode到按键列表
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();

                        if (!input.contains(code))
                            input.add(code);
                    }
                }
        );

        //移除键盘按键KeyCode到按键列表
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                    }
                }
        );
        //新建时钟
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;
//                testRole.move("DOWN");
//                testRole.move("RIGHT");

                //判断用户输入并且处理 之后会放在controller中
                if (input.contains("UP") || input.contains("W"))
                    testRole.move("UP");
                if (input.contains("DOWN") || input.contains("S"))
                    testRole.move("DOWN");
                if (input.contains("LEFT") || input.contains("A"))
                    testRole.move("LEFT");
                if (input.contains("RIGHT") || input.contains("D")){
                    gc.translate(-1,0);
                    System.out.println(gc.getTransform().getTx()); //获取改变后的Tx坐标值
                    testRole.move("RIGHT");
                }

                ground.drawGroud(gc,32,32); //画地图
                testRole.drawRole(gc); //画角色

            }
        }.start(); //开始
        primaryStage.sizeToScene(); //设置舞台大小和场景一致
        primaryStage.setResizable(false); //设置窗口不可缩放
        primaryStage.show();
    }
}
