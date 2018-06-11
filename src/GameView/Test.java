package GameView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test {
    public Test() {
        Stage stage = new Stage();
        ImageView Background = new ImageView();
        Image back = new Image("resource/kaifazhe.png");
        Background.setImage(back);
        Label start1= new Label();
        start1.setTranslateX(360);
        start1.setTranslateY(-260);
        Image sta = new Image("resource/2.png");
        start1.setGraphic(new ImageView(sta));
        start1.setOnMouseEntered((MouseEvent e)->{
            start1.setScaleX(1.5);
            start1.setScaleY(1.5);
        });
        start1.setOnMouseExited((MouseEvent e)->{
            start1.setScaleX(1);
            start1.setScaleY(1);
        });
        start1.setOnMouseClicked((MouseEvent e)->{
            stage.hide();
        });
        StackPane root = new StackPane();
        root.getChildren().add(Background);
        root.getChildren().add(start1);
        Scene scene = new Scene(root, 782, 564);
        stage.setTitle("JavaLand");
        stage.setScene(scene);
        stage.show();
    }
}
