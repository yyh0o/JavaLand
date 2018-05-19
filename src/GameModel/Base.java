package GameModel;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Base {
    private Image image;//对应的图片
    private javafx.geometry.Point2D position;//图片在地图上绘制的位置
    private boolean isVisible;//是否可视

    protected void setImage(Image image) {
        this.image = image;
    }

    protected Image getImage() {
        return image;
    }

    public Base(double initX, double initY, boolean initIsVisible){
        position = new Point2D(initX,initY);
        isVisible = initIsVisible;
    }

    public Base(double x, double y){
        position = new Point2D(x,y);
    }

    public Base(){
        position = new Point2D(0,0);
        isVisible = false;
    }

    public double getHeight() {
        return image.getHeight();
    }

    public double getPx() {
        return position.getX();
    }

    public double getPy() {
        return position.getY();
    }

    public double getWidth() {
        return image.getWidth();
    }

    public Point2D getPosition() {
        return position;
    }

    public boolean getIsVisible(){
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void drawSelf(GraphicsContext gc){
        gc.drawImage(image,position.getX(),position.getY());
    }
}
