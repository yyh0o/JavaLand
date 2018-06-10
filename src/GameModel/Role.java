package GameModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;

public class Role extends Biological {
    private Body body;
    private String Rtoward;
    private Image image;
    public Image[] frames;
    public double duration=0.1;
    private String LastStep="DOWN";
    public void move(ArrayList<String> input){
        if (input.contains("UP") || input.contains("W")) {
            LastStep="UP";
            for(int i=1;i<=4;i++){
                frames[i-1]=new Image("resource/Role1UP"+i+".png");
            }
            move("UP");


        }
        else if (input.contains("DOWN") || input.contains("S")) {
            LastStep="DOWN";
            for(int i=1;i<=4;i++){
                frames[i-1]=new Image("resource/Role1DOWN"+i+".png");
            }
            move("DOWN");


        }
        else if (input.contains("LEFT") || input.contains("A")){
            LastStep="LEFT";
            for(int i=1;i<=4;i++){
                frames[i-1]=new Image("resource/Role1LEFT"+i+".png");
            }
            move("LEFT");


        }
        else if (input.contains("RIGHT") || input.contains("D")){
            LastStep="RIGHT";
            for(int i=1;i<=4;i++){
                frames[i-1]=new Image("resource/Role1RIGHT"+i+".png");
            }
            move("RIGHT");

        }
        else{
            for(int i=1;i<=4;i++){
                frames[i-1]=new Image("resource/Role1"+LastStep+1+".png");
            }
        }
    }

    public void move(String toward){
        Rtoward = toward;
        if (toward.equals("UP")){
            setPy(getPy()-speed);
        }
        if (toward.equals("DOWN")){
            setPy(getPy()+speed);
        }
        if (toward.equals("LEFT")){
            setPx(getPx()-speed);
        }
        if (toward.equals("RIGHT")){
            setPx(getPx()+speed);
        }
    }
//    public Role(double x, double y,Image[] images){
//        super(x,y);
//        speed = 3;
//        Rtoward = "DOWN";
//        frames=images;
//    }

    public Role(){
        File f = new File("Dat/player.info");
        try {
            if (!f.exists()){
                setPx(32*40);
                setPy(32*40);
            }
            else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String s = br.readLine();
                setPx(Double.valueOf(s.split(",")[0]));
                setPx(Double.valueOf(s.split(",")[1]));
                br.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        speed = 3;
        Rtoward = "DOWN";
        frames=new Image[4];
        for(int i=1;i<=4;i++){
            frames[i-1]=new Image("resource/Role1DOWN"+i+".png");
        }
    }

    public Role(double x, double y){
        super(x,y);
        speed = 3;
        Rtoward = "DOWN";
        frames=new Image[4];
        for(int i=1;i<=4;i++){
            frames[i-1]=new Image("resource/Role1DOWN"+i+".png");
        }
    }

    public void draw(GraphicsContext gc, double time){
        gc.drawImage(getFrame(time),getPx(),getPy());
    }

    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void save(){
        File f = new File("Dat/player.info");
        try {
            if (!f.exists()){
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(getPx()+","+getPy());
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
