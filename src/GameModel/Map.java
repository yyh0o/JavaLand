package GameModel;

import com.sun.org.apache.bcel.internal.generic.DDIV;
import javafx.scene.canvas.GraphicsContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


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

//    public double getHeight() {
//        return height;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public double getWinHeight() {
//        return winHeight;
//    }
//
//    public double getWinWidth() {
//        return WinWidth;
//    }

    public double getwX() {
        return wX;
    }

    public double getwY() {
        return wY;
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
        Double tx = null;
        Double ty = null;
        String actBlock = null;
        try {
            File f = new File("Dat/MapDat/map.seed");
            if (f.exists()){
                BufferedReader br = new BufferedReader(new FileReader(f));
                seed = Long.valueOf(br.readLine());
                String s = br.readLine();
                actBlock = br.readLine();
                tx = Double.valueOf(s.split(",")[0]);
                ty = Double.valueOf(s.split(",")[1]);
                br.close();
            }
            else {
                seed = System.nanoTime();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        int px = 0;
        int py = 0;
        if (actBlock != null){
            px = Integer.parseInt(actBlock.split(",")[0]);
            py = Integer.parseInt(actBlock.split(",")[1]);
        }
        mapBlocks = new MapBlock[9];
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed,i%3 -1 + px,i/3 -1 + py);
        }
        mapBlocks[4].setActive(true);
        height = mapBlocks[0].getmHeight()*3;
        width = mapBlocks[0].getmWidth()*3;
        winHeight = 640;
        WinWidth = 860;
        if (tx != null && ty != null){
            wX = tx;
            wY = ty;
        }
        else {
            wX = (width-WinWidth)/2;
            wY = (height-winHeight)/2;
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
            bw.write(String.valueOf(seed)+'\n');
            bw.write(wX+","+wY+'\n');
            bw.write(mapBlocks[4].getPx()+","+mapBlocks[4].getPy()+'\n');
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

    public void checkAttack(){
        if (player.isAttack()){
            double ax = player.getPx();
            double ay = player.getPy();
            double aw = player.getWidth();
            double ah = player.getHeight();
            ArrayList<Scenery> sceneries = mapBlocks[4].getScenes();
//            for (Scenery k : sceneries){
//                Scenery tk = k;
//                double bx = k.getPx();
//                double by = k.getPy();
//                double bw = k.getWidth();
//                double bh = k.getWidth();
//                if (ax<bx+bw&& ax+aw>bx&& ay<by+bh&& ah+ay>by){
//                    sceneries.remove(tk);
//                }
//            }
            for (int i = 0; i < 9; i++){
                Iterator<Scenery> iterator = mapBlocks[i].getScenes().iterator();
                while (iterator.hasNext()){
                    Scenery k = iterator.next();
                    double bx = k.getPx()+(i%3)*width/3;
                    double by = k.getPy()+(i/3)*height/3;
                    double bw = k.getWidth();
                    double bh = k.getWidth();
                    if (ax<bx+bw&& ax+aw>bx&& ay<by+bh&& ah+ay>by){
                        iterator.remove();
                    }

                }
            }
        }
    }
}