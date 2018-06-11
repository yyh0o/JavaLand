package GameModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class River extends Terrain {
    private int[][] waterBlocks;//存放水构成的河流
    private final int Height = 80; //区块高度
    private final int Width = 80; //区块宽度
    private int initX;
    private int initY;
    private int direction;
    private int stepNum;
    private int lastDirection=5;
    private int directionNum=0;
    private int px;//区块在世界的位置(x)
    private int py;//区块在世界的位置(y)


    public River(long seed){
        String nSeed = seed + "";
        Random r = new Random(Long.valueOf(nSeed.hashCode()));
        px =Math.abs( r.nextInt() % 20) - 10;
        py = Math.abs(r.nextInt() % 20) - 10;
        initX=Math.abs(r.nextInt())%80;
        initY=Math.abs(r.nextInt())%80;
        while(px<=9&&px>=-10&&py<=9&&py>=-10){

            waterBlocks = new int[Height][Width];
            for(int i=0;i<waterBlocks.length;i++){
                for(int j=0;j<waterBlocks[0].length;j++){
                    waterBlocks[i][j]=0;
                }
            }
            while(true){
               direction=Math.abs(r.nextInt())%4;
               stepNum=Math.abs(r.nextInt())%10;
               if(direction==0&&lastDirection!=direction){
                   if(initY-stepNum<0||directionNum>9){
                       for (int i = initY; i >= 0; i--) {
                           waterBlocks[i][initX] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       py=py-1;
                       initY=79;
                       break;
                   }
                   else{
                       for(int i=initY;i>=initY-stepNum;i--){
                           waterBlocks[i][initX]=1;
                       }
                       initY-=stepNum;
                   }
                   directionNum++;
                   lastDirection=direction;
               }
               else if(direction==1&&lastDirection!=direction){
                   if(initX+stepNum>79||directionNum>9){
                       for (int i = initX; i <= 79; i++) {
                           waterBlocks[initY][i] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       px+=1;
                       initX=0;
                       break;
                   }
                   else{
                       for(int i=initX;i<=initX+stepNum;i++){
                           waterBlocks[initY][i]=1;
                       }
                      initX+=stepNum;
                   }
                   directionNum++;
                   lastDirection=direction;
               }
               else if(direction==2&&lastDirection!=direction){
                   if(initY+stepNum>79||directionNum>9){
                       for (int i = initY; i <= 79; i++) {
                           waterBlocks[i][initX] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       py+=1;
                       initY=0;
                       break;
                   }
                   else{
                       for(int i=initY;i<=initY+stepNum;i++){
                           waterBlocks[i][initX]=1;
                       }
                       initY+=stepNum;
                   }
                   directionNum++;
                   lastDirection=direction;
               }
               else if(direction==3&&lastDirection!=direction){
                   if(initX-stepNum<0||directionNum>10){
                        if(initX-stepNum<0) {
                            for (int i = initX; i >= 0; i--) {
                                waterBlocks[initY][i] = 1;
                            }
                        }
                        else if(directionNum>10){
                            for(int i=initX;i>=0;i--){
                                waterBlocks[initY][i]=1;
                            }
                        }
                       makeriver();
                       directionNum=0;
                       px=px-1;
                       initX=79;
                       break;
                   }
                   else{
                       for(int i=initX;i>=initX-stepNum;i--){
                           waterBlocks[initY][i]=1;
                       }
                       initX-=stepNum;
                   }
                   directionNum++;
                   lastDirection=direction;
               }
            }
        }
    }

    //储存当前地图块到文件
    public void makeriver(File f){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(this.toString());
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //储存到已有的文件
    public void makeriver(){
        try {
            File f = new File("Dat/MapDat/Water/"+px+","+py+".water");
            if (!f.exists()){
                f.createNewFile();
            }
            makeriver(f);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String toString(){
        String s = "";
        for (int i = 0; i < waterBlocks.length; i++){
            for (int j = 0; j <waterBlocks[0].length; j++){
                    s +=""+ waterBlocks[i][j]+ ' ';
            }
            s+=":";
        }
        return s;
    }






}
