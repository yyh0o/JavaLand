package GameController;

import GameModel.Biological;
import GameModel.Ground;
import javafx.scene.canvas.GraphicsContext;

public class GroundController {

    static double fixX = 0;
    static double fixY = 0;
    public static void updateGround(Ground ground, GraphicsContext gc, Biological role){
        double ch = gc.getCanvas().getHeight();
        double cw = gc.getCanvas().getWidth();
        double rx = role.getPx() + gc.getTransform().getTx();
        double ry = role.getPy() + gc.getTransform().getTy();

        if (ry < (1.0/8)*ch){
            gc.translate(0,role.getSpeed());
            fixY += role.getSpeed();
        }
        if (ry > (7.0/8)*ch){
            gc.translate(0,-role.getSpeed());
            fixY -= role.getSpeed();
        }
        if (rx < (1.0/8)*cw){
            gc.translate(role.getSpeed(),0);
            fixX += role.getSpeed();
        }
        if (rx > (7.0/8)*cw){
            gc.translate(-role.getSpeed(),0);
            fixX -=role.getSpeed();
        }

        if (fixX > ground.getUpdateSize()*32){
            ground.updateSmallGround("LEFT");
            fixX -= ground.getUpdateSize()*32;
        }
        if (fixX < -ground.getUpdateSize()*32){
            ground.updateSmallGround("RIGHT");
            fixX += ground.getUpdateSize()*32;
        }
        if (fixY > ground.getUpdateSize()*32){
            ground.updateSmallGround("UP");
            fixY -= ground.getUpdateSize()*32;
        }
        if (fixY < -ground.getUpdateSize()*32){
            ground.updateSmallGround("DOWN");
            fixY += ground.getUpdateSize()*32;
        }

    }
}
