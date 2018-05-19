package GameModel;
public class Biological extends Base {
    protected double speed;

    public double getSpeed(){
        return speed;
    }
    Biological(double x, double y){
        super(x,y);
    }
    Biological(){}
}
