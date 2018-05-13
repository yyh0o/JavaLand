package GameView;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class MainViewer extends Application {
    public static void main(String[] args){
        Application.launch(MainViewer.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btnHello = new Button("Hello");
        btnHello.setLayoutX(10);
        btnHello.setLayoutY(10);

        Pane pane = new Pane(btnHello);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaLand");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
    }
}
