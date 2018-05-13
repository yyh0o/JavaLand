package GameView;

import GameModel.Ground;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestGround extends Application {

    public static void main(String[] args){
        launch(TestGround.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setTitle("test");
        Canvas canvas = new Canvas(1200,800);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        primaryStage.setScene(scene);
        Ground ground = new Ground();
        ground.drawGroud(gc,32,32);

        primaryStage.show();

    }
}
