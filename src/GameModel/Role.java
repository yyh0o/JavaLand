package GameModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Role extends Biological {
    private Body body;
    private String Rtoward;
    private Image image;
    public void move(String toward){
        Rtoward = toward;
        if (toward.equals("UP")){
            setPosition(getPosition().add(0,-speed));
        }
        if (toward.equals("DOWN")){

            setPosition(getPosition().add(0,speed));
        }
        if (toward.equals("LEFT")){

            setPosition(getPosition().add(-speed,0));
        }
        if (toward.equals("RIGHT")){

            setPosition(this.getPosition().add(speed,0));
        }
        image = new Image("resource/Role1"+Rtoward+".png");
        setWidth(image.getWidth());
        setHeight(image.getHeight());
    }
    public Role(double x, double y){
        super(x,y);
        speed = 5;
        Rtoward = "DOWN";
        image = new Image("resource/Role1"+Rtoward+".png");
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,getPx(),getPy());
    }


}
