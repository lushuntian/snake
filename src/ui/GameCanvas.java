package ui;

//渲染器接口,封装绘制方法
public interface GameCanvas {
    //清屏并初始化界面
    void clearScreen();

    //画草地
    void drawGrass(int x, int y);

    //画墙壁
    void drawWall(int x, int y);

    //画食物
    void drawFood(int x, int y);

    //画蛇身
    void drawBody(int x, int y);

    //画蛇头
    void drawHead(int x, int y);

    //清空缓存，绘制到界面
    void flush();
}
