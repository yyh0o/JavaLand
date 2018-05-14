package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Role extends Biological {
    private Body body;
    private double speed;
    public void move(String toward){
        if (toward.equals("UP")){
//            this.setImage(new Image(""));
            this.setY(this.getY() - speed);
        }
        if (toward.equals("DOWN")){
            this.setImage(new Image("resource/Role1.png"));
            this.setY(this.getY() + speed);
        }
        if (toward.equals("LEFT")){
            this.setImage(new Image("resource/Role1Left.png"));
            this.setX(this.getX() - speed);
        }
        if (toward.equals("RIGHT")){
            this.setImage(new Image("resource/Role1Right.png"));
            this.setX(this.getX() + speed);
        }
    }
    public Role(){
        speed = 3;
        setImage(new Image("resource/Role1.png"));
    }

    public void drawRole(GraphicsContext gc){
        gc.drawImage(getImage(),getX(),getY());
    }
}
