package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter implements GameInput {
    private Direction currentDirection = Direction.RIGHT;
    private boolean paused = false;
    private boolean closed = false;

    @Override
    public Direction getMoveMessage() {
        return currentDirection;
    }

    @Override
    public boolean getCloseMessage() {
        return closed;
    }

    @Override
    public boolean getPauseMessage() {
        return paused;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //根据按键生成游戏输入消息
        //蛇当前移动方向的相反方向将被忽略

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP && currentDirection != Direction.DOWN) {
            currentDirection = Direction.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentDirection != Direction.UP) {
            currentDirection = Direction.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentDirection != Direction.RIGHT) {
            currentDirection = Direction.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentDirection != Direction.LEFT) {
            currentDirection = Direction.RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            paused = !paused;
        }
    }
}
