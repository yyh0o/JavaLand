package GameModel;
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
        s += this.getClass()+ ":" + getPx()+"."+getPx();
        return s;
    }
}
