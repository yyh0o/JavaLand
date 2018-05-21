package GameView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;


public class Menu extends Application {

    private static void handle(javafx.event.ActionEvent e) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //背景设置
        ImageView Background = new ImageView();
        Image back = new Image("resource/startBackground.png");
        Background.setImage(back);
        ImageView start1 =new ImageView();
        Image star2 =new Image("resource/start.png");
        start1.setImage(star2);
        start1.setTranslateX(10);
        start1.setTranslateY(110);

        //开始按钮
        Button button = new Button();
        button.setPrefSize(158, 38);
        button.setTranslateX(10);
        button.setTranslateY(110);
        //button.setOnAction();

        // 在屏幕上显示图像
        StackPane root = new StackPane();
        root.getChildren().add(Background);
        root.getChildren().add(button);
        root.getChildren().add(start1);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("SuperGay");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}