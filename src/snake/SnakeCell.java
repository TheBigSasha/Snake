package snake;

import java.awt.*;

public class SnakeCell extends Cell{
    boolean isHead = false;
    /**
     * Create a cell of a specified type
     *
     * @param x
     * @param y
     */
    public SnakeCell(int x, int y) {
        super(x, y);
    }
    public SnakeCell(int x, int y, boolean isHead) {
        super(x, y);
        this.isHead = isHead;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    @Override
    public int eat() {
        return -1;
    }

    @Override
    public Color getColor() {
        return isHead ? Color.GREEN.darker() : Color.GREEN;
    }
}
