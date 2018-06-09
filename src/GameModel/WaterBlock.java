package GameModel;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class WaterBlock extends GroundBlock {
    public void draw(GraphicsContext gc, double fx, double fy) {
        gc.drawImage(ImageLibray.getImage("WaterBlock"),getPx() + fx,getPy() + fy);
    }

    public WaterBlock(double x, double y){
        super(x,y);
    }
    public WaterBlock(){
    }

//    @Override
//    public String toString() {
//        String s = "WaterBlock:"+getPx()+"."+getPx();
//        return s;
//    }

    public void makeRiver(GroundBlock[][] bigGround){
        int bigRow=bigGround[0].length;
        int bigCol=bigGround.length;
        final long startNanoTime=System.nanoTime();
        Random random=new Random(startNanoTime);
        System.out.println(random.toString());
        for(int k=0;k<bigCol;k++){
            for(int l=bigRow-1;l>0;l--){
                if((k+l)>=199&&(k+l)<=201){
                    bigGround[k][l]=new WaterBlock(l*32,k*32);
                }
                if (k==l||k==l-1||k==l-50||k==l-51){
                    bigGround[k][l]=new WaterBlock(l*32,k*32);
                }

            }
        }
        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                   if(i*i+j*j<=900){
                       bigGround[100+i][100+j]=new WaterBlock((100+j)*32,(100+i)*32);
                   }
            }
        }
        /*for(int i=Math.min(a,c);i<=Math.max(a,c);i++){
            for(int j=Math.max(b,d);j>=Math.min(b,d);j--){
                if((i+j)==(a+b)||(i+j)==(c+d)){
                    bigGround[i][j]=new WaterBlock(j*32,i*32);
                }
            }
        }*/



        /*for(int i=0;i<bigCol;i++){
            for(int j=64;j<85;j++){
                if((i+j)>(150)){
                    bigGround[i][j] = new WaterBlock(j * 32, i * 32);
                }
            }
        }*/
        /*for(int i=c;i<bigRow;i++){
            for(int j=0;j<d;j++){
                if((i+j)==(c+d)) {
                    bigGround[i][j] = new WaterBlock(j * 32, i * 32);
                }
            }
        }*/
    }
}
