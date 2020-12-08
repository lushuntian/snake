package input;

import java.util.Random;

//测试用输入，随机方向
public class RandomInput implements GameInput {
    @Override
    public Direction getMoveMessage() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
    }

    @Override
    public boolean getCloseMessage() {
        return false;
    }

    @Override
    public boolean getPauseMessage() {
        return false;
    }
}
