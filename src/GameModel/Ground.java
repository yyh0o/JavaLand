package GameModel;
import javafx.scene.canvas.GraphicsContext;

public class Ground {
    private Terrain[] terrains;
    private GroundBlock[][] testGround = new GroundBlock[20][30]; //储存当前要绘制的部分地图


    //初始化构建一个全是草的地图
    public Ground(){
        for (int i = 0; i < testGround.length; i++){
            for (int j = 0; j < testGround[0].length; j++){
                testGround[i][j] = new GrassBlock();
            }
        }

    }



    //绘制出当前testGround中储存的部分地图
    public void drawGroud(GraphicsContext gc, double x, double y){
        double width = testGround[0][0].getWidth();
        double height = testGround[0][0].getHeight();
        for (int i = 0; i < testGround.length; i++){
            for (int j = 0; j < testGround[0].length; j++){
                gc.drawImage(testGround[i][j].getImage(),x + width*j,y + height*i);
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
