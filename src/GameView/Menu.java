package GameView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        //Image star = new Image("resource/start.png");
        //开始按钮
        Button button1 = new Button("start");
        button1.getStyleClass().add("button1;s_t_a_r_t");
        button1.setStyle("-fx-font: 30 arial;");
        //button1.setGraphic(new ImageView(star));
        button1.setPrefSize(140, 46);
        button1.setTranslateX(10);
        button1.setTranslateY(110);
        button1.setOnAction(event->{
//            new Test();
            try {
                TestTimer testTimer = new TestTimer();
                testTimer.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.hide();
        });

        //结束按钮
        Button button2 = new Button();
        button2.setPrefSize(20, 20);
        button2.setTranslateX(130);
        button2.setTranslateY(-130);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });

        // 在屏幕上显示图像
        StackPane root = new StackPane();
        root.getChildren().add(Background);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("JavaLand");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}