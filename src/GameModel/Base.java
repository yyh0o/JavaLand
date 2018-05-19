package GameModel;


import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Base extends Parent {
//    private Image image;//对应的图片
    private javafx.geometry.Point2D position;//图片在地图上绘制的位置

//    protected void setImage(Image image) {
//        this.image = image;
//    }

    public abstract void draw(GraphicsContext gc);

    public Base(double initX, double initY){
        position = new Point2D(initX,initY);
    }

    public Base(){
        position = new Point2D(0,0);
    }

//    public double getHeight() {
//        return image.getHeight();
//    }

    public double getPx() {
        return position.getX();
    }

    public double getPy() {
        return position.getY();
    }

//    public double getWidth() {
//        return image.getWidth();
//    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

//    public void drawSelf(GraphicsContext gc){
//        gc.drawImage(image,position.getX(),position.getY());
//    }
}
