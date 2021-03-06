package GameView;

import GameController.GroundController;
import GameController.MainController;
import GameModel.Ground;
import GameModel.MapBlock;
import GameModel.Role;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
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
        MainController mainController = new MainController(null,null,null,null);
        mainController.initImageLibrary();//初始化



//测试mapBlock
        Long seed = Long.valueOf(1);
        String nSeed = seed + "" + 2 + 1;
        seed = Long.valueOf(nSeed);
        mainController.initImageLibrary();
        MapBlock mpb = new MapBlock(seed,0,0);
        File f = new File("Dat/MapDat/"+mpb.getPx()+""+mpb.getPy()+".map");
        f.createNewFile();
        mpb.save(f);
//        MapBlock mpb1 = new MapBlock(f);



        Image[] imageArrayM=new Image[4];
        for(int i=1;i<=4;i++){
            imageArrayM[i-1]=new Image("resource/Role1DOWN"+i+".png");
        }
        Ground ground = new Ground();//新建地
        gc.translate(-ground.getCol()*32,-ground.getRow()*32);
        Role testRole = new Role(gc.getCanvas().getWidth()/2 - gc.getTransform().getTx(),
                gc.getCanvas().getHeight()/2 - gc.getTransform().getTy());//新建角色



        ArrayList<String> input = new ArrayList<String>();//新建储存KeyCode的链表

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
//        System.out.println(LastStep);
        //新建时钟
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;
//                testRole.move("DOWN");
//                testRole.move("RIGHT");
                //判断用户输入并且处理 之后会放在controller中
                /*if (input.contains("UP") || input.contains("W")) {
//                    LastStep="UP";
//                    for(int i=1;i<=4;i++){
//                        imageArrayM[i-1]=new Image("resource/Role1UP"+i+".png");
//                    }
//                    testRole.move("UP",imageArrayM);
                    testRole.move("UP");


                }
                else if (input.contains("DOWN") || input.contains("S")) {
//                    LastStep="DOWN";
//                    for(int i=1;i<=4;i++){
//                        imageArrayM[i-1]=new Image("resource/Role1DOWN"+i+".png");
//                    }
//                    testRole.move("DOWN",imageArrayM);
                    testRole.move("DOWN");

                }
                else if (input.contains("LEFT") || input.contains("A")){
//                    LastStep="LEFT";
//                    for(int i=1;i<=4;i++){
//                        imageArrayM[i-1]=new Image("resource/Role1LEFT"+i+".png");
//                    }
//                    testRole.move("LEFT",imageArrayM);
                    testRole.move("LEFT");


                }
                else if (input.contains("RIGHT") || input.contains("D")){
//                    LastStep="RIGHT";
//                    for(int i=1;i<=4;i++){
//                        imageArrayM[i-1]=new Image("resource/Role1RIGHT"+i+".png");
//                    }
//                    testRole.move("RIGHT",imageArrayM);
                    testRole.move("RIGHT");

                }
                else{
                    for(int i=1;i<=4;i++){
                        imageArrayM[i-1]=new Image("resource/Role1"+LastStep+1+".png");
                    }
                    testRole.frames=imageArrayM;
                }*/

                testRole.move(input);


//                System.out.println(mousePoint[0]);
                Text text = new Text(mousePoint[0].getX(),mousePoint[0].getY(),mousePoint[0].toString());
                GroundController.updateGround( ground,gc,testRole);
                clearCanvas(gc,WIDTH,HEIGHT);
                ground.drawGroud(gc); //画地图
                gc.drawImage(testRole.getFrame(t),testRole.getPx(),testRole.getPy()); //画角色

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
