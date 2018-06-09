package GameModel;
import javafx.scene.canvas.GraphicsContext;

public class Ground {
    final int smallRow = 22;
    final int smallCol = 30;
    final int bigRow = 900;
    final int bigCol = 900;
    private Terrain[] terrains;
    private GroundBlock[][] bigGround;
    private int row;//记录显示部分从哪一行开始
    private int col;//记录显示部分从哪一列开始
    final int updateSize = 2;//每次更新多少行列的小数组

    public int getUpdateSize() {
        return updateSize;

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    //初始化构建一个全是草的地图
    public Ground(){
        bigGround = new GroundBlock[bigCol][bigRow];
        for (int i = 0; i < bigCol; i++){
            for (int j = 0; j < bigRow; j++){
                bigGround[i][j] = new GrassBlock(j*32, i*32);
            }
        }
        WaterBlock drawRiver=new WaterBlock();
        drawRiver.makeRiver(bigGround);

        row = (bigRow - smallRow)/2;
        col = (bigCol - smallCol)/2;
    }


    public void updateSmallGround(String toward){
        if (toward.equals("UP") && row > 2){
            row -= updateSize;
        }
        if (toward.equals("DOWN") && row < bigRow - smallRow -2){
            row += updateSize;
        }
        if (toward.equals("LEFT") && col > 2){
            col -= updateSize;
        }
        if (toward.equals("RIGHT") && col < bigCol -smallCol -2){
            col += updateSize;
        }
    }

    //绘制出当前画布中的部分地图
    public void drawGroud(GraphicsContext gc){
        for (int i = -2; i < smallCol; i++){
            for (int j = -2; j < smallRow; j++){
                bigGround[j+row][i+col].draw(gc,0,0);
            }
        }

    }




}
