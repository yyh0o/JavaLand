package GameModel;

import java.io.*;
import java.util.Random;

public class River extends Terrain {
    private int[][] waterBlocks;//存放水构成的河流
    private final int Height = 80; //区块高度
    private final int Width = 80; //区块宽度
    private final int sizeX=10;//控制产生的预备水的横长
    private final int sizeY=10;//控制产生的预备水的纵长
    private int initX;//在当前块中产生到的位置横坐标
    private int initY;//在当前块中产生到的位置纵坐标
    private int direction;//随机生成的一个方向
    private int stepNum;//随机生成的一个步数
    private int lastDirection=5;//之前一步的方向
    private int directionNum=0;//在当前区域改变了多少次方向
    private int[][] blockNum;//每个大区域当前的已经改变过的次数
    private int px;//区块在世界的位置(x)
    private int py;//区块在世界的位置(y)


    public River(long seed){
        //随机生成各种元素
        String nSeed = seed + "";
        Random r = new Random(Long.valueOf(nSeed.hashCode()));
        px =Math.abs( r.nextInt() % 10) - 5;
        py = Math.abs(r.nextInt() % 10) - 5;
        initX=Math.abs(r.nextInt())%80;
        initY=Math.abs(r.nextInt())%80;
        //开始生成水块
        while(px<=4&&px>=-5&&py<=4&&py>=-5){
            //若是第一次产生的话初始，第二次直接调用
            File file=new File("Dat/MapDat/Water/"+px+","+py+".water");
            blockNum=new int[sizeY][sizeX];
            for(int k=0;k<blockNum.length;k++){
                for(int m=0;m<blockNum[0].length;m++){
                    blockNum[k][m]=0;
                }
            }
            if(!file.exists()) {
                waterBlocks = new int[Height][Width];
                for (int i = 0; i < waterBlocks.length; i++) {
                    for (int j = 0; j < waterBlocks[0].length; j++) {
                        waterBlocks[i][j] = 0;
                    }
                }
            }
            else if(file.exists()){
                try{
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String[] H = br.readLine().split(":");
                    for(int i=0;i<waterBlocks.length;i++) {
                        String[] W=H[i].split(" ");
                        for(int j=0;j<waterBlocks[0].length;j++) {
                            waterBlocks[i][j]=Integer.parseInt(W[j]);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //开始产生水块
            while(true){
               direction=Math.abs(r.nextInt())%4;
               stepNum=Math.abs(r.nextInt())%10;
               if(direction==0&&lastDirection!=direction){
                   if((initY-stepNum<0||directionNum>9)&&blockNum[py+5][px+5]<2){
                       for (int i = initY; i >= 0; i--) {
                           waterBlocks[i][initX] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       blockNum[py+5][px+5]++;
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
               else if((direction==1&&lastDirection!=direction)&&blockNum[py+5][px+5]<2){
                   if(initX+stepNum>79||directionNum>9){
                       for (int i = initX; i <= 79; i++) {
                           waterBlocks[initY][i] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       blockNum[py+5][px+5]++;
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
               else if((direction==2&&lastDirection!=direction)&&blockNum[py+5][px+5]<2){
                   if(initY+stepNum>79||directionNum>9){
                       for (int i = initY; i <= 79; i++) {
                           waterBlocks[i][initX] = 1;
                       }
                       makeriver();
                       directionNum=0;
                       blockNum[py+5][px+5]++;
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
               else if((direction==3&&lastDirection!=direction)&&blockNum[py+5][px+5]<2){
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
                       blockNum[py+5][px+5]++;
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
    //将水块的数组转化为字符串
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
