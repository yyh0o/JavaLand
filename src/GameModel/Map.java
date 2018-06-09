package GameModel;

import javafx.scene.canvas.GraphicsContext;
import java.io.*;


public class Map {
    private MapBlock[] mapBlocks;
    private double winHeight;//可见窗口大小(高)
    private double WinWidth;//可见窗口大小(宽)
    private double wX;//可见窗口位置(x)
    private double wY;//可见窗口位置(y)
    private double height;
    private double width;
    private Role player;
    private long seed;

//    public Map(MapBlock[] initMapBlocks, int initWinHeight, int initWinWidth, double initWX, double initWY){
//        mapBlocks = initMapBlocks;
//        winHeight = initWinHeight;
//        WinWidth = initWinWidth;
//        wX = initWX;
//        wY = initWY;
//    }

    public void setPlayer(Role player){
        this.player = player;
    }

    public Map(long initSeed){
        seed = initSeed;
        mapBlocks = new MapBlock[9];
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed,i%3 -1,i/3 -1);
        }
        mapBlocks[4].setActive(true);
        height = mapBlocks[0].getmHeight()*3;
        width = mapBlocks[0].getmWidth()*3;
        player = new Role(width/2,height/2);
        wX = (width-WinWidth)/2;
        wY = (height-winHeight)/2;

    }

    public Map(){
        try {
            File f = new File("Dat/MapDat/map.seed");
            if (f.exists()){
                BufferedReader br = new BufferedReader(new FileReader(f));
                seed = Long.valueOf(br.readLine());
                br.close();
            }
            else {
                seed = System.nanoTime();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        mapBlocks = new MapBlock[9];
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed,i%3 -1,i/3 -1);
        }
        mapBlocks[4].setActive(true);
        height = mapBlocks[0].getmHeight()*3;
        width = mapBlocks[0].getmWidth()*3;
        player = new Role(width/2,height/2);
        wX = (width-WinWidth)/2;
        wY = (height-winHeight)/2;
    }


    /**
     *move 实现当前地图在整个世界的移动
     * @param toward 允许UP,DOWN,LEFT,RIGHT四个输入<br>
     *               移动相应的方向
     * @return 没有返回值
     */
    public void move(String toward){
        mapBlocks[4].setActive(false);
        switch (toward){
            case "UP":
                for (int i = 8; i >=0; i--){
                    if (i < 3){
                        File f = new File(mapBlocks[i].getPx()+","+(mapBlocks[i].getPy()+1)+".map");
                        if (f.exists()){
                            mapBlocks[i] = new MapBlock(f);
                        }
                        else {
                            mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()+1);
                        }
                    }
                    else {
                        if (i > 5){
                            mapBlocks[i].save();
                        }
                        mapBlocks[i] = mapBlocks[i-3];
                    }
                }
                break;
            case "DOWN":
                for (int i = 0; i < 9; i++){
                    if (i > 5){
                        File f = new File(mapBlocks[i].getPx()+","+(mapBlocks[i].getPy()-1)+".map");
                        if (f.exists()){
                            mapBlocks[i] = new MapBlock(f);
                        }else {
                            mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()-1);
                        }
                    }
                    else {
                        if (i < 3){
                            mapBlocks[i].save();
                        }
                        mapBlocks[i] = mapBlocks[i+3];
                    }
                }
                break;
            case "LEFT":
                for (int i = 8; i >=0; i--){
                    if (i%3 == 0){
                        File f = new File((mapBlocks[i].getPx()-1)+","+mapBlocks[i].getPy()+".map");
                        if (f.exists()){
                            mapBlocks[i] = new MapBlock(f);
                        }
                        else {
                            mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx()-1,mapBlocks[i].getPy());
                        }
                    }
                    else {
                        if (i%3 == 2){
                            mapBlocks[i].save();
                        }
                        mapBlocks[i] = mapBlocks[i-1];
                    }

                }
                break;
            case "RIGHT":
                for (int i = 0; i < 9; i++){
                    if (i%3 == 2){
                        File f = new File((mapBlocks[i].getPx()+1)+","+(mapBlocks[i].getPy())+".map");
                        if (f.exists()){
                            mapBlocks[i] = new MapBlock(f);
                        }
                        else {
                            mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx()+1,mapBlocks[i].getPy());
                        }
                    }
                    else {
                        if (i%3 == 0){
                            mapBlocks[i].save();
                        }
                        mapBlocks[i] = mapBlocks[i+1];
                    }
                }
                break;
        }
        mapBlocks[4].setActive(true);
    }

    public void drawMap(GraphicsContext gc){
        for (int i = 0; i < 9; i++){
            mapBlocks[i].draw(gc,(i%3)*mapBlocks[i].getmWidth(),(i/3)*mapBlocks[i].getmHeight());
        }
    }

    public void save(){
        for (int i = 0; i < 9; i++){
            mapBlocks[i].save();
        }

        player.save();

        File f = new File("Dat/MapDat/map.seed");
        if (!f.exists()){
            try {
                f.createNewFile();
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(String.valueOf(seed));
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
