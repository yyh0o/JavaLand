package GameModel;


import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Set;

public abstract class Base extends Parent {
//    private Image image;//对应的图片
    private javafx.geometry.Point2D position;//图片在地图上绘制的位置

    private double height;
    private double width;

    public abstract void draw(GraphicsContext gc);

    public Base(double initX, double initY){
        position = new Point2D(initX,initY);
    }

    public Base(){
        position = new Point2D(0,0);
    }

    public double getPx() {
        return position.getX();
    }

    public double getPy() {
        return position.getY();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public boolean isCollisionWith(Base base){
        if(getPx() + getWidth() > base.getPx() && getPx() < base.getPx() + base.getWidth() && getPy() + getHeight() > base.getPy() && getPy() < base.getPy() + base.getHeight()){
            return true;
        }
        return false;
    }
}
