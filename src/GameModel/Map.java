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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Map(long initSeed){
        seed = initSeed;
        mapBlocks = new MapBlock[9];
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed,i%3,i/3);
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
        winHeight = 640;
        WinWidth = 860;
        wX = (width-WinWidth)/2;
        wY = (height-winHeight)/2;
    }


    public static void main(String[] args){
        Map map = new Map();
        while (true){
            map.move("LEFT");
        }
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
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()-1);
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
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()+1);
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
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx()-1,mapBlocks[i].getPy());
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
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx()+1,mapBlocks[i].getPy());
                    }
                    else {
                        if (i%3 == 0){
                            mapBlocks[i].save();
                        }
                        mapBlocks[i] = mapBlocks[i+1];
                    }
                }
                break;
            default:
                break;
        }
        mapBlocks[4].setActive(true);
    }

    public void drawMap(GraphicsContext gc, double time){

        for (int i = 0; i < 9; i++){
            mapBlocks[i].draw(gc,(i%3)*mapBlocks[i].getmWidth(),(i/3)*mapBlocks[i].getmHeight());
        }
        player.draw(gc,time);
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

    public String getWinToward(){
        String s = "";
        if (-(wX-player.getPx()) < (1.0/8)*WinWidth)
            s = "LEFT";
        if (-(wX-player.getPx()) > (7.0/8)*WinWidth)
            s = "RIGHT";
        if (-(wY-player.getPy()) < (1.0/8)*winHeight)
            s = "UP";
        if (-(wY-player.getPy()) > (7.0/8)*winHeight)
            s = "DOWN";
        return s;
    }

    public void winMove(String toward, GraphicsContext gc){
        switch (toward){
            case "UP":
                wY -= player.getSpeed();
                gc.translate(0,player.getSpeed());
                break;
            case "DOWN":
                wY += player.getSpeed();
                gc.translate(0,-player.getSpeed());
                break;
            case "LEFT":
                wX -= player.getSpeed();
                gc.translate(player.getSpeed(),0);
                break;
            case "RIGHT":
                wX += player.getSpeed();
                gc.translate(-player.getSpeed(),0);
                break;
            default:
                break;
        }
    }

    public String getMapToward(){
        String s = "";
        if (wX < width/3)
            s = "LEFT";
        if (wX > width*2/3)
            s = "RIGHT";
        if (wY < height/3)
            s = "UP";
        if (wY > width*2/3)
            s = "DOWN";
        return s;
    }

    public void mapMove(String toward, GraphicsContext gc){
//        System.out.println("before");
//        for (int i = 0; i < 9; i++){
//            System.out.print(mapBlocks[i].getPx()+","+mapBlocks[i].getPy()+" \t");
//            if (i%3==2)
//                System.out.println();
//        }
        switch (toward) {
            case "UP":
                gc.translate(0,-height/3);
                wY += height/3;
                player.setPy(player.getPy()+height/3);
                break;
            case "DOWN":
                gc.translate(0,height/3);
                wY -= height/3;
                player.setPy(player.getPy()-height/3);
                break;
            case "LEFT":
                gc.translate(-width/3,0);
                wX += width/3;
                player.setPx(player.getPx()+width/3);
                break;
            case "RIGHT":
                gc.translate(width/3,0);
                wX -= width/3;
                player.setPx(player.getPx()-width/3);
                break;
            default:
                break;
        }
        move(toward);
//        System.out.println("after");
//        for (int i = 0; i < 9; i++){
//            System.out.print(mapBlocks[i].getPx()+","+mapBlocks[i].getPy()+" \t");
//            if (i%3==2)
//                System.out.println();
//        }
    }
}
/*
py: 3191.0 wy: 2631.0
py: 3950.0 wy: 3390.0
py: 4295.0 wy: 3735.0

*/