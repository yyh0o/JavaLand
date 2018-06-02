package GameModel;
public class Map {
    private MapBlock[] mapBlocks;
    private int winHeight;//可见窗口大小(高)
    private int WinWidth;//可见窗口大小(宽)
    private double wX;//可见窗口位置(x)
    private double wY;//可见窗口位置(y)

    public Map(MapBlock[] initMapBlocks, int initWinHeight, int initWinWidth, double initWX, double initWY){
        mapBlocks = initMapBlocks;
        winHeight = initWinHeight;
        WinWidth = initWinWidth;
        wX = initWX;
        wY = initWY;
    }

    public Map(long seed){
        for (int i = 0; i < 9; i++){
            mapBlocks[i] = new MapBlock(seed);
        }
        mapBlocks[4].setActive(true);
    }

    public void move(String toward){

    }
}
