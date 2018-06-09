package GameModel;

public abstract class Base {
    private double px;//相对x坐标
    private double py;//相对y坐标

//    private double height = image.getHeight();//对象高度
//    private double width = image.getWidth();//对象宽度

    public Base(){
        px = 0;
        py = 0;
    }

    //

//    public abstract void draw(GraphicsContext gc);
//    public abstract void draw(GraphicsContext gc, double fx, double fy);

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

}
