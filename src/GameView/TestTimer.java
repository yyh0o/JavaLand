package GameView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestTimer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(1200,800);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("TestTimer");
        primaryStage.setScene(scene);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;



            }
        }.start();
        primaryStage.show();
    }
}
