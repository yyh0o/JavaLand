package GameView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.concurrent.Callable;


public class Menu extends Application {

    //private static void handle(javafx.event.ActionEvent e) {
    //}

    @Override
    public void start(Stage primaryStage) throws Exception {
        //背景设置
        ImageView Background = new ImageView();
        Image back = new Image("resource/startBackground.png");
        Background.setImage(back);
        //退出按钮
        Label start1= new Label();
        start1.setTranslateX(130);
        start1.setTranslateY(-130);
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
            System.exit(0);
        });
        //帮助按钮
        Label start2 =new Label();
        start2.setTranslateX(100);
        start2.setTranslateY(-130);
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
        //开始游戏按钮
        Label start3 = new Label();
        start3.setTranslateX(10);
        start3.setTranslateY(110);
        start3.setGraphic(new ImageView(new Image("resource/start.png")));
        start3.setOnMouseEntered((MouseEvent e)->{
            start3.setScaleX(1.3);
            start3.setScaleY(1.3);
        });
        start3.setOnMouseExited((MouseEvent e)-> {
                    start3.setScaleX(1);
                    start3.setScaleY(1);
        });
        start3.setOnMouseClicked((MouseEvent e)->{
            try {
                TestTimer testTimer = new TestTimer();
                testTimer.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            primaryStage.hide();
        });
        // 在屏幕上显示图像
        StackPane root = new StackPane();
        root.getChildren().add(Background);
        root.getChildren().add(start2);
        root.getChildren().add(start1);
        root.getChildren().add(start3);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("JavaLand");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}