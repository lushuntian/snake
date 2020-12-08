import ui.PrettyCanvas;
import element.GameMap;
import input.KeyboardInput;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeProgram {
    //单位尺寸
    private final static int UNIT_WIDTH = 24;
    private final static int UNIT_HEIGHT = 24;

    //贪吃蛇格子数目
    private final static int ROW_NUM = 22;
    private final static int COL_NUM = 30;

    //界面尺寸
    private final static int WIDTH = UNIT_WIDTH * (COL_NUM + 2);
    private final static int HEIGHT = UNIT_HEIGHT * (ROW_NUM + 2);

    //初始蛇身长度
    private final static int INIT_BODY_LENGTH = 10;

    //应用名称
    private final static String APP_NAME = "JAVA 贪吃蛇";

    public static void main(String[] args){
        //创建窗体
        JFrame frame = new JFrame(APP_NAME);
        frame.setSize(WIDTH + 15, HEIGHT + 36);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameMap map = new GameMap(COL_NUM, ROW_NUM, INIT_BODY_LENGTH);
        KeyboardInput input = new KeyboardInput();
        PrettyCanvas canvas = new PrettyCanvas(WIDTH, HEIGHT, UNIT_WIDTH, UNIT_HEIGHT);
        frame.addKeyListener(input);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.updateFrame(input);
                map.drawMap(canvas);
                if (map.isOver()) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        }, 1000, 100);

        frame.add(canvas);
        frame.setVisible(true);
    }
}
