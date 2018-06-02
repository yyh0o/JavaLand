package GameModel;



import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class MapBlock {
    private ArrayList<Scenery> scenes; //用于储存当前区块中的景物
    private GroundBlock[][] blocks; //用于储存当前区块中的block
    private boolean isActive; //标记当前区块是否活跃
    private final int Height = 80; //区块高度
    private final int Width = 80; //区块宽度
    private double mHeight = blocks.length * Height;//区块像素高度
    private double mWidth = blocks[0].length * Width;//区块像素宽度
    private int px;//区块在世界的位置(x)
    private int py;//区块在世界的位置(y)

    public void setIsActive(boolean active){
        isActive = active;
    }

    //按照输入构造区块
    public MapBlock(ArrayList<Scenery> initScenes, GroundBlock[][] initBlocks, int initPx, int initPy){
        scenes = initScenes;
        blocks = initBlocks;
        isActive = false;
        px = initPx;
        py = initPy;
    }

    //根据种子随机生成区块
    public MapBlock(long seed){
        String nSeed = seed + "" + px + py;
        Random r = new Random(Long.decode(nSeed));
        for (int i = 0; i < Height; i++){
            for (int j = 0; j < Width; j++){
                blocks[i][j] = new GrassBlock(GrassBlock.Width()*j,GrassBlock.Height()*i);
            }
        }
        for (int i = 0; i < 20 + r.nextInt(40); i++){
            int x = r.nextInt(3);
            switch (x){
                case 0:
                    break;
                case 1:
                    scenes.add(new Grass1(r.nextDouble()*mWidth,r.nextDouble()*mHeight));
                    break;
                case 2:
                    scenes.add(new Grass2(r.nextDouble()*mWidth,r.nextDouble()*mHeight));
                    break;
                default:
                    break;
            }
        }
        isActive = false;
    }

//    public static void main(String[] args){
//        Long seed = Long.valueOf(1);
//        String nSeed = seed + "" + 2 + 1;
//        Random r = new Random(Long.decode(nSeed));
//        for (int i = 0; i < 1000; i++){
//            System.out.println(r.nextInt());
//        }
//    }
    //重新toString方便存入文件
    public String toString(){
        String s = "";
//        s += num + "\n";
        for (Scenery k : scenes){
            s += k.toString() + ',';
        }
        s += '\n';
        for (int i = 0; i < blocks.length; i++){
            for (int j = 0; j < blocks[0].length; j++){
                s += blocks[i][j].toString()+',';
            }
            s += '\n';
        }
        return s;
    }

    //在画布上绘制出当前区块
    public void draw(GraphicsContext gc, double fx, double fy){
        for (int i = 0; i < Height; i++){
            for (int j = 0; j < Width; j++){
                blocks[i][j].draw(gc,fx,fy);
            }
        }
        for (Scenery k : scenes){
            k.draw(gc,fx,fy);
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public void updateMapBloc(){
        if (isActive){
            for (Item k : scenes){
                k.updateItem();
            }
        }
    }
}
