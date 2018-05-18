package GameModel;
import javafx.scene.canvas.GraphicsContext;

public class Ground {
    private Terrain[] terrains;
    private GroundBlock[][] bigGround = new GroundBlock[400][600];
    private GroundBlock[][] smallGround = new GroundBlock[20][30]; //储存当前要绘制的部分地图
    private int row;
    private int col;
    final int updateSize = 4;
    public static double fixPx = 0;
    public static double fixPy = 0;
    //初始化构建一个全是草的地图
    public Ground(){
        for (int i = 0; i < smallGround.length; i++){
            for (int j = 0; j < smallGround[0].length; j++){
                smallGround[i][j] = new GrassBlock();
            }
        }
        row = 190;
        col = 270;
    }

    public void updateSmallGround(GroundBlock[][] smallGround, GroundBlock[][] bigGround, String toward){
        if (toward.equals("UP")){
            row -= updateSize;
            fixPy -= updateSize*smallGround[0][0].getWidth();
        }
        if (toward.equals("DOWN")){
            row += updateSize;
            fixPy += updateSize*smallGround[0][0].getWidth();
        }
        if (toward.equals("LEFT")){
            col -= updateSize;
            fixPx -= updateSize*smallGround[0][0].getHeight();
        }
        if (toward.equals("RIGHT")){
            col += updateSize;
            fixPx -= updateSize*smallGround[0][0].getHeight();
        }

        int height = smallGround.length;
        int width = smallGround[0].length;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                smallGround[i][j] = bigGround[i + row][j + col];
            }
        }
    }

    //绘制出当前testGround中储存的部分地图
    public void drawGroud(GraphicsContext gc, double x, double y){
        double width = smallGround[0][0].getWidth();
        double height = smallGround[0][0].getHeight();
        for (int i = 0; i < smallGround.length; i++){
            for (int j = 0; j < smallGround[0].length; j++){
                gc.drawImage(smallGround[i][j].getImage(),x + width*j,y + height*i);
            }
        }

//    private GroundBlock[][] getTestGround() {
//        return testGround;
//    }

        //    public void drawGroud(GraphicsContext gc, double pictWidth, double pictHeight){
//        for (int i = 0; i < getTestGround().length; i++) {
//            for (int j = 0; j < getTestGround()[0].length; j++) {
//                Image image = getTestGround()[i][j].getImage();
//                gc.drawImage(image,pictWidth*j,pictHeight*i);
//            }
//        }
//    }
    }



}
