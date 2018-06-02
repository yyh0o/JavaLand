package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Base {
    private static Image image;//对象图片
    private double px;//相对x坐标
    private double py;//相对y坐标

//    private double height = image.getHeight();//对象高度
//    private double width = image.getWidth();//对象宽度

    public Base(){
        px = 0;
        py = 0;
    }

    //
    public static Image getImage(){
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,getPx(),getPy());
    };
    public void draw(GraphicsContext gc, double fx, double fy){
        gc.drawImage(image,getPx()+fx,getPy()+fy);
    }

    public Base(double initX, double initY){
        px = initX;
        py = initY;
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public boolean isCollisionWith(Base base){
        if(getPx() + image.getWidth() > base.getPx() && getPx() < base.getPx() + image.getWidth() && getPy() + image.getHeight() > base.getPy() && getPy() < base.getPy() + image.getHeight()){
            return true;
        }
        return false;
    }
}
