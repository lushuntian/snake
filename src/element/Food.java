package element;

import ui.GameCanvas;
import java.util.Random;

public class Food {
    private int x;

    private int y;

    private final GameMap map;

    public Food(GameMap map) {
        this.map = map;
        resetLocation();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //重置食物位置，如果没有可放置的位置，返回false
    public boolean resetLocation() {
        Random random = new Random();
        int row = random.nextInt(map.getRowNum() - 2) + 1;
        int col = random.nextInt(map.getColNum() - 2) + 1;
        int preRow = row;
        int preCol = col;

        //如果随机的位置不为空，则尝试下个位置，直到位置用尽
        while (!map.checkIsSpace(col, row)) {
            if (++col >= map.getColNum()) {
                col = 1;
                if (++row >= map.getRowNum()){
                    row = 1;
                }
            }

            if (col == preCol && row == preRow)
                return  false;
        }

        x = col;
        y = row;

        return true;
    }

    public void drawTo(GameCanvas canvas) {
        canvas.drawFood(getX(), getY());
    }
}
