package GameModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Role extends Biological {
    private Body body;
    private String Rtoward;
    private Image image;
    public Image[] frames;
    public double duration=0.1;

    public void move(String toward,Image[] images){
        Rtoward = toward;
        frames=images;
        if (toward.equals("UP")){
            setPy(getPy()-speed);
        }
        if (toward.equals("DOWN")){
            setPy(getPy()+speed);
        }
        if (toward.equals("LEFT")){
            setPx(getPx()-speed);
        }
        if (toward.equals("RIGHT")){
            setPx(getPx()+speed);
        }
    }
    public Role(double x, double y,Image[] images){
        super(x,y);
        speed = 3;
        Rtoward = "DOWN";
        frames=images;
    }

    public Role(double x, double y){
        super(x,y);
        speed = 3;
        Rtoward = "DOWN";
        Image[] imageArrayM=new Image[4];
        for(int i=1;i<=4;i++){
            imageArrayM[i-1]=new Image("resource/Role1DOWN"+i+".png");
        }
        frames=imageArrayM;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,getPx(),getPy());
    }

    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }



}
