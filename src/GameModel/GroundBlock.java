package GameModel;

import javafx.scene.canvas.GraphicsContext;

public abstract class GroundBlock extends Base {

    public static double Height() {
        return 32;
    }

    public static double Width() {
        return 32;
    }

    public GroundBlock(){}
    public GroundBlock(double x,double y){
        super(x,y);
    }

    @Override
    public String toString() {
        String s =  "";
        String c = this.getClass().toString().substring(16,26);
        s += c + ":" + getPx()+","+getPy();
        return s;
    }

    public abstract void draw(GraphicsContext gc, double fx, double fy);
}
