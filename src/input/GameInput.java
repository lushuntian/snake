package input;

/**
 * 游戏控制板，约定游戏的输入控制接口
 * 无论是借由键盘/鼠标/遥控器操作，底层只能由这些基本操作构成。
 */
public interface GameInput {
    //获取移动消息，如果没有方向控制返回null
    Direction getMoveMessage();

    //获取结束消息
    boolean getCloseMessage();

    //获取暂停消息
    boolean getPauseMessage();
}
