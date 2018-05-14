package GameView;

import GameModel.Ground;
import GameModel.Role;
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

        Role testRole = new Role();
        Ground ground = new Ground();
        final long startNanoTime = System.nanoTime();
        new AnimationTimer(){

            @Override
            public void handle(long now) {
                double t = (now - startNanoTime) / 1000000000.0;
                testRole.move("DOWN");
                testRole.move("RIGHT");
                ground.drawGroud(gc,32,32);
                testRole.drawRole(gc);


            }
        }.start();
        primaryStage.show();
    }
}
