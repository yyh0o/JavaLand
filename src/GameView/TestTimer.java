package GameView;

import GameController.GroundController;
import GameController.MainController;
import GameModel.Ground;
import GameModel.Role;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TestTimer extends Application {
    final int WIDTH = 860;
    final int HEIGHT = 640;
    String LastStep="DOWN";//之前那一步的操作时什么
    int StepNum=0;//用来记录该走那一步

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group(); //新建根节点
        Scene scene = new Scene(root);//在根节点新建一个场景
        Canvas canvas = new Canvas(WIDTH,HEIGHT);//新建画布
        root.getChildren().add(canvas);//添加画布到场景
        GraphicsContext gc = canvas.getGraphicsContext2D();//获得画布的graphicContext
        primaryStage.setTitle("TestTimer");//设置title
//        scene.setCursor(new ImageCursor(new Image("/resource/草2.png")));
        primaryStage.setScene(scene);//添加场景到舞台
        MainController.initImageLibary();
        Ground ground = new Ground();//新建地
        gc.translate(-ground.getCol()*32,-ground.getRow()*32);
        Role testRole = new Role(gc.getCanvas().getWidth()/2 - gc.getTransform().getTx(),
                gc.getCanvas().getHeight()/2 - gc.getTransform().getTy());//新建角色



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
        final Point2D[] mousePoint = {new Point2D(0, 0)};//记录坐标
        scene.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Point2D t = new Point2D(event.getScreenX(),event.getScreenY());
                        mousePoint[0] = t;
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
        System.out.println(LastStep);
        //新建时钟
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;
//                testRole.move("DOWN");
//                testRole.move("RIGHT");
                //判断用户输入并且处理 之后会放在controller中

//                if(input.toString().equals(LastStep)){
//                    StepNum=0;
//                }
                if (input.contains("UP") || input.contains("W")) {
                    LastStep="UP";
                    StepNum++;
                    testRole.move("UP", StepNum);
                }
                if (input.contains("DOWN") || input.contains("S")) {
                    LastStep="DOWN";
                    StepNum++;
                    testRole.move("DOWN", StepNum);
                }
                if (input.contains("LEFT") || input.contains("A")){
                    LastStep="LEFT";
                    StepNum++;
                    testRole.move("LEFT",StepNum);
                }
                if (input.contains("RIGHT") || input.contains("D")){
                    LastStep="RIGHT";
                    StepNum++;
//                    gc.translate(-1,0);
//                    System.out.println(gc.getTransform().getTx()); //获取改变后的Tx坐标值
                    testRole.move("RIGHT",StepNum);
                }

//                System.out.println(mousePoint[0]);
                 Text text = new Text(mousePoint[0].getX(),mousePoint[0].getY(),mousePoint[0].toString());
                GroundController.updateGround( ground,gc,testRole);
                clearCanvas(gc,WIDTH,HEIGHT);
                ground.drawGroud(gc); //画地图
                testRole.draw(gc); //画角色

            }
        }.start(); //开始
        primaryStage.sizeToScene(); //设置舞台大小和场景一致
        primaryStage.setResizable(false); //设置窗口不可缩放
        primaryStage.show();
    }

    //清除当前窗口上的内容
    public void clearCanvas(GraphicsContext gc, double canvasWidth, double canvasHeight){
        gc.clearRect(-gc.getTransform().getTx(),-gc.getTransform().getTy(),canvasWidth,canvasHeight);

    }
}
