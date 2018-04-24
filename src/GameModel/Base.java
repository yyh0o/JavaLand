package GameModel;

public class Base {
    private double px;//图片截取点的坐标
    private double py;
    private double width;//图片截取的长度和宽度
    private double height;
    private double x;//图片在地图上绘制的位置
    private double y;
    private boolean isVisible;//是否可视

    public Base(double initPx, double initPy, double initWidth, double initHeight, double initX, double initY, boolean initIsVisible){
        px = initPx;
        py = initPy;
        width = initWidth;
        height = initHeight;
        x = initX;
        y = initY;
        isVisible = initIsVisible;
    }
    Base(){
        px = 0;
        py = 0;
        width = 0;
        height = 0;
        x = 0;
        y = 0;
        isVisible = false;
    }

    public double getHeight() {
        return height;
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getIsVisible(){
        return isVisible;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
