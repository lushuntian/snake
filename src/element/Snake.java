package element;

import input.Direction;
import ui.GameCanvas;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private static class SnakeNode {
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        private final int x;
        private final int y;

        public SnakeNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //方向
    private Direction direction = Direction.RIGHT;

    //蛇头+蛇身
    private final Deque<SnakeNode> nodes;

    protected final GameMap map;

    public Snake(int x, int y, int initBodyLength, GameMap map) {
        this.map = map;
        nodes = new LinkedList<>();
        nodes.addFirst(new SnakeNode(x, y));

        //初始蛇身为默认方向增长一段距离
        for (int i = 0; i < initBodyLength; i++) {
            nodes.addFirst(nextNode());
        }  
    }


    //移动一步
    public boolean moveStep(){
        SnakeNode node = nextNode();
        //遇到墙壁，返回移动失败消息
        if (!map.checkIsSpace(node.getX(), node.getY())){
            return false;
        }

        nodes.addFirst(nextNode());
        //遇到食物，增长身体并重置食物，否则，删掉尾巴
        if (map.checkIsFood(node.getX(), node.getY())){
            map.resetFood();
        }else{
            nodes.removeLast();
        }

        return true;
    }

    public void setDirection(Direction direction) {
        if (direction != null)
            this.direction = direction;
    }

    //检查(x, y)位置是否在边界内
    public boolean checkInBody(int x, int y)
    {
        for (SnakeNode node: nodes) {
            if (node.getX() == x && node.getY() == y)
                return  true;
        }

        return false;
    }

    //运动后的下一个节点
    private SnakeNode nextNode(){
        SnakeNode header = nodes.getFirst();
        int x, y;
        switch (this.direction) {
            case UP -> {
                x = header.getX(); y = header.getY() - 1;
            }
            case DOWN -> {
                x = header.getX(); y = header.getY() + 1;
            }
            case LEFT -> {
                x = header.getX() - 1; y = header.getY();
            }
            case RIGHT -> {
                x = header.getX() + 1; y = header.getY();
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.direction);
        }

        return new SnakeNode(x, y);
    }

    public void drawTo(GameCanvas canvas){
        int count = 0;
        SnakeNode header = nodes.getFirst();
        for (SnakeNode node: nodes) {
            if (count++ > 0)
                canvas.drawBody(node.getX(), node.getY());
        }

        canvas.drawHead(header.getX(), header.getY());
    }
}





