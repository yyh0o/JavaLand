package GameModel;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Base {
    private Image image;//对应的图片
//    private double px;//图片截取点的坐标
//    private double py;
//    private double width;//图片截取的长度和宽度
//    private double height;
//    private double x;
//    private double y;
    private javafx.geometry.Point2D position;//图片在地图上绘制的位置
    private boolean isVisible;//是否可视

    protected void setImage(Image image) {
        this.image = image;
    }

    protected Image getImage() {
        return image;
    }

    public Base(/*double initPx, double initPy, double initWidth, double initHeight,*/ double initX, double initY, boolean initIsVisible){
//        px = initPx;
//        py = initPy;
//        width = initWidth;
//        height = initHeight;
        position = new Point2D(initX,initY);
//        x = initX;
//        y = initY;
        isVisible = initIsVisible;
    }
    Base(){
//        px = 0;
//        py = 0;
//        width = 0;
//        height = 0;
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

//    public void setHeight(double height) {
//        this.height = height;
//    }

//    public void setPx(double px) {
//        this.px = px;
//    }
//
//    public void setPy(double py) {
//        this.py = py;
//    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

//    public void setWidth(double width) {
//        this.width = width;
//    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
