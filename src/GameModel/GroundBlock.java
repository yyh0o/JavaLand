package GameModel;
public abstract class GroundBlock extends Base {

    public static double Height() {
        return getImage().getHeight();
    }

    public static double Width() {
        return getImage().getWidth();
    }

    public GroundBlock(){}
    public GroundBlock(double x,double y){
        super(x,y);
    }
}
