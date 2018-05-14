package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Role extends Biological {
    private Body body;
    private double speed;
    public void move(String toward){
        if (toward.equals("UP"))
            this.setY(this.getY() - speed);
        if (toward.equals("DOWN"))
            this.setY(this.getY() + speed);
        if (toward.equals("LEFT"))
            this.setX(this.getX() - speed);
        if (toward.equals("RIGHT"))
            this.setX(this.getX() + speed);
    }
    public Role(){
        speed = 1;
        setImage(new Image("resource/Role1.png"));
    }

    public void drawRole(GraphicsContext gc){
        gc.drawImage(getImage(),getX(),getY());
    }
}
