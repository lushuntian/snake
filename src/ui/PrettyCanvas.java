package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//比较漂亮的绘图方法
public class PrettyCanvas extends JPanel implements GameCanvas {
    //内存画布
    private final BufferedImage image;
    private final Graphics2D canvas;

    //单位尺寸
    private final int unitWidth;
    private final int unitHeight;

    //各种图标颜色
    private final Color grass = new Color(90,90,90,160);
    private final Color body = new Color(255,99,71,160);
    private final Color head = new Color(178,34,34,160);
    private final Color food = new Color(50,205,50,160);


    public PrettyCanvas(int width, int height, int unitWidth, int unitHeight){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        canvas = image.createGraphics();
        canvas.setBackground(Color.BLACK);
        this.unitHeight = unitHeight;
        this.unitWidth = unitWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void clearScreen() {
        canvas.clearRect(0,0, image.getWidth(), image.getHeight());
    }

    @Override
    public void drawGrass(int x, int y) {
        canvas.setColor(grass);
        canvas.drawRect(x * unitWidth, y * unitHeight, unitWidth, unitHeight);
    }

    @Override
    public void drawWall(int x, int y) {
    }

    @Override
    public void drawFood(int x, int y) {
        canvas.setColor(food);
        canvas.fillRect(x * unitWidth, y * unitHeight, unitWidth, unitHeight);
    }

    @Override
    public void drawBody(int x, int y) {
        canvas.setColor(body);
        canvas.fillRect(x * unitWidth, y * unitHeight, unitWidth, unitHeight);
    }

    @Override
    public void drawHead(int x, int y) {
        canvas.setColor(head);
        canvas.fillRect(x * unitWidth, y * unitHeight, unitWidth, unitHeight);
    }

    @Override
    public void flush() {
        this.repaint();
    }
}
