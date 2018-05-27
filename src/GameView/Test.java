package GameView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test {
    public Test() {
        Stage stage = new Stage();
        ImageView Background = new ImageView();
        Image back = new Image("resource/startBackground.png");
        Background.setImage(back);
        StackPane root = new StackPane();
        root.getChildren().add(Background);
        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("JavaLand");
        stage.setScene(scene);
        stage.show();
    }
}
