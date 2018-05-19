package GameModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Role extends Biological {
    private Body body;

    public void move(String toward){
        if (toward.equals("UP")){
            setPosition(getPosition().add(0,-speed));
        }
        if (toward.equals("DOWN")){
            this.setImage(new Image("resource/Role1.png"));
            setPosition(getPosition().add(0,speed));
        }
        if (toward.equals("LEFT")){
            this.setImage(new Image("resource/Role1Left.png"));
            setPosition(getPosition().add(-speed,0));
        }
        if (toward.equals("RIGHT")){
            this.setImage(new Image("resource/Role1Right.png"));
            setPosition(this.getPosition().add(speed,0));
        }
    }
    public Role(double x, double y){
        super(x,y);
        speed = 5;
        setImage(new Image("resource/Role1.png"));
    }

    public void drawRole(GraphicsContext gc){
        super.drawSelf(gc);
    }
}
