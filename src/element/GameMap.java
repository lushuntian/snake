package element;

import input.GameInput;
import ui.GameCanvas;

//管理全部游戏元素的容器，对外提供高层次接口
public final class GameMap {
    //容器宽度，包含边界
    private final int colNum;

    //容器高度，包含边界
    private final int rowNum;

    //食物
    private final Food food;

    //蛇
    private final Snake snake;

    //是否已达到结束条件（游戏状态机已不能再运行）
    private boolean isOver;

    //创建并初始化一个游戏容器
    public GameMap(int width, int height, int initBodyLength){
        //四周边界各占据一个格子
        this.colNum = width + 2;
        this.rowNum = height + 2;
        snake = new Snake(width / 2 + 1, height / 2 + 1, initBodyLength, this);
        food = new Food(this);
        isOver = false;
    }

    //更新一帧
    public void updateFrame(GameInput input){
        if (input.getPauseMessage())
            return;

        snake.setDirection(input.getMoveMessage());

        if (!isOver)
            isOver = !snake.moveStep();

        if (input.getCloseMessage())
            isOver = true;
    }

    //绘制容器
    public void drawMap(GameCanvas canvas){
        canvas.clearScreen();

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (!checkInBorder(i, j))
                    canvas.drawWall(i, j);
                else
                    canvas.drawGrass(i, j);
            }
        }

        food.drawTo(canvas);
        snake.drawTo(canvas);

        canvas.flush();
    }

    //检查(x, y)位置是否是空地
    public boolean checkIsSpace(int x, int y)
    {
        if (!checkInBorder(x, y))
            return  false;

        return !snake.checkInBody(x, y);
    }

    //检查(x, y)位置是否是食物
    public boolean checkIsFood(int x, int y)
    {
        return food.getX() == x && food.getY() == y;
    }

    //检查(x, y)位置是否在边界内
    public boolean checkInBorder(int x, int y)
    {
        if (x <= 0 || x >= colNum - 1)
            return  false;

        return y > 0 && y < rowNum - 1;
    }

    //重置食物，如果没有可放置的位置，游戏结束
    public void resetFood(){
        if(!food.resetLocation())
            isOver = true;
    }

    public int getColNum() {
        return colNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public boolean isOver() {
        return isOver;
    }
}
