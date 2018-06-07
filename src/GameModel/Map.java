package GameModel;
public class Map {
    private MapBlock[] mapBlocks;
    private int winHeight;//可见窗口大小(高)
    private int WinWidth;//可见窗口大小(宽)
    private double wX;//可见窗口位置(x)
    private double wY;//可见窗口位置(y)
    private long seed;

    public Map(MapBlock[] initMapBlocks, int initWinHeight, int initWinWidth, double initWX, double initWY){
        mapBlocks = initMapBlocks;
        winHeight = initWinHeight;
        WinWidth = initWinWidth;
        wX = initWX;
        wY = initWY;
    }

    public Map(long initSeed){
        seed = initSeed;
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed,i%3 -1,i/3 -1);
        }
        mapBlocks[4].setActive(true);
    }

    public void move(String toward){
        mapBlocks[4].setActive(false);
        switch (toward){
            case "UP":
                for (int i = 8; i >=0; i--){
                    if (i < 3){
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()+1);
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
                        mapBlocks[i] = new MapBlock(seed,mapBlocks[i].getPx(),mapBlocks[i].getPy()-1);
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
        }
        mapBlocks[4].setActive(true);
    }
}
